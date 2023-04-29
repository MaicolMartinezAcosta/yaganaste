package com.yaganaste.login.controller;


import com.yaganaste.login.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }
}
