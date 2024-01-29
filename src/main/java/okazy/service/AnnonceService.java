package okazy.service;

import okazy.model.Annonce;
import okazy.repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


@Service
public class AnnonceService {

    private final AnnonceRepository annonceRepository;

    @Autowired
    public AnnonceService(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }

    public List<Annonce> findAllFavoriteByUser(int id) {
        return this.annonceRepository.findAllFavorisUtilisateur(id);
    }

    public List<Annonce> findAdvenced(
                                        Double prixmin,
                                        Double prixmax,
                                        String boiteVitesse,
                                        String sourceEnergie,
                                        String suspension,
                                        String systemDirection,
                                        String systemeFreinage,
                                        String optionDivertisements,
                                        String optionSecurites,
                                        String marque,
                                        String model,
                                        Double puissancefiscale,
                                        Double cylindre,
                                        Double puissancemoteur,
                                        String cassis,
                                        Integer nombreporte,
                                        Integer nombreplace,
                                        Date miseencirculation,
                                        Date date ) {
        return this.annonceRepository.findAdvenced(prixmin, prixmax, boiteVitesse, sourceEnergie, suspension, systemDirection, systemeFreinage, optionDivertisements, optionSecurites, marque, model, puissancefiscale, cylindre, puissancemoteur, cassis, nombreporte, nombreplace, miseencirculation, date);
    }

    public List<Annonce> findAll() {
        return this.annonceRepository.findAll();
    }

    public List<Annonce> findAllValide() {
        return this.annonceRepository.findByState(11);
    }

    public List<Annonce> findAllNonValide() {
        return this.annonceRepository.findByState(1);
    }

    public List<Annonce> findAllVendu() {
        return this.annonceRepository.findByState(21);
    }


    public List<Annonce> findAllByUser(int iduser) {
        return this.annonceRepository.findByIdutilisateur(iduser);
    }

    public Optional<Annonce> findById(int id) {
        return this.annonceRepository.findById(id);
    }

    public Annonce save(Annonce annonce) {
        annonce.setDate(new Date(new java.util.Date().getTime()));
        annonce.setState(1);
        return this.annonceRepository.save(annonce);
    }

    public Annonce update(int id, Annonce annonce) {
        annonce.setId(id);
        return this.annonceRepository.save(annonce);
    }

    public Annonce validate(int id) throws Exception {
        Optional<Annonce> optionalAnnonce = this.findById(id);

        if (optionalAnnonce.isPresent()) {
            Annonce annonce = optionalAnnonce.get();
            if (annonce.getState() < 11 && annonce.getState() >= 1) {
                annonce.setState(11);
                return this.update(annonce.getId(), annonce);
            } else {
                throw new Exception("Mouvement Impossible");
            }
        } else {
            throw new Exception("Annonce Not Found");
        }
    }

    public Annonce vendre(int id) throws Exception {
        Optional<Annonce> optionalAnnonce = this.findById(id);

        if (optionalAnnonce.isPresent()) {
            Annonce annonce = optionalAnnonce.get();
            if (annonce.getState() < 21 && annonce.getState() >= 11) {
                annonce.setState(21);
                return this.update(annonce.getId(), annonce);
            } else {
                throw new Exception("Mouvement Impossible");
            }
        } else {
            throw new Exception("Annonce Not Found");
        }
    }

    public void delete(int id) {
        this.annonceRepository.deleteById(id);
    }
}
