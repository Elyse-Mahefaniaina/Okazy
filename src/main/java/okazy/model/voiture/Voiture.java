package okazy.model.voiture;

import jakarta.persistence.*;

import okazy.model.voiture.caracteristique.*;

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
    @JoinColumn(name = "idboitevitesse", referencedColumnName = "id")
    private BoiteVitesse boiteVitesse;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idsourceenergie", referencedColumnName = "id")
    private SourceEnergie sourceEnergie;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idsuspension", referencedColumnName = "id")
    private Suspension suspension;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idcassis", referencedColumnName = "id")
    private Cassis cassis;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idsystemedirection", referencedColumnName = "id")
    private SystemDirection systemDirection;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idsystemefreinage", referencedColumnName = "id")
    private SystemeFreinage systemeFreinage;
    @ManyToMany
    @JoinTable(
        name = "voiture_optiondivertissement",
        joinColumns = @JoinColumn(name = "idvoiture") ,
        inverseJoinColumns = @JoinColumn(name = "idoptiondivertissement")
    )
    private List<OptionDivertisement> optionDivertisements;
    @ManyToMany
    @JoinTable(
            name = "voiture_optionsecurite",
            joinColumns = @JoinColumn(name = "idvoiture") ,
            inverseJoinColumns = @JoinColumn(name = "idoptionsecutite")
    )
    private List<OptionSecurite> optionSecurites;
    private int nombreporte;
    private int nombreplace;

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

    public BoiteVitesse getBoiteVitesse() {
        return boiteVitesse;
    }

    public void setBoiteVitesse(BoiteVitesse boiteVitesse) {
        this.boiteVitesse = boiteVitesse;
    }

    public SourceEnergie getSourceEnergie() {
        return sourceEnergie;
    }

    public void setSourceEnergie(SourceEnergie sourceEnergie) {
        this.sourceEnergie = sourceEnergie;
    }

    public Suspension getSuspension() {
        return suspension;
    }

    public void setSuspension(Suspension suspension) {
        this.suspension = suspension;
    }

    public Cassis getCassis() {
        return cassis;
    }

    public void setCassis(Cassis cassis) {
        this.cassis = cassis;
    }

    public SystemDirection getSystemDirection() {
        return systemDirection;
    }

    public void setSystemDirection(SystemDirection systemDirection) {
        this.systemDirection = systemDirection;
    }

    public SystemeFreinage getSystemeFreinage() {
        return systemeFreinage;
    }

    public void setSystemeFreinage(SystemeFreinage systemeFreinage) {
        this.systemeFreinage = systemeFreinage;
    }

    public List<OptionDivertisement> getOptionDivertisements() {
        return optionDivertisements;
    }

    public void setOptionDivertisements(List<OptionDivertisement> optionDivertisements) {
        this.optionDivertisements = optionDivertisements;
    }

    public List<OptionSecurite> getOptionSecurites() {
        return optionSecurites;
    }

    public void setOptionSecurites(List<OptionSecurite> optionSecurites) {
        this.optionSecurites = optionSecurites;
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
}
