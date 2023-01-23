package com.api.microservice.services;

import com.api.microservice.models.UserModel;
import com.api.microservice.services.dtos.GetuserDto;
import com.api.microservice.services.dtos.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserModel save(UserModel user);
    List<UserModel> fidAll();
    Boolean exitsByEmail(String email);
    boolean cpfValidator(String cpf);
    String formatCpf(String cpf);
    UserModel fingById(UUID id);
    void delete(UserModel userModel);
    UserDto loginUser(GetuserDto loginDto);
    boolean validateToken(String token);
    String getTypeUser(String token);
}
