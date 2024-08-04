package com.pose.oauth.repository;

import com.pose.oauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String userId);

    boolean existsByUserId(String userId);
}
