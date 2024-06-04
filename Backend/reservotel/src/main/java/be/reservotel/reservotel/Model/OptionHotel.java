package be.reservotel.reservotel.Model;

import java.util.Set;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class OptionHotel {
    
    @EmbeddedId
    private OptionHotelId id;
    private double prixOption;

    @ManyToOne
    @MapsId("idHotel")
    @JoinColumn(name = "id_Hotel")
    private Hotel hotel;

    @ManyToOne
    @MapsId("idOption")
    @JoinColumn(name = "id_Option")
    private Option option;

    @ManyToMany
    @JoinTable(
        name = "DetailsReservation_OptionHotel",
        joinColumns = {
            @JoinColumn(name = "id_Option", referencedColumnName = "id_Option"),
            @JoinColumn(name = "id_Hotel", referencedColumnName = "id_Hotel")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "idDetailsReservation")
        }
    )
    private Set<DetailsReservation> detailsReservations;


    public OptionHotelId getId() {
        return id;
    }

    public void setId(OptionHotelId id) {
        this.id = id;
    }

    public double getPrixOption() {
        return prixOption;
    }

    public void setPrixOption(double prixOption) {
        this.prixOption = prixOption;
    }

    public OptionHotel(OptionHotelId id, double prixOption) {
        this.id = id;
        this.prixOption = prixOption;
    }

    public OptionHotel() {
    }
}
