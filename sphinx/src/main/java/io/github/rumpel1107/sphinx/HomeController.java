package io.github.rumpel1107.sphinx;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//Need to import the own class to use it.
import io.github.rumpel1107.sphinx.model.Task;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		
		//1. Create a list to hold the tasks.
		List<Task> taskList = new ArrayList<>();
		
		//2. Create a first task object and set its properties.
		Task task1 = new Task();
		task1.setId(1L);
		task1.setTitle("Set up project on Github");
		task1.setStatus("Completed");
		task1.setCreationDate(LocalDateTime.now());

		//3. Create a second task object.
		Task task2 = new Task();
		task2.setId(2L);
		task2.setTitle("Create data model");
		task2.setStatus("Completed");
		task2.setCreationDate(LocalDateTime.now());
		
		//4. Create a third task object.
		Task task3 = new Task();
		task3.setId(3L);
		task3.setTitle("Display task list on homepage");
		task3.setStatus("In Progress");
		task3.setCreationDate(LocalDateTime.now());
		
		// 5. Add the tasks to the list
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        // 6. Add the complete list to the model with the tag "tasks"
        model.addAttribute("tasks", taskList);
		
		return "index";
	}
}