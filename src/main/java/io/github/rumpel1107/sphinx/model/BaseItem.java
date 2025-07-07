package io.github.rumpel1107.sphinx.model;

import java.time.LocalDateTime;

public class BaseItem {

    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime creationDate;

    // No-argument constructor
    public BaseItem() {
    }
    
    // Constructor to initialize all fields
    public BaseItem(Long id, String title, String description, String status, LocalDateTime creationDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.creationDate = creationDate;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return this.status;
    }
    
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
        
    // Setters only for fields that should be modifiable
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}