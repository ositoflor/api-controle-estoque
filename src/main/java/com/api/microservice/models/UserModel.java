package com.api.microservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Entity
@Table(name = "USERS")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @CPF
    @Column(name = "cpf", nullable = false,unique = true)
    private String cpf;
    @Email
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
