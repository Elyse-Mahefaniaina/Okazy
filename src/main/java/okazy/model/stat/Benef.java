package okazy.model.stat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import okazy.model.voiture.caracteristique.Marque;

@Entity
@Table( name = "v_benef_marque")
public class Benef {
    @Id
    private int idmarque;
    private int annee;
    private double benef;
    @Transient
    private Marque marque;

    public int getIdmarque() {
        return idmarque;
    }

    public void setIdmarque(int idmarque) {
        this.idmarque = idmarque;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public double getBenef() {
        return benef;
    }

    public void setBenef(double benef) {
        this.benef = benef;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }
}
