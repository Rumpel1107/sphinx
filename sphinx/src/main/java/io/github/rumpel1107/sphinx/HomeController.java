package io.github.rumpel1107.sphinx;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.rumpel1107.sphinx.model.Task;

@Controller
public class HomeController {

	// 1. The list is now a field of the class, it persists between requests.
	private List<Task> taskList = new ArrayList<>();
	// 2. A simple counter to generate unique IDs.
	private final AtomicLong counter = new AtomicLong();

	public HomeController() {
		// 3. We create the initial tasks in the constructor, only once.
		taskList.add(new Task(counter.incrementAndGet(), "Set up project on GitHub", "Completed", LocalDateTime.now()));
		taskList.add(new Task(counter.incrementAndGet(), "Create data model", "Completed", LocalDateTime.now()));
		taskList.add(new Task(counter.incrementAndGet(), "Display task list on homepage", "Completed", LocalDateTime.now()));
	}

	@GetMapping("/")
	public String home(Model model) {
		// Now we just add the existing list to the model.
		model.addAttribute("tasks", taskList);
		return "index";
	}

	@PostMapping("/addTask")
	public String addTask(@RequestParam("title") String title, @RequestParam("status") String status) {

		// 4. Create a new task with the data from the form.
		Task newTask = new Task(counter.incrementAndGet(), title, status, LocalDateTime.now());

		// 5. Add the new task to our persistent list.
		taskList.add(newTask);

		// 6. Redirect to the homepage to see the updated list.
		return "redirect:/";
	}
}