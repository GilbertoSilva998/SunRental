//package za.ac.cput.service;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//@Service
//public class JWTService {
//    private String secretKey = "";
//
//    public JWTService() {
//        try {
//            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
//            keyGen.init(256); // Initialize key generator to use 256-bit key size
//            SecretKey sk = keyGen.generateKey();
//            this.secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
//            System.out.println("Secret Key: " + secretKey); // Print the secret key for debugging
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException("Error initializing SecretKey: " + e.getMessage(), e);
//        }
//    }
//
//    private SecretKey getKey() {
//        // Decode the Base64 encoded secret key
//        byte[] keyBytes = Base64.getDecoder().decode(this.secretKey);
//
//        // Ensure that the key is of a sufficient length (at least 256 bits for HMAC-SHA-256)
//        if (keyBytes.length < 32) {
//            throw new IllegalArgumentException("The secret key must be at least 256 bits (32 bytes) long.");
//        }
//
//        // Create a SecretKey using the decoded bytes
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public String generateToken(String username) {
//        if (username == null || username.isEmpty()) {
//            throw new IllegalArgumentException("Username cannot be null or empty");
//        }
//
//        Map<String, Object> claims = new HashMap<>();
//
//        try {
//            String token = Jwts.builder()
//                    .setClaims(claims)
//                    .setSubject(username)
//                    .setIssuedAt(new Date(System.currentTimeMillis()))
//                    .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 24-hour expiration
//                    .signWith(SignatureAlgorithm.HS256, getKey())
//                    .compact();
//
//            System.out.println("Generated Token: " + token); // Print the generated token
//            return token;
//        } catch (Exception e) {
//            System.err.println("Error generating token: " + e.getMessage());
//            e.printStackTrace();
//            throw e; // Optionally rethrow the exception
//        }
//
//    }
//
//    public String extractUserName(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
//        Claims claims = extractAllClaims(token);
//        return claimResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser() // Use parserBuilder() instead of the deprecated parser()
//                .setSigningKey(getKey()) // Use the SecretKey directly
//                .parseClaimsJws(token) // Parse the token
//                .getBody(); // Extract the Claims from the token
//    }
//
//    public boolean validateToken(String token, UserDetails userDetails) {
//        String userName = extractUserName(token);
//        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//}

package za.ac.cput.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    private final SecretKeyManager secretKeyManager;

    public JWTService() {
        this.secretKeyManager = new SecretKeyManager();
    }

    public String generateToken(String username, String role) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        // Set claims including the role
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);  // Add the role to claims

        try {
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(username)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 24-hour expiration
                    .signWith(SignatureAlgorithm.HS256, secretKeyManager.getKey()) // Use the SecretKey from SecretKeyManager
                    .compact();

            System.out.println("Generated Token: " + token); // Print the generated token
            return token;
        } catch (Exception e) {
            System.err.println("Error generating token: " + e.getMessage());
            e.printStackTrace();
            throw e; // Optionally rethrow the exception
        }
    }


    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKeyManager.getKey()) // Use the SecretKey from SecretKeyManager
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String userName = extractUserName(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUserRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

}
