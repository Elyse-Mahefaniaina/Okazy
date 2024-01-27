package okazy.model.voiture.caracteristique;

import jakarta.persistence.*;

@Entity
@Table( name = "systemefreinage")
public class SystemFreinage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;

    public SystemFreinage(){}

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
