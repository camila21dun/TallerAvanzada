package org.openapitools.controllers;


import org.openapitools.model.UserRegistration;
import org.openapitools.model.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import jakarta.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final List<UserResponse> users = new ArrayList<>();
    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRegistration request) {

        UserResponse userResponse = new UserResponse()
                .id("123")
                .email(request.getEmail())
                .fullName(request.getFullName())
                .dateBirth(request.getDateBirth())
                .rol(request.getRol().toString());


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userResponse.getId())
                .toUri();


        return ResponseEntity.created(location).body(userResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        UserResponse userResponse = new UserResponse()
                .id(id)
                .email("maria@uniquindio.edu.co")
                .fullName("Maria")
                .rol("user");

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        // Validar parámetros de paginación
        if (page < 1 || size < 1) {
            return ResponseEntity.badRequest().build();
        }

        // Calcular el índice de inicio y fin para la paginación
        int start = (page - 1) * size;
        int end = Math.min(start + size, users.size());

        // Verificar si el índice de inicio es válido
        if (start >= users.size()) {
            return ResponseEntity.ok(List.of()); // Devuelve una lista vacía si no hay elementos
        }

        // Obtener la sublista paginada
        List<UserResponse> paginatedUsers = users.subList(start, end);

        // Devolver la lista paginada
        return ResponseEntity.ok(paginatedUsers);
    }



    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @Valid @RequestBody UserRegistration request) {

        UserResponse userResponse = new UserResponse()
                .id(id)
                .email(request.getEmail())
                .fullName(request.getFullName())
                .rol(request.getRol().toString());

        return ResponseEntity.ok(userResponse);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {

        return ResponseEntity.noContent().build();
    }
}