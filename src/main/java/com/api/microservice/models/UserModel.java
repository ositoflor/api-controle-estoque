package com.api.microservice.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "type_user", nullable = false)
    private TypeUser typeUser;
    @NotBlank
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotBlank
    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

}
