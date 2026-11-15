
package ma.fstt.jersey;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyApplication {

    public static void main(String[] args) {

        URI adresse =
            URI.create("http://localhost:8081/api/");

        ResourceConfig config = new ResourceConfig();

        config.register(HistoCarbResource.class);
        config.register(CorsFilter.class);

        HttpServer serveur =
            GrizzlyHttpServerFactory.createHttpServer(
                adresse, config
            );

        System.out.println("Service Jersey demarre !");
        System.out.println(
            "http://localhost:8081/api/historiques/consulterPrix"
        );
    }
}
