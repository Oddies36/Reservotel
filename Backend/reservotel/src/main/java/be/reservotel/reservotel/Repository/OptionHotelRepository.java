package be.reservotel.reservotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import be.reservotel.reservotel.Model.OptionHotel;
import jakarta.transaction.Transactional;

public interface OptionHotelRepository extends JpaRepository<OptionHotel, Long>{
    
    @Query("SELECT o.prixOption FROM OptionHotel o WHERE o.hotel.idHotel = :idHotel AND o.option.idOption = :idOption")
    double getPrixOption(Long idHotel, Long idOption);

    @Query("SELECT o.id_option_hotel FROM OptionHotel o WHERE o.hotel.idHotel = :idHotel AND o.option.idOption = :idOption")
    Long getIdOptionHotel(Long idHotel, Long idOption);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO details_reservation_option_hotel (id_details_reservation, id_option_hotel) VALUES (:idOptionHotel, :idDetailsReservation)", nativeQuery = true)
    void insertDetailsReservationOptionHotel(@Param("idOptionHotel") Long idOptionHotel, @Param("idDetailsReservation") Long idDetailsReservation);

    @Query("SELECT o.prixOption FROM OptionHotel o WHERE o.id_option_hotel = :idOptionHotel")
    double getPrixOptionHotel(Long idOptionHotel);
}
