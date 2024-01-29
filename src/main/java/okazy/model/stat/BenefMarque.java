package okazy.model.stat;

import jakarta.persistence.*;
import okazy.model.voiture.caracteristique.Marque;

@Entity
@Table(name = "v_benef_marque")
public class BenefMarque {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "idmarque", referencedColumnName = "id")
    private Marque marque;
    private int annee;
    private double benef;

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
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
}
