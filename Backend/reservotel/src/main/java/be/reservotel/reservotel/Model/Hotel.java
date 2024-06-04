package be.reservotel.reservotel.Model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Hotel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHotel;
    private String nomHotel;
    private int etoiles;
    private String descriptionHotel;
    private double prixChambreMinimum;
    private int nombreChambres;
    private String contactTelephone;
    private String contactEmail;
    private String photoHotel;

    @OneToOne(optional = false)
    @JoinColumn(name= "idAdresse", nullable = false)
    @JsonManagedReference
    private Adresse adresse;

    @ManyToMany
    @JoinTable(
        name = "EquipementHotel",
        joinColumns = @JoinColumn(name = "idHotel"),
        inverseJoinColumns = @JoinColumn(name = "idEquipement")
    )
    private Set<Equipement> equipements;

    @OneToMany(mappedBy = "hotel")
    private Set<OptionHotel> optionHotel;

    @OneToMany(mappedBy = "hotel")
    private Set<Chambre> chambres;

    public Long getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
    }

    public String getNomHotel() {
        return nomHotel;
    }

    public void setNomHotel(String nomHotel) {
        this.nomHotel = nomHotel;
    }

    public int getEtoiles() {
        return etoiles;
    }

    public void setEtoiles(int etoiles) {
        this.etoiles = etoiles;
    }

    public String getDescriptionHotel() {
        return descriptionHotel;
    }

    public void setDescriptionHotel(String descriptionHotel) {
        this.descriptionHotel = descriptionHotel;
    }

    public double getPrixChambreMinimum() {
        return prixChambreMinimum;
    }

    public void setPrixChambreMinimum(double prixChambreMinimum) {
        this.prixChambreMinimum = prixChambreMinimum;
    }

    public int getNombreChambres() {
        return nombreChambres;
    }

    public void setNombreChambres(int nombreChambres) {
        this.nombreChambres = nombreChambres;
    }

    public String getContactTelephone() {
        return contactTelephone;
    }

    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Adresse getAdresse() {
        return adresse;
    }
    
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getPhotoHotel() {
        return photoHotel;
    }

    public void setPhotoHotel(String photoHotel) {
        this.photoHotel = photoHotel;
    }

    public Hotel() {
    }

    public Hotel(Long idHotel, String nomHotel, int etoiles, String descriptionHotel, double prixChambreMinimum,
            int nombreChambres, String contactTelephone, String contactEmail, Adresse adresse, String photoHotel) {
        this.idHotel = idHotel;
        this.nomHotel = nomHotel;
        this.etoiles = etoiles;
        this.descriptionHotel = descriptionHotel;
        this.prixChambreMinimum = prixChambreMinimum;
        this.nombreChambres = nombreChambres;
        this.contactTelephone = contactTelephone;
        this.contactEmail = contactEmail;
        this.adresse = adresse;
        this.photoHotel = photoHotel;
    }
}
