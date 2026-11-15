
package ma.fstt.gestioncarburants.services;

import ma.fstt.gestioncarburants.security.JwtUtil;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @POST
    @Path("/login")
    public Response login(Map<String, String> user) {

        String username = user.get("username");
        String password = user.get("password");

        // Identifiants de démonstration
        if ("admin".equals(username)
                && "admin123".equals(password)) {

            String token = JwtUtil.generateToken(username);

            return Response.ok(
                Map.of(
                    "token", token,
                    "type", "Bearer",
                    "expiresIn", 3600
                )
            ).build();
        }

        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(Map.of("message", "Identifiants incorrects"))
                .build();
    }
}
