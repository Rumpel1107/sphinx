package io.github.rumpel1107.sphinx.model;

import java.time.LocalDateTime;

public class Task extends BaseItem {

	// Fields specific to Task.
	private BaseItem.Priority priority;
	private LocalDateTime dueDate;
	
	// No-argument constructor.
	public Task() {
		super(); // Calls the empty parent constructor
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