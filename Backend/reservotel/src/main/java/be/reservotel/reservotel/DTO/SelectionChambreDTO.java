package be.reservotel.reservotel.DTO;

import java.util.List;

public class SelectionChambreDTO {
    private int nombrePersonnes;
    private double prixBase;
    private double prixTotalOptions;
    private double prixTotal;
    private List<Long> options;
    private List<String> nomOptions;


    public int getNombrePersonnes() {
        return nombrePersonnes;
    }

    public void setNombrePersonnes(int nombrePersonnes) {
        this.nombrePersonnes = nombrePersonnes;
    }

    public double getPrixBase() {
        return prixBase;
    }

    public void setPrixBase(double prixBase) {
        this.prixBase = prixBase;
    }

    public double getPrixTotalOptions() {
        return prixTotalOptions;
    }

    public void setPrixTotalOptions(double prixTotalOptions) {
        this.prixTotalOptions = prixTotalOptions;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public List<Long> getOptions() {
        return options;
    }


    public void setOptions(List<Long> options) {
        this.options = options;
    }

    public List<String> getNomOptions() {
        return nomOptions;
    }

    public void setNomOptions(List<String> nomOptions) {
        this.nomOptions = nomOptions;
    }
}
