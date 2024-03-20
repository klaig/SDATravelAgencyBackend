package sda.jre28.travelagency.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private String jwtSecret = "KevinTriinJaan"; // ideally stored externally
    // set JWT_SECRET=your_secret_key_here      // in the Command Prompt

    // @Value("${JWT_SECRET}")                  // Then we can access the value from the environment
    // private String jwtSecret;

    private int jwtExpirationMs = 300000; // 5 minutes

    public String generateJwtToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return JWT.create()
                .withSubject(principal.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() + jwtExpirationMs))
                .sign(Algorithm.HMAC512(jwtSecret));
    }
    public boolean validateJwtToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(jwtSecret.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            // Invalid signature/claims
            return false;
        }
    }

    public String getUserNameFromJwtToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getSubject();
    }
}

