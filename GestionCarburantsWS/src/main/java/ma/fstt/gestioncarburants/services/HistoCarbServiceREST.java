
package ma.fstt.gestioncarburants.services;

import java.time.LocalDate;
import java.util.List;
import ma.fstt.gestioncarburants.security.Secured;
import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import ma.fstt.gestioncarburants.dao.HistoCarbDAO;
import ma.fstt.gestioncarburants.entities.HistoCarb;

@Path("/historiques")
@Secured
public class HistoCarbServiceREST {

    @EJB
    private HistoCarbDAO histoDAO;

    @GET
    @Path("/consulterPrix")
    @Produces(MediaType.TEXT_PLAIN)
    public String consulterPrix(
            @QueryParam("nomStation") String nomStation,
            @QueryParam("date") String date) {

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
