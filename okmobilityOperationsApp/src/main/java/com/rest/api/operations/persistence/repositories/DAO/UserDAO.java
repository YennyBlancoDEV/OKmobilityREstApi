package com.rest.api.operations.persistence.repositories.DAO;

import com.rest.api.operations.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


//Class to query to find users by email in the DB

public interface UserDAO extends JpaRepository<UserEntity, Long> {
	

    @Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
    Optional<UserEntity> findByEmail(String email);
}
