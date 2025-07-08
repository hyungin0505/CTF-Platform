package com.ctfplatform.backend.domain.challenge.repository;

import com.ctfplatform.backend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
