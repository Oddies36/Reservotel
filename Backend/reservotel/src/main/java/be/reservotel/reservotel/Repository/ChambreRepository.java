package be.reservotel.reservotel.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import be.reservotel.reservotel.DTO.ChambreDTO;
import be.reservotel.reservotel.Model.Chambre;

public interface ChambreRepository extends JpaRepository<Chambre, Long>{
    

    @Query("SELECT new be.reservotel.reservotel.DTO.ChambreDTO(ch.nombrePersonnes, ch.photo, ch.prixBase, COUNT(ch)) FROM Chambre ch WHERE ch.hotel.idHotel = :idHotel GROUP BY ch.nombrePersonnes, ch.photo, ch.prixBase")
    List<ChambreDTO> getChambresByIdHotel(Long idHotel);

    @Query("SELECT new be.reservotel.reservotel.DTO.ChambreDTO(ch.nombrePersonnes, COUNT(ch)) FROM Chambre ch WHERE ch.hotel.idHotel = :idHotel AND ch.estDisponible = true GROUP BY ch.nombrePersonnes")
    List<ChambreDTO> getChambresDispoByIdHotel(Long idHotel);


    @Query("SELECT c FROM Chambre c WHERE c.nombrePersonnes = :nombrePersonnes AND c.hotel.idHotel = :idHotel AND c.estDisponible = true")
    List<Chambre> getDistinctChambre(@Param("nombrePersonnes") int nombrePersonnes, @Param("idHotel") Long idHotel, Pageable pageable);

    @Query("SELECT c.prixBase FROM Chambre c WHERE c.idChambre = :idChambre")
    double getPrixChambreById(Long idChambre);

    @Query("SELECT c FROM Chambre c WHERE c.idChambre = :idChambre")
    Chambre getChambreById(Long idChambre);

    @Modifying
    @Transactional
    @Query("UPDATE Chambre c SET c.estDisponible = false WHERE c.idChambre = :idChambre")
    void setChambreIndisponible(@Param("idChambre") Long idChambre);
}