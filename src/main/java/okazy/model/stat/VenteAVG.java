package okazy.model.stat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import java.util.HashMap;

@Entity(name = "v_vente_avg_month")
public class VenteAVG {
    @Id
    private int mois;

    private double moyenne;

    @Transient
    public String str_mois;

    public String getMois(int mois) {
        HashMap<Integer, String> dico = new HashMap<>();
        dico.put(1, "JAN");
        dico.put(2, "FEB");
        dico.put(3, "MAR");
        dico.put(4, "APR");
        dico.put(5, "MAI");
        dico.put(6, "JUNE");
        dico.put(7, "JULY");
        dico.put(8, "AUG");
        dico.put(9, "SEPT");
        dico.put(10, "OCT");
        dico.put(11, "NOV");
        dico.put(12, "DEC");

        return dico.get(mois);
    }

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

    public String getStr_mois() {
        return str_mois;
    }

    public void setStr_mois(String str_mois) {
        this.str_mois = str_mois;
    }
}
