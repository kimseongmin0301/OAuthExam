package com.pose.oauth.repository;

import com.pose.oauth.entity.Certification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, String> {
    Certification findByUserId(String userId);

    @Transactional
    void deleteByUserId(String userId);
}
