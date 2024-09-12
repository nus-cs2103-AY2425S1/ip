package bobby.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bobby.exceptions.InvalidInputException;
import bobby.exceptions.InvalidTaskException;
import bobby.exceptions.InvalidTaskNumberException;
import bobby.tasks.Deadline;
import bobby.tasks.Task;
import bobby.tasks.Todo;


/**
 * Test class for the TaskList class.
 * This class contains unit tests for the methods in the TaskList class.
 */
public class TestTaskList {

    private TaskList taskList;

    @BeforeEach
    public void setup() {
        taskList = new TaskList();
    }

    /**
     * Tests that a task can be successfully added to the task list.
     * Verifies that the task list size increases and the task description is correctly stored.
     *
     * @throws InvalidTaskException if there is an issue with adding the task
     * @throws InvalidTaskNumberException if the task number is invalid during retrieval
     */
    @Test
    public void testAddTask_success() throws InvalidTaskException, InvalidTaskNumberException {
        Task task = new Todo("Test task");
        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals("Test task", taskList.get(0).getDescription());
    }

    /**
     * Tests that adding a duplicate task to the task list throws an InvalidTaskException.
     * A duplicate task is identified by its description.
     *
     * @throws InvalidTaskException if the task already exists in the task list
     */
    @Test
    public void testAddTask_duplicateTask() throws InvalidTaskException {
        Task task = new Todo("Test task");
        taskList.add(task);

        // Expect InvalidTaskException when adding the same task again
        assertThrows(InvalidTaskException.class, () -> taskList.add(task),
                "Expected InvalidTaskException to be thrown, but it wasn't");
    }

    /**
     * Tests that a task can be successfully removed from the task list.
     * Verifies that the task list becomes empty after removing the task.
     *
     * @throws InvalidTaskException if there is an issue with adding the task
     * @throws InvalidTaskNumberException if the task index is invalid during removal
     */
    @Test
    public void testRemoveTask_success() throws InvalidTaskNumberException, InvalidTaskException {
        Task task = new Todo("Test task");
        taskList.add(task);
        Task removedTask = taskList.remove(0);
        assertEquals(task, removedTask);
        assertTrue(taskList.isEmpty());
    }

    /**
     * Tests that attempting to remove a task at an invalid index throws an InvalidTaskNumberException.
     * Verifies that the exception is thrown when trying to remove a task from an empty list.
     */
    @Test
    public void testRemoveTask_invalidIndex() {
        try {
            taskList.remove(0);
            fail("Expected InvalidTaskNumberException to be thrown");
        } catch (InvalidTaskNumberException e) {
            // Exception expected
        }
    }

    /**
     * Tests that tasks with a specific date can be found in the task list.
     * Verifies that tasks with a matching deadline date are retrieved.
     *
     * @throws InvalidTaskException if there is an issue with adding the task
     */
    @Test
    public void testFindTasksByDate() throws InvalidTaskException {
        LocalDate date = LocalDate.of(2023, 9, 12);
        Task deadlineTask = new Deadline("Submit project", date);
        taskList.add(deadlineTask);

        ArrayList<Task> result = taskList.findTasksByDate(date);
        assertEquals(1, result.size());
        assertEquals("Submit project", result.get(0).getDescription());
    }

    /**
     * Tests that tasks containing a specific keyword in their description can be found.
     * Verifies that tasks matching the keyword are correctly retrieved.
     *
     * @throws InvalidTaskException if there is an issue with adding the task
     */
    @Test
    public void testFindTasksByKeyword() throws InvalidTaskException {
        Task task1 = new Todo("Finish homework");
        Task task2 = new Todo("Buy groceries");
        taskList.add(task1);
        taskList.add(task2);

        ArrayList<Task> result = taskList.findTasksByKeyword("homework");
        assertEquals(1, result.size());
        assertEquals("Finish homework", result.get(0).getDescription());
    }


    /**
     * Tests that multiple tasks can be marked as done.
     * Verifies that the tasks are marked correctly using their indices.
     *
     * @throws InvalidInputException if the input index is invalid
     * @throws InvalidTaskNumberException if the task index is invalid
     * @throws InvalidTaskException if there is an issue with adding the task
     */
    @Test
    public void testMarkMultipleTasks() throws InvalidInputException, InvalidTaskNumberException, InvalidTaskException {
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        taskList.add(task1);
        taskList.add(task2);

        ArrayList<Task> markedTasks = taskList.markMultipleTasks(true, "1", "2");
        assertEquals(markedTasks.get(0).getStatusIcon(), "X");
        assertEquals(markedTasks.get(1).getStatusIcon(), "X");
    }

    /**
     * Tests that multiple tasks can be deleted from the task list.
     * Verifies that the tasks are deleted correctly and that the task list is empty afterward.
     *
     * @throws InvalidTaskException if there is an issue with adding the task
     * @throws InvalidTaskNumberException if the task index is invalid during deletion
     * @throws InvalidInputException if the input index is invalid
     */
    @Test
    public void testDeleteMultipleTasks() throws
            InvalidTaskException, InvalidTaskNumberException, InvalidInputException {
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        taskList.add(task1);
        taskList.add(task2);

        ArrayList<Task> deletedTasks = taskList.deleteMultipleTasks("1", "2");
        assertTrue(taskList.isEmpty());
        assertEquals(2, deletedTasks.size());
    }


}
