package io.github.rumpel1107.sphinx.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.rumpel1107.sphinx.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);

}
