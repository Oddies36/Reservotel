package be.reservotel.reservotel.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import be.reservotel.reservotel.DTO.ChambreDTO;
import be.reservotel.reservotel.Model.Chambre;

public interface ChambreRepository extends JpaRepository<Chambre, Long>{
    

    @Query("SELECT new be.reservotel.reservotel.DTO.ChambreDTO(ch.idChambre, ch.numeroChambre, ch.etage, ch.nombrePersonnes, ch.estDisponible, ch.photo, ch.prixBase) FROM Chambre ch WHERE ch.hotel.idHotel = :idHotel")
    List<ChambreDTO> getChambresByIdHotel(Long idHotel);
}