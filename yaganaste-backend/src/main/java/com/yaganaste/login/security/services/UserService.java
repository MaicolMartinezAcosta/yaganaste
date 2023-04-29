package com.yaganaste.login.security.services;

import com.yaganaste.login.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

public interface UserService {
     ResponseEntity<?> registerUser( SignupRequest signUpRequest);

     ResponseEntity<?> getAllUsers();
}
