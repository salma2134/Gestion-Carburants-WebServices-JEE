
package ma.fstt.gestioncarburants.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import ma.fstt.gestioncarburants.entities.Carburant;

@Stateless
public class CarburantDAO {

    @PersistenceContext(unitName = "GestionCarburantsPU")
    private EntityManager em;

    // Ajouter un carburant
    public void ajouter(Carburant carburant) {
        em.persist(carburant);
    }

    // Afficher tous les carburants
    public List<Carburant> afficherTout() {
        return em.createQuery(
            "SELECT c FROM Carburant c", Carburant.class
        ).getResultList();
    }

    // Rechercher un carburant
    public Carburant rechercher(int id) {
        return em.find(Carburant.class, id);
    }

    // Modifier un carburant
    public void modifier(Carburant carburant) {
        em.merge(carburant);
    }

    // Supprimer un carburant
    public void supprimer(int id) {
        Carburant carburant = em.find(Carburant.class, id);

        if (carburant != null) {
            em.remove(carburant);
        }
    }
}
