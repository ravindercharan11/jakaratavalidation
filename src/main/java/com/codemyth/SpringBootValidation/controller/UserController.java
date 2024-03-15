package com.codemyth.SpringBootValidation.controller;

import com.codemyth.SpringBootValidation.model.User;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping("/create")
    public ResponseEntity createUser(@Valid @RequestBody User user, BindingResult result){

        if(result.hasErrors()){
            List<String> errorMessageList= result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessageList);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("User Created Successfully");

    }

}
