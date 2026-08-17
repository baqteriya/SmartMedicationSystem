package com.riya.smartmedication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riya.smartmedication.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmailAndPassword(String email, String password);

}