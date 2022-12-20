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
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @GetMapping
    public  ResponseEntity<List<UserModel>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.fidAll());
    }
}
