package nrifintech.busMangementSystem;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {

   private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

//    private Key getSecretKey() {
//        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//    }

   public String generateToken(int userId, int type) {
	   System.out.println("\n--------------------------------\n----------------------\n");
	   System.out.println("key: "+key.toString());
	   System.out.println("\n--------------------------------\n----------------------\n");
	   
	    Map<String, Object> claims = new HashMap<>();
	    claims.put("type", type);
		String createdToken=createToken(claims, Integer.toString(userId));
		System.out.println("Token is -> "+createdToken);
	    return createdToken;
	}
 

   private String createToken(Map<String, Object> claims, String subject) {
	    final Date createdDate = new Date();
	    return Jwts.builder()
	        .setClaims(claims)
	        .setSubject(subject)
	        .setIssuedAt(createdDate)
	        .claim("type", claims.get("type"))
	        .signWith(key)
	        .compact();
	}
   public int extractType(String token) {
	    return extractClaim(token, claims -> claims.get("type", Integer.class));
	}

   public boolean validateToken(String token, UserDetails userDetails, int expectedType) {
	    final String username = extractUsername(token);
	    final int type = extractType(token);
	    return (username.equals(userDetails.getUsername()) && type == expectedType && !isTokenExpired(token));
	}

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
    	return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
