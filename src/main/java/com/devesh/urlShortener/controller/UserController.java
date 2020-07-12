package com.devesh.urlShortener.controller;

import com.devesh.urlShortener.models.UserDTO;
import com.devesh.urlShortener.response.Response;
import com.devesh.urlShortener.service.UserCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @Autowired
    UserCreateService userCreateService;

    @PostMapping("/create")
    public ResponseEntity<Response> createUser(@RequestBody UserDTO userDTO) {
        userCreateService.createUser(userDTO);
        Response response = Response.builder()
                .message("Customer Created")
                .code(HttpStatus.OK.value()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
