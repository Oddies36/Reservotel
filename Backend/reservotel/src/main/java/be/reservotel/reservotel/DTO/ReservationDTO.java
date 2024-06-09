package be.reservotel.reservotel.DTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class ReservationDTO {
    private Long idReservation;
    private String statutReservation;
    private LocalDate dateArrive;
    private LocalDate dateDepart;
    private double prixTotalReservation;
    private ClientDTO client;
    private List<DetailsReservationDTO> detailsReservations;
    private int totalReservations;

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public String getStatutReservation() {
        return statutReservation;
    }

    public void setStatutReservation(String statutReservation) {
        this.statutReservation = statutReservation;
    }

    public LocalDate getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(LocalDate dateArrive) {
        this.dateArrive = dateArrive;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public double getPrixTotalReservation() {
        return prixTotalReservation;
    }

    public void setPrixTotalReservation(double prixTotalReservation) {
        this.prixTotalReservation = prixTotalReservation;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public List<DetailsReservationDTO> getDetailsReservations() {
        return detailsReservations;
    }

    public void setDetailsReservations(List<DetailsReservationDTO> detailsReservations) {
        this.detailsReservations = detailsReservations;
    }

    public int getTotalReservations() {
        return totalReservations;
    }

    public void setTotalReservations(int totalReservations) {
        this.totalReservations = totalReservations;
    }
}
