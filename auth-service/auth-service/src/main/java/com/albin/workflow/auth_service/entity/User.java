package com.albin.workflow.auth_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tenantId;

    @Column(unique = true)
    private String email;

    private String password;

    private String role;
    @Column(name = "created_at")
    private LocalDateTime createdAt;


}
