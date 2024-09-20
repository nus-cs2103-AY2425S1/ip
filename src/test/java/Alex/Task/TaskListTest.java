package Alex.Task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Alex.Exceptions.AlexException;

/**
 * This class contains unit tests for the TaskList class.
 * It verifies that tasks can be added and removed successfully, including
 * different types of tasks such as Todo, Deadline, and Event.
 */
public class TaskListTest {

    /**
     * Tests if a valid Todo task is added successfully to the TaskList.
     * Verifies that the size of the TaskList increases and the correct task is added.
     */
    @Test
    public void addTask_validTodoTask_taskAddedSuccessfully() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("Test todo");
        taskList.addTask(todo);

        assertEquals(1, taskList.size());
        assertEquals(todo, taskList.getTask(0));
    }

    /**
     * Tests if a valid Deadline task is added successfully to the TaskList.
     * Verifies that the size of the TaskList increases and the correct task is added.
     *
     * @throws AlexException if the deadline format is invalid.
     */
    @Test
    public void addTask_validDeadlineTask_taskAddedSuccessfully() throws AlexException {
        TaskList taskList = new TaskList();
        Deadline deadline = new Deadline("Submit report", "2024-09-05 15:55");
        taskList.addTask(deadline);

        assertEquals(1, taskList.size());
        assertEquals(deadline, taskList.getTask(0));
    }

    /**
     * Tests if a valid Event task is added successfully to the TaskList.
     * Verifies that the size of the TaskList increases and the correct task is added.
     */
    @Test
    public void addTask_validEventTask_taskAddedSuccessfully() {
        TaskList taskList = new TaskList();
        Event event = new Event("Project meeting", "2024-09-05 14:00", "2024-09-05 16:00");
        taskList.addTask(event);

        assertEquals(1, taskList.size());
        assertEquals(event, taskList.getTask(0));
    }

    /**
     * Tests if a task is removed successfully from the TaskList.
     * Verifies that the size of the TaskList decreases after the task is removed.
     */
    @Test
    public void removeTask_validTask_taskRemovedSuccessfully() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("Test todo");
        taskList.addTask(todo);
        taskList.deleteTask(0);

        assertEquals(0, taskList.size());
    }
}