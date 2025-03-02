package org.openapitools.controllers;


import org.openapitools.model.UserRegistration;
import org.openapitools.model.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    // POST /users: Registrar un nuevo usuario
    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRegistration request) {
        // Simulación de la creación de un usuario
        UserResponse userResponse = new UserResponse()
                .id("1") // Simulación de ID generado
                .email(request.getEmail())
                .fullName(request.getFullName())
                .dateBirth(request.getDateBirth()) // Asignar dateBirth
                .rol(request.getRol().toString());

        // Construir la URL del nuevo recurso
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userResponse.getId())
                .toUri();

        // Devolver la respuesta con el código 201 y la URL en la cabecera Location
        return ResponseEntity.created(location).body(userResponse);
    }

    // GET /users/{id}: Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        // Simulación de la obtención de un usuario por ID
        UserResponse userResponse = new UserResponse()
                .id(id)
                .email("ejemplo@uniquindio.edu.co")
                .fullName("Maria")
                .rol("user");

        return ResponseEntity.ok(userResponse);
    }

    // PUT /users/{id}: Actualizar un usuario por ID
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @Valid @RequestBody UserRegistration request) {
        // Simulación de la actualización de un usuario
        UserResponse userResponse = new UserResponse()
                .id(id)
                .email(request.getEmail())
                .fullName(request.getFullName())
                .rol(request.getRol().toString());

        return ResponseEntity.ok(userResponse);
    }

    // DELETE /users/{id}: Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        // Simulación de la eliminación de un usuario
        return ResponseEntity.noContent().build();
    }
}