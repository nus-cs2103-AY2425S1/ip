package task;

import exception.IndexOutOfBoundsKukiShinobuException;
import org.junit.jupiter.api.Test;
import task.tags.Tags;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@link TaskList} class.
 */
public class TaskListTest {

    /**
     * Tests adding a task to the {@link TaskList}.
     * Ensures that the task is added successfully and retrievable from the list.
     */
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Some Task", new HashSet<>());
        taskList.addTask(task);
        assertEquals(task, taskList.getTasks().get(0));
    }

    /**
     * Tests deleting a task from the {@link TaskList}.
     * Ensures that the task is removed from the list and the remaining tasks are shifted correctly.
     */
    @Test
    public void testDeleteTask() throws IndexOutOfBoundsKukiShinobuException {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("Task 1", new HashSet<>());
        Task task2 = new Todo("Task 2", new HashSet<>());
        taskList.addTask(task1);
        taskList.addTask(task2);

        assertEquals(1, taskList.getTasks().size()); // Ensure task1 is deleted
        assertEquals(task2, taskList.getTasks().get(0)); // task2 should now be at index 0
    }
}
