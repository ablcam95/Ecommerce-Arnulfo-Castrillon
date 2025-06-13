package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.rest;

import com.acsolutions.arnulfocastrillon.acsolutions.application.UserService;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.User;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.dto.JWTClient;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.dto.UserDTO;
import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.jwt.JWTGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/security")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;
    private final UserService userService;

    public LoginController(AuthenticationManager authenticationManager, JWTGenerator jwtGenerator, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<JWTClient> login (@RequestBody UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.username(),userDTO.password())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("el Rol de User : {}",SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().findFirst().get().toString());

        User user = userService.findByEmail(userDTO.username());


        String token = jwtGenerator.getToken(userDTO.username());

        JWTClient jwtClient = new JWTClient(user.getId(),token, user.getUserType().toString());

        return new ResponseEntity<>(jwtClient, HttpStatus.OK);
    }
}
