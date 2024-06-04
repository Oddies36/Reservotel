package be.reservotel.reservotel.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import be.reservotel.reservotel.Model.Adresse;
import jakarta.transaction.Transactional;

public interface AdresseRepository extends JpaRepository<Adresse, Long>{
    
    @Query("SELECT rue FROM Adresse WHERE numero = :numero")
    List<String> findRueByNumero(@Param("numero") String numero);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Adresse (boite, code_postal, numero, pays, rue, ville) VALUES (:boite, :code_postal, :numero, :pays, :rue, :ville) RETURNING id_Adresse", nativeQuery = true)
    Long insertAdresse(@Param("boite") String boite, @Param("code_postal") String code_postal, @Param("numero") String numero, @Param("pays") String pays, @Param("rue") String rue, @Param("ville") String ville);
}
