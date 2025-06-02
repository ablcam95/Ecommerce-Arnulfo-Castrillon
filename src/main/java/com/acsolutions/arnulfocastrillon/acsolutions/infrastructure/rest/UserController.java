package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.rest;

import com.acsolutions.arnulfocastrillon.acsolutions.application.UserService;
import com.acsolutions.arnulfocastrillon.acsolutions.domain.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<User> findByActivoTrue(){
        return userService.findByActivoTrue();
    }

    @PostMapping
    public User save (@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @GetMapping("/activo")
    public Iterable<User> findByActivo(){
        return userService.findByActivo();
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateUser(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build(); // Respuesta 400 si el ID es inválido
        }

        boolean updated = userService.updateActivo(id);
        if (updated) {
            return ResponseEntity.ok().build(); // Respuesta 204 si se actualizó correctamente
        } else {
            return ResponseEntity.notFound().build(); // Respuesta 404 si no se encontró el usuario
        }
    }

    @PutMapping("/{id}/reactivate")
    public ResponseEntity<String> reactivateUser(@PathVariable Integer id) {
        try {
            userService.reactivateUser(id);
            return ResponseEntity.ok("Usuario reactivado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al reactivar el usuario: " + e.getMessage());
        }
    }
}



