package be.reservotel.reservotel.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import be.reservotel.reservotel.Model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

@Query("SELECT r FROM Reservation r JOIN FETCH r.detailsReservations dr JOIN FETCH dr.chambre c JOIN FETCH c.hotel h WHERE r.client.email = :email")
List<Reservation> getReservationsByEmail(@Param("email") String email);
}
