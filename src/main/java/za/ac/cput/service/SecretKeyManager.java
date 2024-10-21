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
            keyGen.init(256);
            SecretKey sk = keyGen.generateKey();
            this.secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
            System.out.println("Secret Key: " + secretKey); // Print the secret key for debugging
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error initializing SecretKey: " + e.getMessage(), e);
        }
    }

    public SecretKey getKey() {

        byte[] keyBytes = Base64.getDecoder().decode(this.secretKey);


        if (keyBytes.length < 32) {
            throw new IllegalArgumentException("The secret key must be at least 256 bits (32 bytes) long.");
        }


        return Keys.hmacShaKeyFor(keyBytes);
    }
}

