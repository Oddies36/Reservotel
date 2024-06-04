package be.reservotel.reservotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import be.reservotel.reservotel.Model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
    
    @Query("UPDATE Client SET nom = :nom WHERE id = :id")
    void updateNomById(Long id, String nom);

    @Query("SELECT password FROM Client WHERE email = :email")
    String getPasswordByEmail(String email);

    @Query("SELECT count(email) FROM Client WHERE email = :email")
    int getEmail(String email);
}
