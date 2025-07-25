package io.github.rumpel1107.sphinx;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.rumpel1107.sphinx.model.Task;

@Controller
public class HomeController {

	// The list is now a field of the class, it persists between requests.
	private List<Task> taskList = new ArrayList<>();
	// A simple counter to generate unique IDs.
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("tasks", taskList);
		// Add an empty Task object for the creation form
		model.addAttribute("taskToProcess", new Task());
		return "index";
	}
	
	@GetMapping("/editTask/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		// Find the task in our list by its ID
		Task taskToEdit = taskList.stream()
				.filter(task -> task.getId().equals(id))
				.findFirst()
				.orElse(null); // orElse(null) handles the case where the task is not found
		
		// Add the found task to the model to pre-fill the form
		model.addAttribute("tasks", taskList);
		model.addAttribute("taskToProcess", taskToEdit);
		return "index";
	}

	@PostMapping("/addTask")
	public String addTask(@RequestParam("title") String title, @RequestParam("description") String description,
			@RequestParam("status") String status) {

		// Create a new task with the data from the form.
		Task newTask = new Task(counter.incrementAndGet(), title, description, status, LocalDateTime.now());

		// Add the new task to our persistent list.
		taskList.add(newTask);

		// Redirect to the homepage to see the updated list.
		return "redirect:/";
	}

	@PostMapping("/deleteTask")
	public String deleteTask(@RequestParam("id") Long id) {
		// We use the removeIf method to find and delete the task with the matching ID.
		taskList.removeIf(task -> task.getId().equals(id));

		// Redirect to the homepage to show the updated list.
		return "redirect:/";

	}
	
	@PostMapping("/saveTask")
	public String saveTask(@ModelAttribute Task formTask) {
	    if (formTask.getId() == null) {
	    	Task newTask = new Task(
	    			counter.incrementAndGet(),
	    			formTask.getTitle(),
	    			formTask.getDescription(),
	    			formTask.getStatus(),
	    			LocalDateTime.now()
	    			);
	    	taskList.add(newTask);
	    } else {
	    	Task originalTask = taskList.stream()
	    			.filter(t -> t.getId().equals(formTask.getId()))
	    			.findFirst()
	    			.orElse(null);
	    	if (originalTask != null) {
	    		originalTask.setTitle(formTask.getTitle());
	    		originalTask.setDescription(formTask.getDescription());
	    		originalTask.setStatus(formTask.getStatus());
	    	}
	    }
	    return "redirect:/";
	}
}