package com.brito.parkapi.domain.service;

import com.brito.parkapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // Cria um metodo construtor com a variável para fazer a injeção de dependencias
@Service // Bean gerenciado pelo spring
public class UserService {

    private final UserRepository userRepository;
}
