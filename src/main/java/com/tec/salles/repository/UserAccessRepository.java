package com.tec.salles.repository;

import com.tec.salles.entity.UserAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccessRepository extends JpaRepository<UserAccess, String> {
    Optional<UserAccess> findByUsername(String username);
}
