package io.github.rumpel1107.sphinx;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.rumpel1107.sphinx.model.BaseItem.Priority;
import io.github.rumpel1107.sphinx.model.Task;
import io.github.rumpel1107.sphinx.model.User;
import io.github.rumpel1107.sphinx.repository.TaskRepository;
import io.github.rumpel1107.sphinx.repository.UserRepository;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository; // Injecting the TaskRepository to interact with the database.
    @Autowired
    private UserRepository userRepository; // Injecting the UserRepository to interact with the database.

    @GetMapping
    public String listTasks(Model model) {
    	
        // We pass the user's task to the model, not the whole list.
        model.addAttribute("tasks", taskRepository.findByIsActiveTrue());
        Task newTask = new Task();
        newTask.setDueDate(LocalDate.now().atStartOfDay()); // Default due date is today
        model.addAttribute("taskToProcess", newTask);
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
    	
        // Use the TaskRepository to find the task by ID.
        Task taskToEdit = taskRepository.findById(id)
                .orElse(null); // orElse(null) handles the case where the task is not found
        
        if (taskToEdit == null) {
        	return "redirect:/tasks"; // Redirect to the task list if the task is not found
        }

        // Add the found task to the model to pre-fill the form
        model.addAttribute("tasks", taskRepository.findByIsActiveTrue());
        model.addAttribute("taskToProcess", taskToEdit);
        return "index";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task formTask, @RequestParam(name = "dueDate", required = false) String dueDateString)
    {
    	
    	if (dueDateString != null && !dueDateString.isEmpty())
    	{
    		LocalDate datePart = LocalDate.parse(dueDateString);
    		formTask.setDueDate(datePart.atTime(LocalTime.MAX));
    		}
    	else {
    		formTask.setDueDate(null);
    	}

    	
    	
    	if (formTask.getId() == null) {
    		formTask.setCreationDate(LocalDateTime.now());
            formTask.setPriority(Priority.Medium); // Default priority
            formTask.setActive(true);
            User user = userRepository.findAll().get(0); // Get the first user (mock user)
            formTask.setUser(user);
        }
    	
    	else {
            Task originalTask = taskRepository.findById(formTask.getId()).orElse(null);

            if (originalTask != null && originalTask.isActive()) {
                originalTask.setTitle(formTask.getTitle());
                originalTask.setDescription(formTask.getDescription());
                originalTask.setStatus(formTask.getStatus());
                originalTask.setPriority(formTask.getPriority());
                originalTask.setDueDate(formTask.getDueDate());
            }
        }
    	
        taskRepository.save(formTask);    	
    	return "redirect:/tasks";
        
   }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        // Find the task in the database.
        Task taskToDelete = taskRepository.findById(id)
                .orElse(null); // orElse(null) handles the case where the task is not found

        if (taskToDelete != null) {
            taskToDelete.setActive(false);
            taskRepository.save(taskToDelete); // Save the updated task to mark it as inactive.
        }
        
        return "redirect:/tasks";
    }
}