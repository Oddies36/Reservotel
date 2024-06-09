package be.reservotel.reservotel.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import be.reservotel.reservotel.DTO.ChambreDTO;
import be.reservotel.reservotel.DTO.CustomPannierDTO;
import be.reservotel.reservotel.DTO.DetailsReservationDTO;
import be.reservotel.reservotel.DTO.HotelDTO;
import be.reservotel.reservotel.DTO.ReservationDTO;
import be.reservotel.reservotel.DTO.SelectionChambreDTO;
import be.reservotel.reservotel.Model.Chambre;
import be.reservotel.reservotel.Model.Client;
import be.reservotel.reservotel.Model.DetailsReservation;
import be.reservotel.reservotel.Model.Hotel;
import be.reservotel.reservotel.Model.Reservation;
import be.reservotel.reservotel.Repository.ChambreRepository;
import be.reservotel.reservotel.Repository.ClientRepository;
import be.reservotel.reservotel.Repository.DetailsReservationRepository;
import be.reservotel.reservotel.Repository.OptionHotelRepository;
import be.reservotel.reservotel.Repository.ReservationRepository;

@Service
public class ReservationService {

    @Autowired
    private DetailsReservationService detailsReservationService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ChambreRepository chambreRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private DetailsReservationRepository detailsReservationRepository;

    @Autowired
    private OptionHotelRepository optionHotelRepository;

    public void createReservation(CustomPannierDTO pannierDTO) {

        Reservation res = new Reservation();
        Client client = new Client();
        boolean choixPointFidelite = pannierDTO.getChoixPointFidelite();

        String userEmail = pannierDTO.getUserEmail();
        client = clientRepository.findByEmail(userEmail);
        Long idUser = client.getIdClient();

        String dateArrive = pannierDTO.getdateArriveURL();
        String dateDepart = pannierDTO.getDateDepartURL();

        int pointsFideliteActuel = clientRepository.getPointsFideliteByIdClient(idUser);
        
        res.setDateArrive(LocalDate.parse(dateArrive));
        res.setDateDepart(LocalDate.parse(dateDepart));
        res.setStatutReservation("En cours");
        res.setClient(client);
        res = reservationRepository.save(res);

        Double prixTotal = calculTotalReservation(pointsFideliteActuel, pannierDTO, res);

        if (choixPointFidelite) {
            double reduc = calculReductionFidelite(pointsFideliteActuel);
            prixTotal -= reduc;
            clientRepository.resetPointsFidelite(client.getIdClient());
            pointsFideliteActuel = 0;
        }

        res.setPrixTotalReservation(prixTotal);
        res = reservationRepository.save(res);

        int nouveauPointsFidelite = calculAjoutPointsFidelite(pointsFideliteActuel, prixTotal);

        System.out.println("Le client: " + client.getIdClient());
        System.out.println("Nouveau points fidelite: " + nouveauPointsFidelite);
        clientRepository.addPointsFidelite(client.getIdClient(), nouveauPointsFidelite);

    }


    public List<ReservationDTO> getReservationsByClientEmail(String email, int page, int limit) {
        List<Reservation> reservations = reservationRepository.getReservationsByEmail(email);

        // Pagination
        int start = Math.min((page - 1) * limit, reservations.size());
        int end = Math.min(page * limit, reservations.size());
        List<Reservation> paginatedReservations = reservations.subList(start, end);

        int totalReservations = reservations.size();

        List<ReservationDTO> reservationDTOs = new ArrayList<>();
        for (Reservation reservation : paginatedReservations) {
            ReservationDTO dto = addToDTO(reservation);
            dto.setTotalReservations(totalReservations);
            reservationDTOs.add(dto);
        }
        return reservationDTOs;
    }



    private ReservationDTO addToDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setIdReservation(reservation.getIdReservation());
        dto.setStatutReservation(reservation.getStatutReservation());
        dto.setDateArrive(reservation.getDateArrive());
        dto.setDateDepart(reservation.getDateDepart());
        dto.setPrixTotalReservation(reservation.getPrixTotalReservation());

        List<DetailsReservationDTO> detailsDTOs = new ArrayList<>();
        for (DetailsReservation detail : reservation.getDetailsReservations()) {
            DetailsReservationDTO detailsDTO = new DetailsReservationDTO();
            detailsDTO.setIdDetailsReservation(detail.getIdDetailsReservation());
            detailsDTO.setPrixChambre(detail.getPrixChambre());
            detailsDTO.setPrixTotal(detail.getPrixTotal());

            Chambre chambre = detail.getChambre();
            ChambreDTO chambreDTO = new ChambreDTO();
            chambreDTO.setIdChambre(chambre.getIdChambre());
            chambreDTO.setNumeroChambre(chambre.getNumeroChambre());
            chambreDTO.setPrixBase(chambre.getPrixBase());

            Hotel hotel = chambre.getHotel();
            HotelDTO hotelDTO = new HotelDTO();
            hotelDTO.setIdHotel(hotel.getIdHotel());
            hotelDTO.setNomHotel(hotel.getNomHotel());
            hotelDTO.setPhotoHotel(hotel.getPhotoHotel());

            chambreDTO.setHotel(hotelDTO);
            detailsDTO.setChambre(chambreDTO);

            detailsDTOs.add(detailsDTO);
        }

        dto.setDetailsReservations(detailsDTOs);
        return dto;
    }



    /**
     * Calcule la réduction en fonction des points de fidelité. Il s'agit de 1% de réduction par point de fidelité
     * @param pointsFideliteActuel Les points de fidelité actuels du client
     * @return Retourne la réduction en fonction des points de fidelité
     */
    public double calculReductionFidelite(int pointsFideliteActuel){
        return pointsFideliteActuel * 0.01;
    };

    /**
     * Calcule le prix total de la réservation en fonction des points de fidelité
     * @param pointsFideliteActuel Les points de fidelité actuels du client
     * @return Retourne le prix total de la réservation en déduisant la réduction
     */
    public double calculTotalReservation(int pointsFideliteActuel, CustomPannierDTO pannierDTO, Reservation res){
        double prixTotalChambres = detailsReservationService.calculPrixTotalChambre(pannierDTO, res);
        double reduction = this.calculReductionFidelite(pointsFideliteActuel);

        return prixTotalChambres - reduction;
    }

    /**
     * Calcule le nombre de points de fidelité à ajouter en fonction du prix total de la réservation. Il peut en avoir un maximum de 10.000
     * @param pointFideliteActuel Les points de fidelité actuels du client
     * @param prixTotalReservationApresReduction Le prix total de la réservation après réduction
     * @return Retourne le nombre de points de fidelité à ajouter. Si le nombre de points de fidelité dépasse 10.000, il retourne 10.000
     */
    public int calculAjoutPointsFidelite(double pointFideliteActuel, double prixTotalReservationApresReduction){
        double pointsMax = 10000;
        double resultat = pointFideliteActuel + prixTotalReservationApresReduction;

        if(resultat > pointsMax){
            return (int)pointsMax;
        }
        return (int)resultat;
    }
}
