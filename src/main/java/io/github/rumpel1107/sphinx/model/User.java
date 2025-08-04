package io.github.rumpel1107.sphinx.model;
import java.util.ArrayList;
import java.util.List;

public class User {
	
	private Long id;
	private String name;
	private String email;
	private String username;
	private String password;
	
	// This represents the "one-to-many" relationship: one User can have many Tasks.
	private List<Task> tasks = new ArrayList<>();
	
	// No-argument constructor (good practice, especially for frameworks)
	public User() {
	}
	
	// Constructor with essential fields
	public User(Long id, String name, String email, String username, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	// Getters
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	// Setters
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
}
