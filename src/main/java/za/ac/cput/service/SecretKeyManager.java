package za.ac.cput.service;

import io.jsonwebtoken.security.Keys;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecretKeyManager {
    private final String secretKey;

    public SecretKeyManager() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            keyGen.init(256); // Initialize key generator to use 256-bit key size
            SecretKey sk = keyGen.generateKey();
            this.secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
            System.out.println("Secret Key: " + secretKey); // Print the secret key for debugging
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error initializing SecretKey: " + e.getMessage(), e);
        }
    }

    public SecretKey getKey() {
        // Decode the Base64 encoded secret key
        byte[] keyBytes = Base64.getDecoder().decode(this.secretKey);

        // Ensure that the key is of a sufficient length (at least 256 bits for HMAC-SHA-256)
        if (keyBytes.length < 32) {
            throw new IllegalArgumentException("The secret key must be at least 256 bits (32 bytes) long.");
        }

        // Create a SecretKey using the decoded bytes
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

