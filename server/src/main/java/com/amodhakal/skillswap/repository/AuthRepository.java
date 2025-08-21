package com.amodhakal.skillswap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.amodhakal.skillswap.entities.AuthEntity;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<AuthEntity, UUID> {
    AuthEntity findByEmail(String email);
}
