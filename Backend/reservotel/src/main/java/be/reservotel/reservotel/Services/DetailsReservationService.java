package be.reservotel.reservotel.Services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import be.reservotel.reservotel.DTO.CustomPannierDTO;
import be.reservotel.reservotel.DTO.SelectionChambreDTO;
import be.reservotel.reservotel.Model.Chambre;
import be.reservotel.reservotel.Model.DetailsReservation;
import be.reservotel.reservotel.Model.OptionHotel;
import be.reservotel.reservotel.Model.Reservation;
import be.reservotel.reservotel.Repository.ChambreRepository;
import be.reservotel.reservotel.Repository.DetailsReservationRepository;
import be.reservotel.reservotel.Repository.OptionHotelRepository;

@Service
public class DetailsReservationService {

    @Autowired
    private DetailsReservationRepository detailsReservationRepository;


    @Autowired
    private ChambreRepository chambreRepository;

    @Autowired
    private OptionHotelService optionHotelService;

    @Autowired
    private OptionHotelRepository optionHotelRepository;




    // public void createDetailsReservation(CustomPannierDTO pannierDTO, Reservation res) {
    //     DetailsReservation detailsReservation = new DetailsReservation();
    //     Long idHotel = pannierDTO.getIdHotel();
    //     Long dureeSejour = calculDureeSejour(pannierDTO.getdateArriveURL(), pannierDTO.getDateDepartURL());

    //     for (SelectionChambreDTO selection : pannierDTO.getPannier()){
    //         Pageable pageable = PageRequest.of(0, 1);
    //         int nombrePersonnes = selection.getNombrePersonnes();
    //         List<Chambre> chambre = chambreRepository.getDistinctChambre(nombrePersonnes, idHotel, pageable);
    //         Long idChambre = chambre.get(0).getIdChambre();
    //         double prixTotalOptions = 0;
    //         double prixTotalChambre = 0;

    //         List<OptionHotel> optionHotelList = optionHotelService.getListeOptionHotel(selection.getOptions(), idHotel);

    //         for (OptionHotel option : optionHotelList){
    //             prixTotalOptions += option.getPrixOption();
    //         }

    //         prixTotalChambre = calculPrixTotalChambre(selection.getPrixBase(), dureeSejour, prixTotalOptions);


    //         detailsReservation.setPrixChambre(selection.getPrixBase());
    //         detailsReservation.setReservation(res);
    //         detailsReservation.setChambre(chambre.get(0));
    //         detailsReservation.setPrixTotal(prixTotalChambre);
    //         detailsReservation.setOptionHotels(optionHotelList);
    //         detailsReservation = detailsReservationRepository.save(detailsReservation);

    //         optionHotelService.insertDetailsReservationOptionHotel(detailsReservation.getIdDetailsReservation());

    //     }
    // }


    public List<Double> genereListePrixTotalChambre(CustomPannierDTO pannier, Reservation res) {
        List<Double> listePrix = new ArrayList<>();
        Long idHotel = pannier.getIdHotel();

        for (SelectionChambreDTO selection : pannier.getPannier()){
            double prixBase = selection.getPrixBase();
            List<Double> listePrixOptionsHotel = new ArrayList<>();
            for(Long option : selection.getOptions()){
                Long idOptionHotel = optionHotelService.getIdOptionHotel(option, pannier.getIdHotel());
                double prixOptionHotel = optionHotelService.getPrixOptionHotel(idOptionHotel);
                listePrixOptionsHotel.add(prixOptionHotel);
            }
            double prixTotalUneChambre = calculTotalUnechambre(prixBase, listePrixOptionsHotel);
            listePrix.add(prixTotalUneChambre);

            List<OptionHotel> optionHotelList = optionHotelService.getListeOptionHotel(selection.getOptions(), idHotel);
            Pageable pageable = PageRequest.of(0, 1);
            int nombrePersonnes = selection.getNombrePersonnes();
            List<Chambre> chambre = chambreRepository.getDistinctChambre(nombrePersonnes, idHotel, pageable);


            createDetailsReservation(prixBase, prixTotalUneChambre, res, optionHotelList, chambre.get(0));
        }
        return listePrix;
    }





    private void createDetailsReservation(double prixBase, double prixTotalUneChambre, Reservation res, List<OptionHotel> optionHotelList, Chambre chambre) {
        DetailsReservation detailsReservation = new DetailsReservation();
        detailsReservation.setPrixChambre(prixBase);
        detailsReservation.setPrixTotal(prixTotalUneChambre);
        detailsReservation.setReservation(res);
        detailsReservation.setChambre(chambre);
        detailsReservation.setOptionHotels(optionHotelList);
        detailsReservation = detailsReservationRepository.save(detailsReservation);
        chambreRepository.setChambreIndisponible(chambre.getIdChambre());
    }






    public double calculPrixTotalChambre(CustomPannierDTO pannierDTO, Reservation res) {
        List<Double> listePrixTotal = genereListePrixTotalChambre(pannierDTO, res);
        double prixTotalDesChambres = 0;

        for(double prixTotal : listePrixTotal){
            prixTotalDesChambres += prixTotal;
        }

        return prixTotalDesChambres;
    }


    public double calculTotalUnechambre(double prixBase, List<Double> listePrixOptionsHotel){
        double prixTotalOptions = 0;
        for (double prixOption : listePrixOptionsHotel){
            prixTotalOptions += prixOption;
        }
        return prixTotalOptions + prixBase;
    }


    

    public double calculPrixOptions(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculPrixOptions'");
    }


    public Long calculDureeSejour(String dateArriveStr, String dateDepartStr) {
        LocalDate dateArrive = LocalDate.parse(dateArriveStr);
        LocalDate dateDepart = LocalDate.parse(dateDepartStr);

        return ChronoUnit.DAYS.between(dateArrive, dateDepart);
    }






    


}
