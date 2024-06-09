package be.reservotel.reservotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.reservotel.reservotel.Model.DetailsReservation;

public interface DetailsReservationRepository extends JpaRepository<DetailsReservation, Long>{
    
    
}
