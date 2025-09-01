package io.github.rumpel1107.sphinx;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import io.github.rumpel1107.sphinx.model.BaseItem;
import io.github.rumpel1107.sphinx.model.Task;
import io.github.rumpel1107.sphinx.model.User;
import io.github.rumpel1107.sphinx.repository.TaskRepository;
import io.github.rumpel1107.sphinx.repository.UserRepository;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void listTasks_shouldReturnTasksListView() throws Exception {
		
		mockMvc.perform(get("/tasks"))
		       .andExpect(status().isOk())
		       .andExpect(view().name("index"))
		       .andExpect(model().attributeExists("tasks"));
	}
	
	@Test
	public void showEditForm_shouldReturnTaskInModel() throws Exception {
		// Arrange: Create and save a mock user and task
		User user = new User("Test User", "test@example.com", "testuser", "password");
		userRepository.save(user);
		
		Task task = new Task("Test Title", "Test Description", "Pending", user, BaseItem.Priority.High, LocalDateTime.now());
		taskRepository.save(task);

		// Act & Assert: Perform GET request to edit the task and verify the response
		mockMvc.perform(get("/tasks/edit/" + task.getId()))
		       .andExpect(status().isOk())
		       .andExpect(view().name("index"))
		       .andExpect(model().attribute("taskToProcess", hasProperty("id", is(task.getId()))))
		       .andExpect(model().attribute("taskToProcess", hasProperty("title", is(task.getTitle()))))
		       .andExpect(model().attribute("taskToProcess", hasProperty("description", is(task.getDescription()))));
		
	}

}