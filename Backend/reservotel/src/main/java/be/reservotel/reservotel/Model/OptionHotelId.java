package be.reservotel.reservotel.Model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class OptionHotelId implements Serializable{
    private Long idHotel;
    private Long idOption;

    public Long getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
    }

    public Long getIdOption() {
        return idOption;
    }

    public void setIdOption(Long idOption) {
        this.idOption = idOption;
    }

    public OptionHotelId() {
    }

    public OptionHotelId(Long idHotel, Long idOption) {
        this.idHotel = idHotel;
        this.idOption = idOption;
    }
}
