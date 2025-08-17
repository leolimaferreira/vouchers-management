package com.vouchers.model;

import com.vouchers.model.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", unique = false, length = 150)
    private String name;

    @Column(name = "email", unique = true, length = 255)
    private String email;

    @Column(name = "password", unique = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
