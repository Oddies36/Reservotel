package be.reservotel.reservotel.Config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtUtil {
    
    @Value("${jwt.secret}")
    private String secret;

    public String getSecretKey(){
        return secret;
    }

    public String generateToken(UserDetails userDetails){
        return createToken(userDetails.getUsername());
    }

    private String createToken(String subject){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .sign(algorithm);
    }
}
