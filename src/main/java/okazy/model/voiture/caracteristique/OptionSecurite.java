package okazy.model.voiture.caracteristique;

import jakarta.persistence.*;

@Entity
@Table( name = "optionsecurite")
public class OptionSecurite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;

    public OptionSecurite(){}

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
