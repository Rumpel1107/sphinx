package io.github.rumpel1107.sphinx.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;


@Entity
public class Task extends BaseItem {

	// Fields specific to Task.
	private BaseItem.Priority priority;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dueDate;

	@ManyToMany
	private List<User> assignedUsers = new ArrayList<>(); // List of users assigned to this task
	
	// No-argument constructor.
	public Task() {
		super(); // Calls the empty parent constructor
		this.priority = BaseItem.Priority.Medium; // Default priority
		}
	
	// Constructor with arguments
	public Task (String title, String description, String status, User user, BaseItem.Priority priority, LocalDateTime dueDate) {
		
		// Call the parent constructor to set the common fields
		super(title, description, status, user);
		
		// Set the specific fields for Task
		this.priority = priority;
		this.dueDate = dueDate;
	}
	
	// Getters and Setters for Task-specific fields
	public BaseItem.Priority getPriority() {
		return priority;
	}
	
	public void setPriority(BaseItem.Priority priority) {
		this.priority = priority;
	}
	
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

}