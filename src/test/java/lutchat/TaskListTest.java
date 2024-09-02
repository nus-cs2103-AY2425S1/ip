package lutchat;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TaskListTest {

    private TaskList taskList;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        ui = new Ui(); // Use a real Ui instance
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Test task 1"));
        tasks.add(new Todo("Test task 2"));
        taskList = new TaskList(tasks);
    }

    @Test
    public void testMarkTaskAsDoneToStringComparison() {
        // Given
        int taskIndex = 0;
        Task task = taskList.getTasks().get(taskIndex);

        // Check initial toString output
        String initialToString = task.toString();

        // When
        taskList.markTaskAsDone(taskIndex, ui);

        // Check if the task is marked as done
        String updatedToString = task.toString();

        // Verify the task's toString output changed correctly
        assertNotEquals(initialToString, updatedToString, "Task's toString output should change after marking as done.");
        assertTrue(updatedToString.contains("[X]"), "Task's toString should contain '[X]' after marking as done.");
    }

    @Test
    public void testMarkTaskAsDoneWithInvalidIndex() {
        // Given
        int invalidIndex = 999; // An index that is out of bounds

        // Store the original state of the tasks list
        ArrayList<Task> originalTasks = new ArrayList<>(taskList.getTasks());

        //When
        taskList.markTaskAsDone(invalidIndex, ui);

        // Verify that the tasks list is unchanged
        assertEquals(originalTasks, taskList.getTasks(), "Task list should remain unchanged when marking with an invalid index.");
    }

    @Test
    public void testMarkTaskAsDoneWhenAlreadyDone() {
        // Given
        int taskIndex = 1; // Assuming this task is initially not done
        taskList.markTaskAsDone(taskIndex, ui); // Mark it as done

        // Store the toString output after marking it as done
        String firstMarkToString = taskList.getTasks().get(taskIndex).toString();

        // When
        taskList.markTaskAsDone(taskIndex, ui); // Try marking it as done again

        // Then
        // Check if the toString output remains correct and does not duplicate the done state
        String secondMarkToString = taskList.getTasks().get(taskIndex).toString();

        // Verify the toString output remains consistent and correct
        assertEquals(firstMarkToString, secondMarkToString, "Task's toString output should remain the same after marking as done again.");
        assertTrue(secondMarkToString.contains("[X]"), "Task's toString should contain '[X]' after marking as done, even if done previously.");
    }
}
