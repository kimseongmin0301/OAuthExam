package com.pose.oauth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CERTIFICATION")
@Entity(name = "CERTIFICATION")
public class Certification {
    @Id
    private String userId;
    private String email;
    private String certificationNumber;
}
