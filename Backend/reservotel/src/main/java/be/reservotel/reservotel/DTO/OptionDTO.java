package be.reservotel.reservotel.DTO;

public class OptionDTO {
    private Long idOption;
    private String nomOption;
    private double prixOption;

    
    public Long getIdOption() {
        return idOption;
    }

    public void setIdOption(Long idOption) {
        this.idOption = idOption;
    }

    public String getNomOption() {
        return nomOption;
    }
    
    public void setNomOption(String nomOption) {
        this.nomOption = nomOption;
    }
    
    public double getPrixOption() {
        return prixOption;
    }
    
    public void setPrixOption(double prixOption) {
        this.prixOption = prixOption;
    }

    public OptionDTO() {
    }

    public OptionDTO(Long idOption, String nomOption) {
        this.idOption = idOption;
        this.nomOption = nomOption;
    }

    public OptionDTO(Long idOption, String nomOption, double prixOption) {
        this.idOption = idOption;
        this.nomOption = nomOption;
        this.prixOption = prixOption;
    }
}