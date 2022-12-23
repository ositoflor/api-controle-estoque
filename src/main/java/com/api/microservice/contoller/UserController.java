package com.api.microservice.contoller;

import com.api.microservice.execptionhandler.MessageExceptionHandler;
import com.api.microservice.models.UserModel;
import com.api.microservice.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @GetMapping
    public  ResponseEntity<List<UserModel>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.fidAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getOneUser(@PathVariable(value = "id")UUID id) {
       return new ResponseEntity<>(userService.fingById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable(value = "id")UUID id) {
        UserModel transactionsModelOptional = userService.fingById(id);
        userService.delete(transactionsModelOptional);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageExceptionHandler(new Date(),HttpStatus.OK.value(),"Usuário deletado com sucesso"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> upDateUser(@PathVariable(value = "id") UUID id,
                                             @RequestBody @Valid UserModel user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user,id));
    }

}
