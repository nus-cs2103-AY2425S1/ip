package lama;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lama.task.Task;
import lama.task.Todo;




/**
 * Test class for TaskList.
 * Contains unit test cases for TaskList class.
 */
public class TaskListTest {

    /**
     * Tests instantiation of an empty TaskList.
     * Verifies that a task list created without parameters has a size of 0.
     */
    @Test
    public void instantiateEmptyTaskListTest() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
    }

    /**
     * Tests the instantiation of TaskList with a given list of tasks.
     * Verifies that the task list is correctly initialized with the provided tasks.
     */
    @Test
    public void instantiateGivenTaskListTest() {
        ArrayList<Task> given = new ArrayList<>();
        Task todo = new Todo("Read Book");
        given.add(todo);
        TaskList taskList = new TaskList(given);
        assertEquals(1, taskList.size());
        assertEquals(todo, taskList.get(0));
    }

    /**
     * Tests the size method of TaskList.
     * Verifies that the size of a new task list is 0.
     */
    @Test
    public void sizeTest() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
    }

    /**
     * Tests the add method of TaskList.
     * Verifies that task is correctly added to the task list.
     */
    @Test
    public void addTest() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Read Book");
        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    /**
     * Tests the get method of TaskList.
     * Verifies that a task can be correctly get from the task list.
     */
    @Test
    public void getTest() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Read Book");
        taskList.add(task);
        assertEquals(task, taskList.get(0));
    }

    /**
     * Tests the get method of TaskList.
     * Verifies that a task can be correctly get from the task list.
     */
    @Test
    public void getFailTest() {
        TaskList taskList = new TaskList();

        assertThrows(AssertionError.class, () -> {
            taskList.add(null);
        });
    }
    /**
     * Tests the add method of TaskList.
     * Verifies that an AssertionError is thrown when a null task is added.
     */
    @Test
    public void removeTest() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Read Book");
        taskList.add(task);
        assertEquals(task, taskList.get(0));
        Task taskRemoved = taskList.remove(0);
        assertEquals(task, taskRemoved);
    }

    /**
     * Test case for find by keyword.
     * Verifies that TaskList with the keywords will be returned.
     */
    @Test
    public void findTest() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Read Book");
        Task task2 = new Todo("Reading");
        taskList.add(task);
        taskList.add(task2);
        assertEquals(task, taskList.find("Read").get(0));
        assertEquals(task2, taskList.find("Read").get(1));
    }
}
