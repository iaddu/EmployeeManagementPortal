package com.ems;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordExample {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Hash a password
        String rawPassword = "Sufism";
        String hashedPassword = encoder.encode(rawPassword);

        System.out.println("Hashed Password: " + hashedPassword);

        // Verify the hashed password
        boolean isMatch = encoder.matches(rawPassword, hashedPassword);
        System.out.println("Passwords match: " + isMatch);
    }
}