package be.reservotel.reservotel.DTO;

import java.util.List;

public class DetailsReservationDTO {
    private Long idDetailsReservation;
    private double prixChambre;
    private double prixTotal;
    private List<OptionHotelDTO> optionHotels;
    private ReservationDTO reservation;
    private ChambreDTO chambre;

    public Long getIdDetailsReservation() {
        return idDetailsReservation;
    }

    public void setIdDetailsReservation(Long idDetailsReservation) {
        this.idDetailsReservation = idDetailsReservation;
    }

    public double getPrixChambre() {
        return prixChambre;
    }

    public void setPrixChambre(double prixChambre) {
        this.prixChambre = prixChambre;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public List<OptionHotelDTO> getOptionHotels() {
        return optionHotels;
    }

    public void setOptionHotels(List<OptionHotelDTO> optionHotels) {
        this.optionHotels = optionHotels;
    }

    public ReservationDTO getReservation() {
        return reservation;
    }

    public void setReservation(ReservationDTO reservation) {
        this.reservation = reservation;
    }

    public ChambreDTO getChambre() {
        return chambre;
    }

    public void setChambre(ChambreDTO chambre) {
        this.chambre = chambre;
    }

}
