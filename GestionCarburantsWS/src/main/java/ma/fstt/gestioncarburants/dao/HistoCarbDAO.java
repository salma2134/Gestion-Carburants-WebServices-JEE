
package ma.fstt.gestioncarburants.dao;

import java.time.LocalDate;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import ma.fstt.gestioncarburants.entities.HistoCarb;

@Stateless
public class HistoCarbDAO {

    @PersistenceContext(unitName = "GestionCarburantsPU")
    private EntityManager em;

    // Ajouter un historique
    public void ajouter(HistoCarb h) {
        em.persist(h);
    }

    // Afficher tous les historiques
    public List<HistoCarb> afficherTout() {
        return em.createQuery(
            "SELECT h FROM HistoCarb h",
            HistoCarb.class
        ).getResultList();
    }

    // Rechercher un historique par ID
    public HistoCarb rechercher(int id) {
        return em.find(HistoCarb.class, id);
    }

    // Modifier un historique
    public void modifier(HistoCarb h) {
        em.merge(h);
    }

    // Supprimer un historique
    public void supprimer(int id) {
        HistoCarb h = em.find(HistoCarb.class, id);

        if (h != null) {
            em.remove(h);
        }
    }

    // Rechercher les historiques d'une station à une date
    public List<HistoCarb> rechercherParStationEtDate(
            String nomStation, LocalDate date) {

        return em.createQuery(
            "SELECT h FROM HistoCarb h " +
            "WHERE h.station.nom = :nomStation " +
            "AND h.date = :date",
            HistoCarb.class
        )
        .setParameter("nomStation", nomStation)
        .setParameter("date", date)
        .getResultList();
    }
}
