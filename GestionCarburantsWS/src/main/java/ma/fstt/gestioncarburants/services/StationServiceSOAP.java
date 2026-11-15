
package ma.fstt.gestioncarburants.services;
import jakarta.ejb.Stateless;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import ma.fstt.gestioncarburants.dao.StationDAO;
import ma.fstt.gestioncarburants.entities.Station;
@Stateless
@WebService(serviceName = "StationService")
public class StationServiceSOAP {

    @EJB
    private StationDAO stationDAO;

    // Ajouter une station
    @WebMethod
    public String ajouterStation(String nom, String ville, String adresse) {

        Station station = new Station(nom, ville, adresse);

        stationDAO.ajouter(station);

        return "Station ajoutee avec succes";
    }

    // Afficher toutes les stations
    @WebMethod
    public List<Station> afficherStations() {
        return stationDAO.afficherTout();
    }

    // Rechercher une station
    @WebMethod
    public Station rechercherStation(int id) {
        return stationDAO.rechercher(id);
    }

    // Modifier une station
    @WebMethod
    public String modifierStation(
            int id, String nom, String ville, String adresse) {

        Station station = stationDAO.rechercher(id);

        if (station == null) {
            return "Station introuvable";
        }

        station.setNom(nom);
        station.setVille(ville);
        station.setAdresse(adresse);

        stationDAO.modifier(station);

        return "Station modifiee avec succes";
    }

    // Supprimer une station
    @WebMethod
    public String supprimerStation(int id) {

        Station station = stationDAO.rechercher(id);

        if (station == null) {
            return "Station introuvable";
        }

        stationDAO.supprimer(id);

        return "Station supprimee avec succes";
    }
}
