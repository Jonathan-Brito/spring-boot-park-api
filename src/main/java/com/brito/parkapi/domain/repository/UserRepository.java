package com.brito.parkapi.domain.repository;

import com.brito.parkapi.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
