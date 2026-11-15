
package ma.fstt.jersey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/historiques")
public class HistoCarbResource {

    private static final String URL =
        "jdbc:mysql://localhost:3306/gestion_carburants?useSSL=false&serverTimezone=UTC";

    private static final String USER = "gestion_app";
    private static final String PASSWORD = "Gestion2026!";

    @GET
    @Path("/consulterPrix")
    @Produces(MediaType.TEXT_PLAIN)
    public String consulterPrix(
            @QueryParam("nomStation") String nomStation,
            @QueryParam("date") String date) {

    	String sql =
    		    "SELECT DISTINCT c.nom, h.prix " +
    		    "FROM histocarb h " +
    		    "JOIN station s ON h.id_station = s.id " +
    		    "JOIN carburant c ON h.id_carburant = c.id " +
    		    "WHERE s.nom = ? AND h.date = ?";

        StringBuilder resultat = new StringBuilder();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con =
                    DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, nomStation);
                ps.setDate(2, Date.valueOf(date));

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        resultat.append("Carburant : ")
                                .append(rs.getString("nom"))
                                .append(" | Prix : ")
                                .append(rs.getBigDecimal("prix"))
                                .append("\n");
                    }
                }
            }

            if (resultat.length() == 0) {
                return "Aucun historique trouve";
            }

            return resultat.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur : " + e.getMessage();
        }
    }
}
