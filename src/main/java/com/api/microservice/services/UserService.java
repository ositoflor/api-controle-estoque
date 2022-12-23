package com.api.microservice.services;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.format.Formatter;
import com.api.microservice.execptionhandler.UserNotFoundException;
import com.api.microservice.models.UserModel;
import com.api.microservice.repositories.UserRepository;
import com.api.microservice.services.validation.CpfValidate;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserModel save(UserModel user) {
        var encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public List<UserModel> fidAll() {
        return userRepository.findAll();
    }

    public boolean exitsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public boolean exitsBycpf(String cpf) {
        return userRepository.existsByCpf(cpf);
    }
    public boolean cpfValidator(String cpf) {
        boolean cpfIsValid = CpfValidate.isCPF(cpf);
        return cpfIsValid;
    }

    public String formatCpf(String cpf) {
        if (cpf.length() == 14) {
            Formatter formatrer = new CPFFormatter();
            String unformatedValue = formatrer.unformat(cpf);
            return unformatedValue;
        }else {
            return  cpf;
        }
    }

    public UserModel fingById(UUID id) {
        Optional<UserModel> user = userRepository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException());
    }

    @Transactional
    public void delete(UserModel userModel) {
        fingById(userModel.getId());
        userRepository.delete(userModel);
    }

}
