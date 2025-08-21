package com.amodhakal.skillswap.entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AuthEntity {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private String email;

    @Getter
    @Setter
    @Column(nullable = false)
    private String password;

    @Getter
    @CreationTimestamp
    private Date createdAt;

    public Boolean isValidHashedPassword(String password) {
        return getPassword().equals(getHashedPassword(password));
    }

    public static AuthEntity newWithHashedPassword(String name, String email, String password) {

        AuthEntity newEntity = new AuthEntity();
        newEntity.setName(name);
        newEntity.setEmail(email);
        newEntity.setPassword(getHashedPassword(password));
        return newEntity;
    }

    public static String getHashedPassword(String password) {
        String hashedPassword;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte item : hash) {
                String hex = Integer.toHexString(0xff & item);
                hexString.append(hex.length() == 1 ? '0' : hex);
            }

            hashedPassword = hexString.toString();

        } catch (NoSuchAlgorithmException exception) {
            throw new RuntimeException("SHA-256 not supported");
        }

        return hashedPassword;
    }
}
