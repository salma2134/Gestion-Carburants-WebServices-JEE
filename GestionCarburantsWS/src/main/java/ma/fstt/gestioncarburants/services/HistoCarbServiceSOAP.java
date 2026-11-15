
package ma.fstt.gestioncarburants.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import ma.fstt.gestioncarburants.dao.HistoCarbDAO;
import ma.fstt.gestioncarburants.dao.StationDAO;
import ma.fstt.gestioncarburants.dao.CarburantDAO;

import ma.fstt.gestioncarburants.entities.HistoCarb;
import ma.fstt.gestioncarburants.entities.Station;
import ma.fstt.gestioncarburants.entities.Carburant;

@Stateless
@WebService(serviceName = "HistoCarbService")
public class HistoCarbServiceSOAP {

    @EJB
    private HistoCarbDAO histoDAO;

    @EJB
    private StationDAO stationDAO;

    @EJB
    private CarburantDAO carburantDAO;

    // Ajouter un historique de prix
    @WebMethod
    public String ajouterHistorique(
            String date, BigDecimal prix,
            int idStation, int idCarburant) {

        Station station = stationDAO.rechercher(idStation);
        Carburant carburant = carburantDAO.rechercher(idCarburant);

        if (station == null || carburant == null) {
            return "Station ou carburant introuvable";
        }

        HistoCarb historique = new HistoCarb(
            LocalDate.parse(date),
            prix,
            station,
            carburant
        );

        histoDAO.ajouter(historique);

        return "Historique ajoute avec succes";
    }

    // Afficher tous les historiques
    @WebMethod
    public String afficherHistoriques() {

        List<HistoCarb> historiques = histoDAO.afficherTout();

        String resultat = "";

        for (HistoCarb h : historiques) {

            resultat += "ID : " + h.getId()
                + " | Date : " + h.getDate()
                + " | Prix : " + h.getPrix()
                + " | Station : " + h.getStation().getNom()
                + " | Carburant : " + h.getCarburant().getNom()
                + "\n";
        }

        return resultat;
    }

    // Rechercher un historique
    @WebMethod
    public String rechercherHistorique(int id) {

        HistoCarb h = histoDAO.rechercher(id);

        if (h == null) {
            return "Historique introuvable";
        }

        return "ID : " + h.getId()
            + " | Date : " + h.getDate()
            + " | Prix : " + h.getPrix()
            + " | Station : " + h.getStation().getNom()
            + " | Carburant : " + h.getCarburant().getNom();
    }

    // Modifier un historique
    @WebMethod
    public String modifierHistorique(
            int id, String date, BigDecimal prix,
            int idStation, int idCarburant) {

        HistoCarb h = histoDAO.rechercher(id);

        if (h == null) {
            return "Historique introuvable";
        }

        Station station = stationDAO.rechercher(idStation);
        Carburant carburant = carburantDAO.rechercher(idCarburant);

        if (station == null || carburant == null) {
            return "Station ou carburant introuvable";
        }

        h.setDate(LocalDate.parse(date));
        h.setPrix(prix);
        h.setStation(station);
        h.setCarburant(carburant);

        histoDAO.modifier(h);

        return "Historique modifie avec succes";
    }

    // Supprimer un historique
    @WebMethod
    public String supprimerHistorique(int id) {

        HistoCarb h = histoDAO.rechercher(id);

        if (h == null) {
            return "Historique introuvable";
        }

        histoDAO.supprimer(id);

        return "Historique supprime avec succes";
    }

    // Consulter les prix des carburants d'une station à une date
    @WebMethod
    public String consulterPrix(String nomStation, String date) {

        List<HistoCarb> historiques =
            histoDAO.rechercherParStationEtDate(
                nomStation,
                LocalDate.parse(date)
            );

        if (historiques.isEmpty()) {
            return "Aucun historique trouve";
        }

        String resultat = "";

        for (HistoCarb h : historiques) {

            resultat += "Carburant : "
                + h.getCarburant().getNom()
                + " | Prix : "
                + h.getPrix()
                + "\n";
        }

        return resultat;
    }
}
