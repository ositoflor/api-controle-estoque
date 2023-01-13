package com.api.microservice.contoller;

import com.api.microservice.services.dtos.GetuserDto;
import com.api.microservice.services.execptionhandler.MessageExceptionHandler;
import com.api.microservice.models.UserModel;
import com.api.microservice.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("user")
public class UserController {

    final UserService userService;

    public  UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserModel user,
                                           @RequestHeader(HttpHeaders.AUTHORIZATION)String token){
        if (!userService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Token Inválido" ));
        };

        if (!userService.getTypeUser(token).equals("ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Usuário sem permisão" ));
        }

        if (userService.exitsByEmail(user.getEmail())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(),"E-mail já cadastrado" ));
        }
        String cpfFormated = userService.formatCpf(user.getCpf());
        user.setCpf(cpfFormated);
        if (userService.exitsBycpf(user.getCpf())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(),"CPF já cadastrado" ));
        }

        if (!userService.cpfValidator(user.getCpf())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(),"CPF invalido" ));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @GetMapping
    public  ResponseEntity<Object> getAllUsers(@RequestHeader(HttpHeaders.AUTHORIZATION)String token){
        if (!userService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Token Inválido" ));
        };

        if (!userService.getTypeUser(token).equals("ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Usuário sem permisão" ));
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.fidAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id")UUID id,
                                             @RequestHeader(HttpHeaders.AUTHORIZATION)String token) {
        if (!userService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Token Inválido" ));
        };

        if (!userService.getTypeUser(token).equals("ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Usuário sem permisão" ));
        }
       return new ResponseEntity<>(userService.fingById(id), HttpStatus.OK);
    }

   @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody GetuserDto getuserDto) {
        Object response = userService.loginUser(getuserDto);

        if (response == null) {
            MessageExceptionHandler error = new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(), "E-mail ou senha invalido.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id")UUID id,
                                                    @RequestHeader(HttpHeaders.AUTHORIZATION)String token) {
        if (!userService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Token Inválido" ));
        };

        if (!userService.getTypeUser(token).equals("ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Usuário sem permisão" ));
        }
        UserModel userModel = userService.fingById(id);
        userService.delete(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageExceptionHandler(new Date(),HttpStatus.OK.value(),"Usuário deletado com sucesso"));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> upDateUser(@PathVariable(value = "id") UUID id,
                                             @RequestBody @Valid UserModel user,
                                             @RequestHeader(HttpHeaders.AUTHORIZATION)String token) {
        if (!userService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Token Inválido" ));
        };

        if (!userService.getTypeUser(token).equals("ADMIN")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageExceptionHandler(new Date(), HttpStatus.UNAUTHORIZED.value(), "Usuário sem permisão" ));
        }
        String cpfFormated = userService.formatCpf(user.getCpf());
        UserModel userModel = userService.fingById(id);
        userModel.setCpf(cpfFormated);
        userModel.setName(user.getName());
        userModel.setTypeUser(user.getTypeUser());
        userModel.setEmail(user.getEmail());
        userModel.setPassword(user.getPassword());
        if (!userService.cpfValidator(userModel.getCpf())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageExceptionHandler(new Date(), HttpStatus.NOT_FOUND.value(),"CPF invalido" ));
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
    }

}
