
package ma.fstt.gestioncarburants.security;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
public class JwtFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext request)
            throws IOException {

        String authorization =
                request.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorization == null
                || !authorization.startsWith("Bearer ")) {

            request.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Token JWT manquant")
                    .build()
            );
            return;
        }

        String token = authorization.substring(7);

        if (!JwtUtil.validateToken(token)) {

            request.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Token JWT invalide ou expiré")
                    .build()
            );
        }
    }
}
