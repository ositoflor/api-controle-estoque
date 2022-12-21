package com.api.microservice.contoller;

import com.api.microservice.models.UserModel;
import com.api.microservice.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("user")
public class UserController {

    final UserService userService;

    public  UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserModel user){
        String cpfFormated = userService.formatCpf(user.getCpf());
        user.setCpf(cpfFormated);
        if (!userService.cpfValidator(user.getCpf())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF invalido");
        }
        if (userService.exitsByEmail(user.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail informado já existe");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @GetMapping
    public  ResponseEntity<List<UserModel>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.fidAll());
    }
}
