
package ma.fstt.gestioncarburants.services;

import java.util.List;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import ma.fstt.gestioncarburants.dao.CarburantDAO;
import ma.fstt.gestioncarburants.entities.Carburant;

@Stateless
@WebService(serviceName = "CarburantService")
public class CarburantServiceSOAP {

    @EJB
    private CarburantDAO carburantDAO;

    // Ajouter un carburant
    @WebMethod
    public String ajouterCarburant(String nom, String description) {

        Carburant carburant = new Carburant(nom, description);

        carburantDAO.ajouter(carburant);

        return "Carburant ajoute avec succes";
    }

    // Afficher tous les carburants
    @WebMethod
    public List<Carburant> afficherCarburants() {
        return carburantDAO.afficherTout();
    }

    // Rechercher un carburant
    @WebMethod
    public Carburant rechercherCarburant(int id) {
        return carburantDAO.rechercher(id);
    }

    // Modifier un carburant
    @WebMethod
    public String modifierCarburant(
            int id, String nom, String description) {

        Carburant carburant = carburantDAO.rechercher(id);

        if (carburant == null) {
            return "Carburant introuvable";
        }

        carburant.setNom(nom);
        carburant.setDescription(description);

        carburantDAO.modifier(carburant);

        return "Carburant modifie avec succes";
    }

    // Supprimer un carburant
    @WebMethod
    public String supprimerCarburant(int id) {

        Carburant carburant = carburantDAO.rechercher(id);

        if (carburant == null) {
            return "Carburant introuvable";
        }

        carburantDAO.supprimer(id);

        return "Carburant supprime avec succes";
    }
}
