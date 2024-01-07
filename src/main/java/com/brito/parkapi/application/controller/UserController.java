package com.brito.parkapi.application.controller;

import com.brito.parkapi.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor // Cria um metodo construtor com a variável para fazer a injeção de dependencias
@RestController // Bean gerenciado pelo spring
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
}
