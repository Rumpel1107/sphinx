package io.github.rumpel1107.sphinx.model;

import java.time.LocalDateTime;

public class Task extends BaseItem {

	private String status;
	
	public Task() {		
	}

	public Task(Long id, String title, String status, LocalDateTime creationDate) {
		this.setId(id);
		this.setTitle(title);
		this.setStatus(status);
		this.setCreationDate(creationDate);
	}
	
	// Getter for the 'status' property
	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

}