package be.reservotel.reservotel.DTO;

public class ChambreDTO {
    
    private Long idChambre;
    private String numeroChambre;
    private int etage;
    private int nombrePersonnes;
    private boolean estDisponible;
    private String photo;
    private double prixBase;

    public Long getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(Long idChambre) {
        this.idChambre = idChambre;
    }

    public String getNumeroChambre() {
        return numeroChambre;
    }

    public void setNumeroChambre(String numeroChambre) {
        this.numeroChambre = numeroChambre;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public int getNombrePersonnes() {
        return nombrePersonnes;
    }

    public void setNombrePersonnes(int nombrePersonnes) {
        this.nombrePersonnes = nombrePersonnes;
    }

    public boolean isEstDisponible() {
        return estDisponible;
    }

    public void setEstDisponible(boolean estDisponible) {
        this.estDisponible = estDisponible;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getPrixBase() {
        return prixBase;
    }

    public void setPrixBase(double prixBase) {
        this.prixBase = prixBase;
    }

    public ChambreDTO() {
    }

    public ChambreDTO(Long idChambre, String numeroChambre, int etage, int nombrePersonnes, boolean estDisponible, String photo, double prixBase) {
        this.idChambre = idChambre;
        this.numeroChambre = numeroChambre;
        this.etage = etage;
        this.nombrePersonnes = nombrePersonnes;
        this.estDisponible = estDisponible;
        this.photo = photo;
        this.prixBase = prixBase;
    }   
}
