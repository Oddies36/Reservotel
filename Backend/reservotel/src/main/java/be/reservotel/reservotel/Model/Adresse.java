package be.reservotel.reservotel.Model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdresse;
    private String rue;
    private String numero;
    private String boite;
    private String ville;
    private String codePostal;
    private String pays;

    @OneToOne(mappedBy = "adresse")
    @JsonBackReference
    private Hotel hotel;

    @OneToMany(mappedBy = "adresse")
    private Set<Client> client;

    public Long getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(Long idAdresse) {
        this.idAdresse = idAdresse;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBoite() {
        return boite;
    }

    public void setBoite(String boite) {
        this.boite = boite;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    //Constructeur par défaut, nécessaire pour JPA
    public Adresse() {
    }

    public Adresse(Long idAdresse, String rue, String numero, String boite, String ville, String codePostal, String pays) {
        this.idAdresse = idAdresse;
        this.rue = rue;
        this.numero = numero;
        this.boite = boite;
        this.ville = ville;
        this.codePostal = codePostal;
        this.pays = pays;
    }

    public Adresse(String rue, String numero, String boite, String ville, String codePostal, String pays) {
        this.rue = rue;
        this.numero = numero;
        this.boite = boite;
        this.ville = ville;
        this.codePostal = codePostal;
        this.pays = pays;
    }
}
