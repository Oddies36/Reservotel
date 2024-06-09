package be.reservotel.reservotel.DTO;

import java.util.List;

public class CustomPannierDTO {
    
    private Long idHotel;
    private String userEmail;
    private String dateArriveURL;
    private String dateDepartURL;
    private boolean choixPointFidelite;
    private int nombrePersonnesURL;
    private List<SelectionChambreDTO> pannier;

    public Long getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getdateArriveURL() {
        return dateArriveURL;
    }

    public void setdateArriveURL(String dateArriveURL) {
        this.dateArriveURL = dateArriveURL;
    }

    public String getDateDepartURL() {
        return dateDepartURL;
    }

    public void setDateDepartURL(String dateDepartURL) {
        this.dateDepartURL = dateDepartURL;
    }

    public boolean getChoixPointFidelite() {
        return choixPointFidelite;
    }

    public void setChoixPointFidelite(boolean choixPointFidelite) {
        this.choixPointFidelite = choixPointFidelite;
    }

    public List<SelectionChambreDTO> getPannier() {
        return pannier;
    }

    public void setPannier(List<SelectionChambreDTO> pannier) {
        this.pannier = pannier;
    }

    public int getNombrePersonnesURL() {
        return nombrePersonnesURL;
    }

    public void setNombrePersonnesURL(int nombrePersonnesURL) {
        this.nombrePersonnesURL = nombrePersonnesURL;
    }
}
