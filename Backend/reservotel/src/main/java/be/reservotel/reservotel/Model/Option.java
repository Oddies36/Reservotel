package be.reservotel.reservotel.Model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOption;
    private String nomOption;

    @OneToMany(mappedBy = "option")
    private Set<OptionHotel> optionHotel;

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

    //Constructeur par défaut, nécessaire pour JPA
    public Option() {
    }

    public Option(Long idOption, String nomOption) {
        this.idOption = idOption;
        this.nomOption = nomOption;
    }

}
