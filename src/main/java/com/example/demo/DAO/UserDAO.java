package com.example.demo.DAO;

import com.example.demo.Model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserDAO extends JpaRepository <user, Long> {

    Optional<user> findByUsername(String username);
}
