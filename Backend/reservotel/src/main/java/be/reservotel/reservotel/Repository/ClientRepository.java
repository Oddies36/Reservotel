package be.reservotel.reservotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import be.reservotel.reservotel.Model.Client;
import jakarta.transaction.Transactional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE c.email = :email")
    Client findByEmail(String email);

    @Query("UPDATE Client SET nom = :nom WHERE id = :id")
    void updateNomById(Long id, String nom);

    @Query("SELECT password FROM Client WHERE email = :email")
    String getPasswordByEmail(String email);

    @Query(value = "SELECT COUNT(email) FROM client WHERE email = :email", nativeQuery = true)
    int getEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Client c SET c.pointsFidelite = :pointsFidelite WHERE c.idClient = :idClient")
    void addPointsFidelite(@Param("idClient") Long idClient, @Param("pointsFidelite") double pointsFidelite);

    @Modifying
    @Transactional
    @Query("UPDATE Client c SET c.pointsFidelite = 0 WHERE c.idClient = :idClient")
    void resetPointsFidelite(Long idClient);

    @Query("SELECT pointsFidelite FROM Client WHERE idClient = :idClient")
    int getPointsFideliteByIdClient(Long idClient);
}
