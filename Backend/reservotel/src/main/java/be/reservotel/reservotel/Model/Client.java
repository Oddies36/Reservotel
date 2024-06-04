package be.reservotel.reservotel.Model;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    private String password;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String email;
    private String numeroTelephone;
    private int pointsFidelite;
    private String role;

    @ManyToOne
    @JoinColumn(name = "idAdresse")
    private Adresse adresse;

    @OneToMany(mappedBy = "client")
    private Set<Reservation> reservations;


    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public int getPointsFidelite() {
        return pointsFidelite;
    }

    public void setPointsFidelite(int pointsFidelite) {
        this.pointsFidelite = pointsFidelite;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    //Constructeur par défaut, nécessaire pour JPA
    public Client() {
    }


    public Client(Long idClient, String nom, String prenom, LocalDate dateNaissance, String email, String numeroTelephone, int pointsFidelite, Adresse adresse, String role) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.numeroTelephone = numeroTelephone;
        this.pointsFidelite = pointsFidelite;
        this.adresse = adresse;
        this.role = role;
    }

    public Client(String password, String nom, String prenom, LocalDate dateNaissance, String email, String numeroTelephone, int pointsFidelite, Adresse adresse, String role) {
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.numeroTelephone = numeroTelephone;
        this.pointsFidelite = pointsFidelite;
        this.adresse = adresse;
        this.role = role;
    }
}
