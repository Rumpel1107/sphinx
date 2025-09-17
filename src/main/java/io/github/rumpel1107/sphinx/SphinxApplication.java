package io.github.rumpel1107.sphinx;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.rumpel1107.sphinx.model.User;
import io.github.rumpel1107.sphinx.repository.UserRepository;

@SpringBootApplication
public class SphinxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SphinxApplication.class, args);
	}

    @Bean
    CommandLineRunner initialData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    	return args -> {
    		// Check if any user already exists in the database
    		if (userRepository.count() == 0) {
    			// If no users exist, create and save the mock user
	            User testUser = new User("Test User", "test@example.com", "testuser", passwordEncoder.encode("password"));
	            userRepository.save(testUser);
    		}
		};
	};

}