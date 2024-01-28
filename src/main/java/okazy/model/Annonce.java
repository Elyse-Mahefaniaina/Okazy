package okazy.model;

import jakarta.persistence.*;
import okazy.model.user.Utilisateur;
import okazy.model.voiture.Voiture;
import okazy.model.voiture.caracteristique.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Annonce {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private String titre;
    private String description;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idutilisateur", referencedColumnName = "id")
    private Utilisateur utilisateur;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idvoiture", referencedColumnName = "id")
    private Voiture voiture;
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
    @JoinColumn(name = "idsystemedirection", referencedColumnName = "id")
    private SystemDirection systemDirection;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idsystemefreinage", referencedColumnName = "id")
    private SystemFreinage systemeFreinage;
    @ManyToMany()
    @JoinTable(
            name = "annonce_optiondivertissement",
            joinColumns = @JoinColumn(name = "idannonce") ,
            inverseJoinColumns = @JoinColumn(name = "idoptiondivertissement")
    )
    private Set<OptionDivertissement> optionDivertisements = new HashSet<>();
    @ManyToMany()
    @JoinTable(
            name = "annonce_optionsecurite",
            joinColumns = @JoinColumn(name = "idannonce") ,
            inverseJoinColumns = @JoinColumn(name = "idoptionsecutite")
    )
    private Set<OptionSecurite> optionSecurites = new HashSet<>();
    @Column(name = "couleurinterieur")
    private String couleurInterieur;
    @Column(name = "couleurexterieur")
    private String couleurExterieur;
    private Double prix;
    private int state;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "idannonce")
    private Set<AnnoncePhoto> photos = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
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

    public SystemDirection getSystemDirection() {
        return systemDirection;
    }

    public void setSystemDirection(SystemDirection systemDirection) {
        this.systemDirection = systemDirection;
    }

    public SystemFreinage getSystemeFreinage() {
        return systemeFreinage;
    }

    public void setSystemeFreinage(SystemFreinage systemeFreinage) {
        this.systemeFreinage = systemeFreinage;
    }

    public Set<OptionDivertissement> getOptionDivertisements() {
        return optionDivertisements;
    }

    public void setOptionDivertisements(Set<OptionDivertissement> optionDivertisements) {
        this.optionDivertisements = optionDivertisements;
    }

    public Set<OptionSecurite> getOptionSecurites() {
        return optionSecurites;
    }

    public void setOptionSecurites(Set<OptionSecurite> optionSecurites) {
        this.optionSecurites = optionSecurites;
    }

    public String getCouleurInterieur() {
        return couleurInterieur;
    }

    public void setCouleurInterieur(String couleurInterieur) {
        this.couleurInterieur = couleurInterieur;
    }

    public String getCouleurExterieur() {
        return couleurExterieur;
    }

    public void setCouleurExterieur(String couleurExterieur) {
        this.couleurExterieur = couleurExterieur;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Set<AnnoncePhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<AnnoncePhoto> photos) {
        this.photos = photos;
    }
}
