package okazy.model.voiture.caracteristique;

import jakarta.persistence.*;

@Entity
@Table( name = "systemdirection")
public class SystemDirection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;

    public SystemDirection(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
