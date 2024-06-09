package be.reservotel.reservotel.DTO;

public class ChambreDTO {
    
    private Long idChambre;
    private String numeroChambre;
    private int etage;
    private int nombrePersonnes;
    private boolean estDisponible;
    private String photo;
    private double prixBase;
    private Long compte;
    private HotelDTO hotel;

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

    public boolean getEstDisponible() {
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

    public Long getCompte() {
        return compte;
    }

    public void setCompte(Long compte) {
        this.compte = compte;
    }

    public HotelDTO getHotel() {
        return hotel;
    }

    public void setHotel(HotelDTO hotel) {
        this.hotel = hotel;
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

    public ChambreDTO(int nombrePersonnes, String photo, double prixBase, Long compte) {
        this.nombrePersonnes = nombrePersonnes;
        this.photo = photo;
        this.prixBase = prixBase;
        this.compte = compte;
    }

    public ChambreDTO(int nombrePersonnes, Long compte) {
        this.nombrePersonnes = nombrePersonnes;
        this.compte = compte;
    }
}
