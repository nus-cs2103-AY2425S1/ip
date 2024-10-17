package ollie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ollie.exception.OllieException;

public class TaskListTest {
    private TaskList taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        task1 = new Todo("Read book");
        task2 = new Todo("Write code");

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        taskList = new TaskList(tasks, null);
    }

    /**
     * Tests the adding of a task to the task list.
     * Verifies that the task list is initialized with the provided tasks.
     */
    @Test
    public void constructorWithTasks_tasksProvided_tasksInitialized() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        TaskList newList = new TaskList(tasks, null);

        assertEquals(1, newList.getTasks().size());
        assertEquals("Read book", newList.getTasks().get(0).getDescription());
    }

    /**
     * Tests the {@link TaskList#addTask(Task)} method.
     * Verifies that a new task can be added to the task list.
     */
    @Test
    public void addTask_newTask_taskAdded() {
        Task newTask = new Todo("Go jogging");
        String result = taskList.addTask(newTask);

        assertEquals(3, taskList.getTasks().size());
        assertEquals("Go jogging", taskList.getTasks().get(2).getDescription());
        assertTrue(result.contains("added"));
    }

    /**
     * Tests the {@link TaskList#markTaskAsDone(int)} method.
     * Verifies that a task can be marked as done based on the task number.
     */
    @Test
    public void markTaskAsDone_invalidTaskNumber_errorMessageReturned() {
        String result = taskList.markTaskAsDone(5);

        assertEquals("Please enter a valid task number within the list ☺", result);
    }

    /**
     * Tests the {@link TaskList#unmarkTaskAsDone(int)} method.
     * Verifies that a task can be unmarked as done based on the task number.
     */
    @Test
    public void unmarkTaskAsDone_invalidTaskNumber_errorMessageReturned() throws OllieException {
        String result = taskList.unmarkTaskAsDone(5);

        assertEquals("Please enter a valid task number within the list ☺", result);
    }

    /**
     * Tests the {@link TaskList#deleteTask(int)} method.
     * Verifies that a task can be deleted based on the task number.
     */
    @Test
    public void deleteTask_invalidTaskNumber_errorMessageReturned() throws OllieException {
        String result = taskList.deleteTask(5);

        assertEquals("Please enter a valid task number within the list ☺", result);
    }

    /**
     * Tests the {@link TaskList#findTasksByKeyword(String)} method.
     * Verifies that tasks containing the keyword are returned.
     */
    @Test
    public void findTasksByKeyword_keywordMatches_tasksReturned() {
        String result = taskList.findTasksByKeyword("book");

        assertTrue(result.contains("Read book"));
    }

    /**
     * Tests the {@link TaskList#findTasksByKeyword(String)} method.
     * Verifies that no tasks are returned when the keyword does not match any tasks.
     */
    @Test
    public void addTaskWithoutMessage_newTask_taskAddedWithoutMessage() {
        Task newTask = new Todo("Go jogging");
        taskList.addTaskWihoutMessage(newTask);

        assertEquals(3, taskList.getTasks().size());
        assertEquals("Go jogging", taskList.getTasks().get(2).getDescription());
    }
}
