package com.brito.parkapi.domain.service;

import com.brito.parkapi.domain.model.User;
import com.brito.parkapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor // Cria um metodo construtor com a variável para fazer a injeção de dependencias
@Service // Bean gerenciado pelo spring
public class UserService {

    private final UserRepository userRepository;

    @Transactional // Spring vai cuidar
    public User save(User user){
        return userRepository.save(user);
    }

    @Transactional(readOnly = true) // Indicando para o Spring que esse método é exclusivo para uma consulta no bando de dados
    public User buscarPorId(Long id){   // Gerando um benefício de performance
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User notFound")
        );
    }
}
