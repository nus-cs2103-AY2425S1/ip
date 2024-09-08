package elysia.tasks;

import java.lang.IndexOutOfBoundsException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link TaskList} class.
 * Tests the functionality of adding and deleting tasks, as well as handling exceptions.
 */
public class TaskListTest {
    private TaskList taskList;

    /**
     * Sets up the test environment before each test is run.
     * Initializes a new {@link TaskList} instance.
     */
    @BeforeEach
    void setup() {
        taskList = new TaskList();
    }

    /**
     * Tests that a task is added correctly to the task list.
     * Verifies that the task list size increases and the task is added at the correct position.
     */
    @Test
    void addTask_correctTask_taskAdded() {
        Task newTask = new Task("task");
        taskList.addTask(newTask);
        assertEquals(1, taskList.list.size());
        assertEquals(newTask, taskList.list.get(0));
    }

    /**
     * Tests the deletion of a task from the task list using a correct index.
     * Verifies that the task is removed and the task list size decreases.
     */
    @Test
    void deleteTask_correctIndex_taskDeleted() {
        Task newTask = new Task("task");
        taskList.addTask(newTask);
        assertEquals(newTask, taskList.deleteTask(1));
        assertEquals(0, taskList.list.size());
    }

    /**
     * Tests the deletion of a task from the task list using an incorrect index.
     * Verifies that an {@link IndexOutOfBoundsException} is thrown when trying to delete a task with an invalid index.
     */
    @Test
    void deleteTask_wrongIndex_exceptionThrown() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(1));
    }
}
