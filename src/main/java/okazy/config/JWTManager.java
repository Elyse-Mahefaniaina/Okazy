package okazy.config;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import okazy.model.user.Utilisateur;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTManager {
    private static final String secret = "DvxMWzlQ2d6zSQ77EseNcGI1x0hhpCVJwtXBIx4c7uUlDSSRCD4kBXFyzEY2zLdN";
    private static final Key key = new SecretKeySpec(Base64.getDecoder().decode(secret),
            SignatureAlgorithm.HS256.getJcaName());

    public String generateToken(Utilisateur utilisateur) {
        Date currentDate = new Date();

        return Jwts.builder()
                .setSubject(utilisateur.getUsername())
                .setIssuedAt(currentDate)
                .setExpiration(new Date(currentDate.getTime() + dayToMs(1)))
                .claim("nom", utilisateur.getNom())
                .claim("username", utilisateur.getUsername())
                .claim("role", utilisateur.getRole().getNom())
                .signWith(key)
                .compact();
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public void validateToken(String token) throws AuthenticationCredentialsNotFoundException {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("Invalid token",
                    ex.fillInStackTrace());
        }
    }

    private long dayToMs(int days) {
        return days * 24 * 60 * 60 * 1000L;
    }
}
