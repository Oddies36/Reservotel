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
public class OptionHotel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_option_hotel;
    private double prixOption;

    @ManyToOne
    @JoinColumn(name = "id_Hotel")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "id_Option")
    private Option option;

    @ManyToMany(mappedBy = "optionHotels")
    private Set<DetailsReservation> detailsReservations;


    public Long getid_option_hotel() {
        return id_option_hotel;
    }

    public void setid_option_hotel(Long id_option_hotel) {
        this.id_option_hotel = id_option_hotel;
    }

    public double getPrixOption() {
        return prixOption;
    }

    public void setPrixOption(double prixOption) {
        this.prixOption = prixOption;
    }

    public OptionHotel(Long id_option_hotel, double prixOption) {
        this.id_option_hotel = id_option_hotel;
        this.prixOption = prixOption;
    }

    public OptionHotel() {
    }
}
