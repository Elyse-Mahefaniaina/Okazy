package okazy.model.voiture;

import jakarta.persistence.*;

import okazy.model.voiture.caracteristique.*;

import java.sql.Date;
import java.util.List;

@Entity
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idmarque", referencedColumnName = "id")
    private Marque marque;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idmodel", referencedColumnName = "id")
    private Model model;
    private double puissancefiscale;
    private double cylindre;
    private double puissancemoteur;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idcassis", referencedColumnName = "id")
    private Cassis cassis;
    private int nombreporte;
    private int nombreplace;
    private Date miseencirculation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public double getPuissancefiscale() {
        return puissancefiscale;
    }

    public void setPuissancefiscale(double puissancefiscale) {
        this.puissancefiscale = puissancefiscale;
    }

    public double getCylindre() {
        return cylindre;
    }

    public void setCylindre(double cylindre) {
        this.cylindre = cylindre;
    }

    public double getPuissancemoteur() {
        return puissancemoteur;
    }

    public void setPuissancemoteur(double puissancemoteur) {
        this.puissancemoteur = puissancemoteur;
    }

    public Cassis getCassis() {
        return cassis;
    }

    public void setCassis(Cassis cassis) {
        this.cassis = cassis;
    }

    public int getNombreporte() {
        return nombreporte;
    }

    public void setNombreporte(int nombreporte) {
        this.nombreporte = nombreporte;
    }

    public int getNombreplace() {
        return nombreplace;
    }

    public void setNombreplace(int nombreplace) {
        this.nombreplace = nombreplace;
    }

    public Date getMiseencirculation() {
        return miseencirculation;
    }

    public void setMiseencirculation(Date miseencirculation) {
        this.miseencirculation = miseencirculation;
    }
}
