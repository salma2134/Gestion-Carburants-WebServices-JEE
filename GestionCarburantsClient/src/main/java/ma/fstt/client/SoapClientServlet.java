
package ma.fstt.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/soap")
public class SoapClientServlet extends HttpServlet {

    private static final String NAMESPACE =
        "http://services.gestioncarburants.fstt.ma/";

    private String param(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        return value == null ? "" : value.trim();
    }

    private String escapeXml(String value) {
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&apos;");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = param(request, "action");

        String serviceUrl;
        String operation;
        String[] values;

        switch (action) {

            // STATION
            case "ajouterStation":
                serviceUrl = "StationService";
                operation = "ajouterStation";
                values = new String[] {
                    param(request, "nom"),
                    param(request, "ville"),
                    param(request, "adresse")
                };
                break;

            case "afficherStations":
                serviceUrl = "StationService";
                operation = "afficherStations";
                values = new String[] {};
                break;

            case "rechercherStation":
                serviceUrl = "StationService";
                operation = "rechercherStation";
                values = new String[] {
                    param(request, "id")
                };
                break;

            case "modifierStation":
                serviceUrl = "StationService";
                operation = "modifierStation";
                values = new String[] {
                    param(request, "id"),
                    param(request, "nom"),
                    param(request, "ville"),
                    param(request, "adresse")
                };
                break;

            case "supprimerStation":
                serviceUrl = "StationService";
                operation = "supprimerStation";
                values = new String[] {
                    param(request, "id")
                };
                break;

            // CARBURANT
            case "ajouterCarburant":
                serviceUrl = "CarburantService";
                operation = "ajouterCarburant";
                values = new String[] {
                    param(request, "nom"),
                    param(request, "description")
                };
                break;

            case "afficherCarburants":
                serviceUrl = "CarburantService";
                operation = "afficherCarburants";
                values = new String[] {};
                break;

            case "rechercherCarburant":
                serviceUrl = "CarburantService";
                operation = "rechercherCarburant";
                values = new String[] {
                    param(request, "id")
                };
                break;

            case "modifierCarburant":
                serviceUrl = "CarburantService";
                operation = "modifierCarburant";
                values = new String[] {
                    param(request, "id"),
                    param(request, "nom"),
                    param(request, "description")
                };
                break;

            case "supprimerCarburant":
                serviceUrl = "CarburantService";
                operation = "supprimerCarburant";
                values = new String[] {
                    param(request, "id")
                };
                break;

            // HISTORIQUES
            case "ajouterHistorique":
                serviceUrl = "HistoCarbService";
                operation = "ajouterHistorique";
                values = new String[] {
                    param(request, "date"),
                    param(request, "prix"),
                    param(request, "idStation"),
                    param(request, "idCarburant")
                };
                break;

            case "afficherHistoriques":
                serviceUrl = "HistoCarbService";
                operation = "afficherHistoriques";
                values = new String[] {};
                break;

            case "rechercherHistorique":
                serviceUrl = "HistoCarbService";
                operation = "rechercherHistorique";
                values = new String[] {
                    param(request, "id")
                };
                break;

            case "modifierHistorique":
                serviceUrl = "HistoCarbService";
                operation = "modifierHistorique";
                values = new String[] {
                    param(request, "id"),
                    param(request, "date"),
                    param(request, "prix"),
                    param(request, "idStation"),
                    param(request, "idCarburant")
                };
                break;

            case "supprimerHistorique":
                serviceUrl = "HistoCarbService";
                operation = "supprimerHistorique";
                values = new String[] {
                    param(request, "id")
                };
                break;

            case "consulterPrix":
                serviceUrl = "HistoCarbService";
                operation = "consulterPrix";
                values = new String[] {
                    param(request, "nomStation"),
                    param(request, "date")
                };
                break;

            default:
                throw new ServletException("Operation inconnue");
        }

        String endpoint =
            "http://localhost:8080/GestionCarburantsWS/"
            + serviceUrl + "/" + serviceUrl + "SOAP";

        StringBuilder arguments = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            arguments.append("<arg")
                     .append(i)
                     .append(">")
                     .append(escapeXml(values[i]))
                     .append("</arg")
                     .append(i)
                     .append(">");
        }

        String soapXml =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<soapenv:Envelope "
            + "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
            + "xmlns:ser=\"" + NAMESPACE + "\">"
            + "<soapenv:Header/>"
            + "<soapenv:Body>"
            + "<ser:" + operation + ">"
            + arguments
            + "</ser:" + operation + ">"
            + "</soapenv:Body>"
            + "</soapenv:Envelope>";

        String resultat;

        try {
            HttpURLConnection connection =
                (HttpURLConnection) new URL(endpoint).openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty(
                "Content-Type", "text/xml; charset=UTF-8"
            );

            byte[] data = soapXml.getBytes(StandardCharsets.UTF_8);

            try (OutputStream output = connection.getOutputStream()) {
                output.write(data);
            }

            int status = connection.getResponseCode();

            InputStream input = status >= 400
                ? connection.getErrorStream()
                : connection.getInputStream();

            if (input == null) {
                resultat = "Erreur HTTP : " + status;
            } else {
                try (input) {
                    resultat = new String(
                        input.readAllBytes(),
                        StandardCharsets.UTF_8
                    );
                }
            }

            connection.disconnect();

            request.setAttribute("status", status);

        } catch (Exception e) {
            resultat = "Erreur lors de l'appel SOAP : "
                     + e.getMessage();
            request.setAttribute("status", "Erreur");
        }

        request.setAttribute("operation", operation);
        request.setAttribute("resultat", resultat);

        request.getRequestDispatcher("/result.jsp")
               .forward(request, response);
    }
}
