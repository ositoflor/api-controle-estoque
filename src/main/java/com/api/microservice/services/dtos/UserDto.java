package com.api.microservice.services.dtos;

import com.api.microservice.models.TypeUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String name;
    private  String email;
    private TypeUser typeUser;

    private  String cpf;
}
