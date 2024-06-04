package be.reservotel.reservotel.DTO;

import java.util.List;

import be.reservotel.reservotel.Model.Adresse;
import be.reservotel.reservotel.Model.Equipement;
import be.reservotel.reservotel.Model.Option;

public class HotelDTO {

    private Long idHotel;
    private String nomHotel;
    private int etoiles;
    private String descriptionHotel;
    private double prixChambreMinimum;
    private String photoHotel;
    private int nombreChambres;
    private String contactTelephone;
    private String contactEmail;
    private Adresse adresse;
    private List<Equipement> equipements;
    private List<OptionDTO> options;


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

    public String getPhotoHotel() {
        return photoHotel;
    }

    public void setPhotoHotel(String photoHotel) {
        this.photoHotel = photoHotel;
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

    public List<Equipement> getEquipements() {
        return equipements;
    }

    public void setEquipements(List<Equipement> equipements) {
        this.equipements = equipements;
    }

    public List<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDTO> options) {
        this.options = options;
    }

    public HotelDTO(Long idHotel, String nomHotel, int etoiles, String descriptionHotel, double prixChambreMinimum, String photoHotel) {
        this.idHotel = idHotel;
        this.nomHotel = nomHotel;
        this.etoiles = etoiles;
        this.descriptionHotel = descriptionHotel;
        this.prixChambreMinimum = prixChambreMinimum;
        this.photoHotel = photoHotel;
    }

    public HotelDTO(Long idHotel, String nomHotel, int etoiles, String descriptionHotel, double prixChambreMinimum, String photoHotel, int nombreChambres, String contactTelephone, String contactEmail, Adresse adresse) {
        this.idHotel = idHotel;
        this.nomHotel = nomHotel;
        this.etoiles = etoiles;
        this.descriptionHotel = descriptionHotel;
        this.prixChambreMinimum = prixChambreMinimum;
        this.photoHotel = photoHotel;
        this.nombreChambres = nombreChambres;
        this.contactTelephone = contactTelephone;
        this.contactEmail = contactEmail;
        this.adresse = adresse;
    }

    
}
