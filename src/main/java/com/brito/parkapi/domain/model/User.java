package com.brito.parkapi.domain.model;

import com.brito.parkapi.domain.model.enuns.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

// @Data gera um equalsandhashcode com todos os campos da entidade e as boas praticas pede que gere somente com o id da entidade
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users") // Tabelas são uma coleção de entidades por isso deve ser no plural
public class User implements Serializable { // Serializable é uma boa pratica ao trabalhar com JPa e Hibernate

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Enumerated(EnumType.STRING) // Pega o enum que é uma constante e salva no banco como uma String
    @Column(name = "role", nullable = false, length = 25)
    private Role role = Role.ROLE_CLIENT;

    // Sistema da auditoria com data de criação e por quem criou. Vai está disponivel com o spring security o spring vai pegar pelo contexto.
    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }
}
