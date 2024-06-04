package be.reservotel.reservotel.Model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Equipement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;
    private String nomEquipement;
    private String descriptionEquipement;

    @ManyToMany(mappedBy = "equipements")
    private Set<Hotel> hotel;

    public Long getIdEquipement() {
        return idEquipement;
    }

    public void setIdEquipement(Long idEquipement) {
        this.idEquipement = idEquipement;
    }

    public String getNomEquipement() {
        return nomEquipement;
    }

    public void setNomEquipement(String nomEquipement) {
        this.nomEquipement = nomEquipement;
    }

    public String getDescriptionEquipement() {
        return descriptionEquipement;
    }

    public void setDescriptionEquipement(String descriptionEquipement) {
        this.descriptionEquipement = descriptionEquipement;
    }

    public Equipement() {
    }

    public Equipement(Long idEquipement, String nomEquipement, String descriptionEquipement) {
        this.idEquipement = idEquipement;
        this.nomEquipement = nomEquipement;
        this.descriptionEquipement = descriptionEquipement;
    }
}
