package io.github.rumpel1107.sphinx.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import io.github.rumpel1107.sphinx.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	// Find all active tasks
	List<Task> findByIsActiveTrue();
	
}
