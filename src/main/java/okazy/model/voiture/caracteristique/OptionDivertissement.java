package okazy.model.voiture.caracteristique;

import jakarta.persistence.*;

@Entity
@Table( name = "optiondivertisement")
public class OptionDivertissement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;

    public OptionDivertissement(){}

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
