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
        int taskIndex = 0;
        Task task = taskList.getTasks().get(taskIndex);

        String initialToString = task.toString();

        taskList.markTaskAsDone(taskIndex, ui);

        String updatedToString = task.toString();

        assertNotEquals(initialToString, updatedToString, "Task's toString output should change after marking as done.");
        assertTrue(updatedToString.contains("[X]"), "Task's toString should contain '[X]' after marking as done.");
    }

    @Test
    public void testMarkTaskAsDoneWithInvalidIndex() {
        // Given
        int invalidIndex = 999; // An index that is out of bounds

        ArrayList<Task> originalTasks = new ArrayList<>(taskList.getTasks());

        taskList.markTaskAsDone(invalidIndex, ui);

        assertEquals(originalTasks, taskList.getTasks(), "Task list should remain unchanged when marking with an invalid index.");
    }

    @Test
    public void testMarkTaskAsDoneWhenAlreadyDone() {
        int taskIndex = 1;
        taskList.markTaskAsDone(taskIndex, ui);

        String firstMarkToString = taskList.getTasks().get(taskIndex).toString();

        taskList.markTaskAsDone(taskIndex, ui);

        String secondMarkToString = taskList.getTasks().get(taskIndex).toString();

        assertEquals(firstMarkToString, secondMarkToString, "Task's toString output should remain the same after marking as done again.");
        assertTrue(secondMarkToString.contains("[X]"), "Task's toString should contain '[X]' after marking as done, even if done previously.");
    }
}
