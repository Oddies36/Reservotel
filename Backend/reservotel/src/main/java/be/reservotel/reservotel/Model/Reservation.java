package be.reservotel.reservotel.Model;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    private String statutReservation;
    private LocalDate dateArrive;
    private LocalDate dateDepart;
    private double prixTotalReservation;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;

    @OneToMany(mappedBy = "reservation")
    private Set<DetailsReservation> detailsReservations;

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

    public Reservation() {
    }

    public Reservation(Long idReservation, String statutReservation, LocalDate dateArrive, LocalDate dateDepart, double prixTotalReservation) {
        this.idReservation = idReservation;
        this.statutReservation = statutReservation;
        this.dateArrive = dateArrive;
        this.dateDepart = dateDepart;
        this.prixTotalReservation = prixTotalReservation;
    }
}
