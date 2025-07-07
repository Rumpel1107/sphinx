package io.github.rumpel1107.sphinx.model;

import java.time.LocalDateTime;

public class Task extends BaseItem {
	
	public Task() {
		super(); // Calls the empty parent constructor
		}
	
	// Constructor with arguments
	public Task (Long id, String title, String description, String status, LocalDateTime creationDate){
		
		// Call the parent constructor to set the common fields
		super(id, title, description, status, creationDate);
		}
}