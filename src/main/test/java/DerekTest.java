import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DerekTest {

    private Derek derek;

    @Before
    public void setUp() {
        derek = new Derek();
    }

    @Test
    public void testMarkCompleted() {
        // Arrange
        derek.addTask(new Command("todo Test Task"));
        int initialSize = derek.getTaskList().size();

        // Act
        derek.markCompleted(1);

        // Assert
        assertTrue(derek.getTaskList().get(0).toString().contains("[X]")); // The task should be marked as completed.
        assertEquals(initialSize, derek.getTaskList().size()); // Task list size should remain the same after marking complete.
    }

    @Test
    public void testAddTask() {
        // Arrange
        int initialSize = derek.getTaskList().size();
        Command command = new Command("todo Test Task");

        // Act
        derek.addTask(command);

        // Assert
        assertEquals(initialSize + 1, derek.getTaskList().size()); // Task list size should increase by 1 after adding a task.
        assertEquals("[T][ ] Test Task", derek.getTaskList().get(0).toString()); // The first task should be the one added.
    }

    @Test
    public void testDeleteTask() {
        // Arrange
        derek.addTask(new Command("todo Task to be deleted"));
        int initialSize = derek.getTaskList().size();

        // Act
        derek.deleteTask(1);

        // Assert
        assertEquals(initialSize - 1, derek.getTaskList().size()); // Task list size should decrease by 1 after deletion.
        assertTrue(derek.getTaskList().isEmpty()); // The task list should be empty after deletion.
    }
}
