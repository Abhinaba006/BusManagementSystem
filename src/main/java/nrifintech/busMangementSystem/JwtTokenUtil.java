// package nrifintech.busMangementSystem;
//
// import java.security.Key;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.function.Function;
//
// import javax.crypto.SecretKey;
//
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;
//
// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;
//
// @Component
// public class JwtTokenUtil {
//
// private static final SecretKey key =
// Keys.secretKeyFor(SignatureAlgorithm.HS512);
//
//// private Key getSecretKey() {
//// return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//// }
//
// public String generateToken(int userId) {
// Map<String, Object> claims = new HashMap<>();
// return createToken(claims, Integer.toString(userId));
// }
//
// private String createToken(Map<String, Object> claims, String subject) {
//// System.out.println(key.get);
// final Date createdDate = new Date();
// final Date expirationDate = new Date(System.currentTimeMillis() + 1000 * 60 *
// 60 * 10);
// return Jwts.builder()
// .setClaims(claims)
// .setSubject(subject)
// .setIssuedAt(createdDate)
// .setExpiration(expirationDate)
// .signWith(SignatureAlgorithm.HS512, key)
// .compact();
// }
//
// public boolean validateToken(String token, UserDetails userDetails) {
// final String username = extractUsername(token);
// return (username.equals(userDetails.getUsername()) &&
// !isTokenExpired(token));
// }
//
// private boolean isTokenExpired(String token) {
// return extractExpiration(token).before(new Date());
// }
//
// public String extractUsername(String token) {
// return extractClaim(token, Claims::getSubject);
// }
//
// public Date extractExpiration(String token) {
// return extractClaim(token, Claims::getExpiration);
// }
//
// public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
// final Claims claims = extractAllClaims(token);
// return claimsResolver.apply(claims);
// }
//
// private Claims extractAllClaims(String token) {
// return
// Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
// }
// }
