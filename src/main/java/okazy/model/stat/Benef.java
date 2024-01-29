package okazy.model.stat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import okazy.model.voiture.caracteristique.Marque;

@Entity
@Table( name = "v_benef_marque_avg")
public class Benef {
    @Id
    private int idmarque;
    private int annee;
    private double benef;
    private double total;
    @Transient
    private Marque marque;
    @Transient
    public double pourcentage;

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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }
}
