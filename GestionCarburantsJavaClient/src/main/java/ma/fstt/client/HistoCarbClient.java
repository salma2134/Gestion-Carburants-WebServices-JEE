
package ma.fstt.client;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class HistoCarbClient {

    public static void main(String[] args) {

        try {
            // Adresse du service JAX-RS
            String baseUrl =
                "http://localhost:8080/GestionCarburantsWS/api/historiques/consulterPrix";

            // Paramètres de recherche
            String nomStation = URLEncoder.encode(
                "Station Afriquia", StandardCharsets.UTF_8);

            String date = "2026-11-14";

            // Construire l'URL complète
            String url = baseUrl
                + "?nomStation=" + nomStation
                + "&date=" + date;

            // Créer le client HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Préparer la requête GET
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

            // Envoyer la requête
            HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
            );

            // Afficher le résultat
            System.out.println("===== CLIENT JAVA JAX-RS =====");

            System.out.println("URL : " + url);

            System.out.println("Statut HTTP : "
                + response.statusCode());

            System.out.println("Réponse du serveur :");

            System.out.println(response.body());

        } catch (Exception e) {
            System.out.println("Erreur lors de l'appel du service :");
            e.printStackTrace();
        }
    }
}
