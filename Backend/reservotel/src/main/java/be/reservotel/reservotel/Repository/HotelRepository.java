package be.reservotel.reservotel.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import be.reservotel.reservotel.DTO.HotelDTO;
import be.reservotel.reservotel.DTO.OptionDTO;
import be.reservotel.reservotel.Model.Equipement;
import be.reservotel.reservotel.Model.Hotel;
import be.reservotel.reservotel.Model.Option;

public interface HotelRepository extends JpaRepository<Hotel, Long>{
    
    @Query("SELECT DISTINCT adr.pays FROM Hotel AS hot INNER JOIN hot.adresse AS adr")
    List<String> getHotelPays();

    @Query("SELECT DISTINCT adr.ville FROM Hotel AS hot INNER JOIN hot.adresse AS adr WHERE adr.pays = ?1")
    List<String> getHotelVilles(String pays);

    // Avec new, on demande a JPA de creer un objet avec ce constructeur bien precis du DTO
    @Query("SELECT new be.reservotel.reservotel.DTO.HotelDTO(hot.idHotel, hot.nomHotel, hot.etoiles, hot.descriptionHotel, hot.prixChambreMinimum, hot.photoHotel) FROM Hotel AS hot INNER JOIN hot.adresse AS adr WHERE adr.pays = ?1 AND adr.ville = ?2")
    List<HotelDTO> getHotels(String pays, String ville);

    @Query("SELECT new be.reservotel.reservotel.DTO.HotelDTO(hot.idHotel, hot.nomHotel, hot.etoiles, hot.descriptionHotel, hot.prixChambreMinimum, hot.photoHotel) FROM Hotel AS hot INNER JOIN hot.adresse AS adr WHERE adr.pays = ?1 AND adr.ville = ?2 AND hot.prixChambreMinimum <= ?3")
    List<HotelDTO> getHotelsWithBudget(String pays, String ville, double budget);

    @Query("SELECT new be.reservotel.reservotel.DTO.HotelDTO(hot.idHotel, hot.nomHotel, hot.etoiles, hot.descriptionHotel, hot.prixChambreMinimum, hot.photoHotel, hot.nombreChambres, hot.contactTelephone, hot.contactEmail, hot.adresse) FROM Hotel hot WHERE hot.idHotel = :idHotel")
    HotelDTO getHotelById(Long idHotel);

    @Query("SELECT hot.equipements FROM Hotel hot WHERE hot.idHotel = :idHotel")
    List<Equipement> getEquipementsByIdHotel(Long idHotel);

    @Query("SELECT new be.reservotel.reservotel.DTO.OptionDTO(o.option.idOption, o.option.nomOption, o.prixOption) FROM OptionHotel o WHERE o.hotel.idHotel = :idHotel")
    List<OptionDTO> getOptionsByIdHotel(Long idHotel);
}
