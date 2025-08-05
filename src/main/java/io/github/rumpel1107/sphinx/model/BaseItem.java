package io.github.rumpel1107.sphinx.model;

import java.time.LocalDateTime;

public abstract class BaseItem {

    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime creationDate;
    private LocalDateTime completionDate;
    private User user;
    
    // No-argument constructor - Required by frameworks.
    public BaseItem() {
    }
    
    // Constructor for subclasses to call via super()
    public BaseItem(String title, String description, String status, User user) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.user = user;
        this.creationDate = LocalDateTime.now(); // Creation date is set automatically.
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
		this.id = id;
	}

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
		this.title = title;
	}

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
		this.description = description;
	}

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
		this.status = status;
	}
    
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(LocalDateTime creationDate) {
    	this.creationDate = creationDate;
    }
    
    public LocalDateTime getCompletionDate() {
		return completionDate;
	}
    
    public void setCompletionDate(LocalDateTime completionDate) {
    	this.completionDate = completionDate;
    }
    
    public User getUser() {
		return user;
	}
    
    public void setUser(User user) {
    	this.user = user;
    }
    
}