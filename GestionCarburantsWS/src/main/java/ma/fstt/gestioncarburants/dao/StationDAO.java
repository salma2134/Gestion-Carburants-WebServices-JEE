
package ma.fstt.gestioncarburants.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import ma.fstt.gestioncarburants.entities.Station;

@Stateless
public class StationDAO {

    @PersistenceContext(unitName = "GestionCarburantsPU")
    private EntityManager em;

    // Ajouter une station
    public void ajouter(Station station) {
        em.persist(station);
    }

    // Afficher toutes les stations
    public List<Station> afficherTout() {
        return em.createQuery(
            "SELECT s FROM Station s", Station.class
        ).getResultList();
    }

    // Rechercher une station par son ID
    public Station rechercher(int id) {
        return em.find(Station.class, id);
    }

    // Modifier une station
    public void modifier(Station station) {
        em.merge(station);
    }

    // Supprimer une station
    public void supprimer(int id) {
        Station station = em.find(Station.class, id);

        if (station != null) {
            em.remove(station);
        }
    }
}
