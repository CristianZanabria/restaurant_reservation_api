package com.zdevs.restaurant_reservation_api.repository;

import com.zdevs.restaurant_reservation_api.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findOneByEmail(String username);
    boolean existsByEmail(String email);

}
