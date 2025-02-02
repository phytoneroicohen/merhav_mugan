package com.example.merhav_mugan;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    // Method to generate a hash using a specified algorithm (e.g., SHA-256)
    public static String generateHash(String input, String algorithm) {
        try {
            // Get an instance of the hashing algorithm
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            // Convert input string to bytes and compute the hash
            byte[] hashBytes = digest.digest(input.getBytes());

            // Convert byte array to hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


}
