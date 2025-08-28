package io.github.rumpel1107.sphinx;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.github.rumpel1107.sphinx.model.BaseItem.Priority;
import io.github.rumpel1107.sphinx.model.Task;

public class TaskTests {
	
	@Test
	public void taskConstructorShouldSetTitle() {
		//Arrange
		String expectedTitle = "Revisar cuentas";
		//Act
		Task newTask = new Task(expectedTitle, "Description", "Pending", null, Priority.Medium, null);
		//Assert
		assertEquals(expectedTitle, newTask.getTitle());
	}
	
	@Test
	public void newTaskShouldHaveMediumPriorityByDefault() {
		//Arrange
		Priority expectedPriority = Priority.Medium;
		//Act
		Task newTask = new Task();
		//Assert
		assertEquals(expectedPriority, newTask.getPriority());
	}

}
