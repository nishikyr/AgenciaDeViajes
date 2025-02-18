package es.daw.vuelosmvc.repository;


import es.daw.vuelosmvc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Siendo una interfaz, no necesitas la anotación @Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
