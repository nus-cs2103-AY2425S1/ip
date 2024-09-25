package tasklisttest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasklist.TaskList;
import task.Task;
import todo.ToDo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@code TaskList} class.
 * <p>
 * This class contains tests for the methods in the {@code TaskList} class, including adding and deleting tasks,
 * and handling invalid operations.
 * </p>
 */
public class TaskListTest {
    private ArrayList<Task> taskList;

    /**
     * Initializes a new {@code ArrayList} of {@code Task} objects before each test.
     */
    @BeforeEach
    public void setUp() {
        taskList = new ArrayList<>();
    }

    /**
     * Tests that the size of the task list increases when a {@code ToDo} task is created.
     * <p>
     * Also verifies that the name of the newly created task is correct.
     * </p>
     */
    @Test
    public void testCreateToDo() {
        String taskName = "Read a book";
        int initialSize = taskList.size();

        TaskList.createToDo(taskName, taskList);

        assertEquals(initialSize + 1, taskList.size());
        assertEquals(taskName, taskList.get(0).getName());
    }

    /**
     * Tests that the size of the task list increases when a {@code ToDo} task is created.
     * <p>
     * Also verifies that the name of the newly created task is correct.
     * </p>
     */
    @Test
    public void testDeleteEvent() {
        ToDo task1 = new ToDo("Task 1");
        ToDo task2 = new ToDo("Task 2");
        taskList.add(task1);
        taskList.add(task2);
        int initialSize = taskList.size();

        TaskList.deleteEvent(0, taskList);

        assertEquals(initialSize - 1, taskList.size());
        assertEquals(task2, taskList.get(0));
    }

    /**
     * Tests that attempting to delete a task with an invalid index throws an {@code IndexOutOfBoundsException}.
     * <p>
     * This verifies that the method handles invalid indices appropriately.
     * </p>
     */
    @Test
    public void testDeleteEventWithInvalidIndex() {
        ToDo task = new ToDo("Task 1");
        taskList.add(task);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            TaskList.deleteEvent(2, taskList);
        });
    }
}
