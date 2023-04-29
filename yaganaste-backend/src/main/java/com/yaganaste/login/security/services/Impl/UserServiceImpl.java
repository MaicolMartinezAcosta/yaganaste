package com.yaganaste.login.security.services.Impl;

import com.yaganaste.login.models.ERole;
import com.yaganaste.login.models.Role;
import com.yaganaste.login.models.User;
import com.yaganaste.login.payload.request.SignupRequest;
import com.yaganaste.login.payload.response.MessageResponse;
import com.yaganaste.login.repository.RoleRepository;
import com.yaganaste.login.repository.UserRepository;
import com.yaganaste.login.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: el nombre de usuario ya esta en uso"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: este correo ya esta en uso"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: este rol no existe en el sistema"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: este rol no existe en el sistema"));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: este rol no existe en el sistema"));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: este rol no existe en el sistema"));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("El usuario se registro exitosamente!"));
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        String sql = "SELECT * FROM users u inner join user_roles ur on ur.user_id = u.id inner join roles r on ur.role_id = r.id";
        List<User> usuarios = jdbcTemplate.query(sql,new UserRowMapper());
        return  ResponseEntity.ok(usuarios);
    }
}
