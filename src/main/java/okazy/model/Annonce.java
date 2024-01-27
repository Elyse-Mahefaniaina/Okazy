package okazy.model;

import jakarta.persistence.*;
import okazy.model.user.Utilisateur;
import okazy.model.voiture.Voiture;

import java.sql.Date;
import java.util.List;

@Entity
public class Annonce {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private String titre;
    private String descritpion;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idutilisateur", referencedColumnName = "id")
    private Utilisateur utilisateur;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idvoiture", referencedColumnName = "id")
    private Voiture voiture;
    private String couleurInterieur;
    private String couleurExterieur;
    private Double prix;
    private int state;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idannonce")
    private List<AnnoncePhoto> photos;

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

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
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

    public List<AnnoncePhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<AnnoncePhoto> photos) {
        this.photos = photos;
    }
}
