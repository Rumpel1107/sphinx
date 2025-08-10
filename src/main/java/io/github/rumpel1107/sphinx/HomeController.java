package io.github.rumpel1107.sphinx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.rumpel1107.sphinx.model.Task;
import io.github.rumpel1107.sphinx.repository.TaskRepository;

@Controller
public class HomeController {
	
	@Autowired
	private TaskRepository taskRepository; // Injecting the TaskRepository to interact with the database.

	@GetMapping("/")
	public String home(Model model) {
		// We pass the user's task to the model, not the whole list.
		model.addAttribute("tasks", taskRepository.findAll());
		model.addAttribute("taskToProcess", new Task());
		return "index";
	}
	
	@GetMapping("/editTask/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		// Use the TaskRepository to find the task by ID.
		Task taskToEdit = taskRepository.findById(id)
				.orElse(null); // orElse(null) handles the case where the task is not found
		
		// Add the found task to the model to pre-fill the form
		model.addAttribute("tasks", taskRepository.findAll());
		model.addAttribute("taskToProcess", taskToEdit);
		return "index";
	}

	@PostMapping("/saveTask")
	public String saveTask(@ModelAttribute Task formTask) {
		// The .save() method will either insert a new task or update an existing one based on the ID.
		taskRepository.save(formTask);
	    return "redirect:/";
	}
	
	@PostMapping("/deleteTask")
	public String deleteTask(@RequestParam("id") Long id) {
		taskRepository.deleteById(id);
		return "redirect:/";
	}

}