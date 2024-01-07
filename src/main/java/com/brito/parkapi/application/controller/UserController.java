package com.brito.parkapi.application.controller;

import com.brito.parkapi.domain.model.User;
import com.brito.parkapi.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor // Cria um metodo construtor com a variável para fazer a injeção de dependencias
@RestController // Bean gerenciado pelo spring
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    // ResponseEntity emcapsula o objeto(User) para ser transformado em um Json  e enviado para o cliente com a resposta da requisição
    // Juntamente ao objeto ele guarda algumas informações como código de restposta e cabeçalho
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){ // O User é o argumento devido a aplicação cliente tem um Json com os campos do Obejto User
                                                    // Por isso os campos de ambos deve ser igual para er feito a conversão
        User users = userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(users);
    }
}
