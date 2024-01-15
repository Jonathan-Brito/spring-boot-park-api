package com.brito.parkapi.domain.service;

import com.brito.parkapi.domain.model.User;
import com.brito.parkapi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public User editarSenha(Long id, String currentPassword, String newPassword, String confirmPassword){
        if (!newPassword.equals(confirmPassword)){
            throw new RuntimeException("New password not equals with confirm password!!!");
        }

        User user = buscarPorId(id);

        if (!user.getPassword().equals(currentPassword)){
            throw new RuntimeException("Password not equals with current password!!!");
        }

        user.setPassword(newPassword);

        return user;
    }

    @Transactional(readOnly = true)
    public List<User> buscarTodos() {
        return userRepository.findAll();
    }
}
