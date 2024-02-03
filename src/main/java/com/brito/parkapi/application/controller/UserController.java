package com.brito.parkapi.application.controller;

import com.brito.parkapi.domain.dto.UserCreateDto;
import com.brito.parkapi.domain.dto.UserPasswordDto;
import com.brito.parkapi.domain.dto.UserResponseDto;
import com.brito.parkapi.domain.mapper.UserMapper;
import com.brito.parkapi.domain.model.User;
import com.brito.parkapi.domain.service.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDto createDto){ // O User é o argumento devido a aplicação cliente tem um Json com os campos do Obejto User
                                                    // Por isso os campos de ambos deve ser igual para er feito a conversão
        User user = userService.save(UserMapper.toUser(createDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable("id") Long id){
        User user = userService.buscarPorId(id);

        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll(){
       List<User> users = userService.buscarTodos();

       return ResponseEntity.ok(UserMapper.toListDto(users));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable("id") Long id, @Valid @RequestBody UserPasswordDto userPasswordDto){
        User user = userService.editarSenha(id, userPasswordDto.getCurrentPassword(), userPasswordDto.getNewPassword(), userPasswordDto.getConfirmPassword());

        return ResponseEntity.noContent().build();
    }
}
