package okazy.model.stat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "v_vente_avg_month")
public class VenteAVG {
    @Id
    private int mois;
    private double moyenne;

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }
}
