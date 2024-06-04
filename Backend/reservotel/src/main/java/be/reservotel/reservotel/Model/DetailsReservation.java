package be.reservotel.reservotel.Model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class DetailsReservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetailsReservation;
    private double prixChambre;
    private double prixTotal;

    @ManyToMany(mappedBy = "detailsReservations")
    private Set<OptionHotel> optionHotels;

    @ManyToOne
    @JoinColumn(name = "idReservation")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "idChambre")
    private Chambre chambre;

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

    //Constructeur par défaut, nécessaire pour JPA
    public DetailsReservation() {
    }

    public DetailsReservation(Long idDetailsReservation, double prixChambre, double prixTotal) {
        this.idDetailsReservation = idDetailsReservation;
        this.prixChambre = prixChambre;
        this.prixTotal = prixTotal;
    }
}
