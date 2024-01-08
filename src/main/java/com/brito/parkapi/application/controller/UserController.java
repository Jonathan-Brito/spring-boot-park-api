package com.brito.parkapi.application.controller;

import com.brito.parkapi.domain.model.User;
import com.brito.parkapi.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id){
        User user = userService.buscarPorId(id);

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
       List<User> users = userService.buscarTodos();

       return ResponseEntity.ok(users);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable("id") Long id, @RequestBody User user){
        User usuario = userService.editarSenha(id, user.getPassword());

        return ResponseEntity.ok(usuario);
    }
}
