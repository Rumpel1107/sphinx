package io.github.rumpel1107.sphinx.model;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseItem {
	
	// The Priority enum is defined here, making it accessible to all subclasses.
	public enum Priority {
		HIGH, MEDIUM, LOW
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime creationDate;
    private LocalDateTime completionDate;
    @ManyToOne
    private User user; // This field represents the user who created the item.
    
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