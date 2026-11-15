
package ma.fstt.gestioncarburants.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    // Clé de démonstration : à remplacer et garder secrète
    private static final String SECRET =
        "bXktc3VwZXItc2VjcmV0LWtleS0zMi1ieXRlcy1sb25nIQ==";

    private static final long EXPIRATION = 60 * 60 * 1000;

    private static SecretKey getKey() {
        return Keys.hmacShaKeyFor(
            Decoders.BASE64.decode(SECRET)
        );
    }

    public static String generateToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getKey())
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getUsername(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }
}
