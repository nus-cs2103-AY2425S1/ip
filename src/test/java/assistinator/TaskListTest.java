package assistinator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import assistinator.tasks.EventTask;
import assistinator.tasks.Task;
import assistinator.tasks.TodoTask;


/**
 * Test class for the TaskList class.
 * This class contains unit tests to ensure that the TaskList methods
 * function correctly for various scenarios.
 */
class TaskListTest {

    private TaskList taskList;

    /**
     * Sets up a new TaskList instance before each test.
     */
    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    /**
     * Tests that a task can be added to the task list.
     */
    @Test
    void testAddTask() {
        Task task = new TodoTask("Test task");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.getTasks().get(0));
    }

    /**
     * Tests that a task can be deleted from the task list.
     * @throws AssistinatorException if the task deletion fails
     */
    @Test
    void testDeleteTask() throws AssistinatorException {
        Task task = new TodoTask("Test task");
        taskList.addTask(task);
        taskList.deleteTask(0);
        assertEquals(0, taskList.size());
    }

    /**
     * Tests that deleting a task with an invalid index throws an AssistinatorException.
     */
    @Test
    void testDeleteTaskInvalidIndex() {
        assertThrows(AssistinatorException.class, () -> taskList.deleteTask(0));
    }

    /**
     * Tests that a task can be marked as done and undone.
     * @throws AssistinatorException if the task marking fails
     */
    @Test
    void testMarkTask() throws AssistinatorException {
        Task task = new TodoTask("Test task");
        taskList.addTask(task);
        taskList.markTask(0, true);
        assertTrue(taskList.getTasks().get(0).isDone());
        taskList.markTask(0, false);
        assertFalse(taskList.getTasks().get(0).isDone());
    }

    /**
     * Tests that marking a task with an invalid index throws an AssistinatorException.
     */
    @Test
    void testMarkTaskInvalidIndex() {
        assertThrows(AssistinatorException.class, () -> taskList.markTask(0, true));
    }

    /**
     * Tests that listTasks() returns the correct message for an empty task list.
     */
    @Test
    void testListTasksEmpty() {
        assertEquals("Task list is empty.", taskList.listTasks());
    }

    /**
     * Tests that listTasks() returns the correct formatted string for a non-empty task list.
     */
    @Test
    void testListTasksNonEmpty() {
        taskList.addTask(new TodoTask("Task 1"));
        taskList.addTask(new TodoTask("Task 2"));
        String expected = "1.[T][ ] Task 1\n2.[T][ ] Task 2";
        assertEquals(expected, taskList.listTasks());
    }

    /**
     * Tests that filterTasks() correctly filters tasks based on a keyword.
     */
    @Test
    void testFilterTasks() {
        taskList.addTask(new TodoTask("Buy groceries"));
        taskList.addTask(new TodoTask("Do laundry"));
        taskList.addTask(new TodoTask("Buy new shoes"));
        String filtered = taskList.filterTasks("Buy");
        assertTrue(filtered.contains("Buy groceries"));
        assertTrue(filtered.contains("Buy new shoes"));
        assertFalse(filtered.contains("Do laundry"));
    }

    /**
     * Tests that hasTimeClash() correctly identifies time clashes between event tasks.
     */
    @Test
    void testHasTimeClash() {
        LocalDateTime now = LocalDateTime.now();
        EventTask event1 = new EventTask("Event 1", now, now.plusHours(2));
        EventTask event2 = new EventTask("Event 2", now.plusHours(1), now.plusHours(3));
        EventTask event3 = new EventTask("Event 3", now.plusHours(3), now.plusHours(4));

        taskList.addTask(event1);
        assertEquals(event1, taskList.hasTimeClash(event2));
        assertNull(taskList.hasTimeClash(event3));
    }

    /**
     * Tests that hasTimeClash() returns null when checking a TodoTask against an EventTask.
     */
    @Test
    void testHasTimeClashWithTodoTask() {
        LocalDateTime now = LocalDateTime.now();
        EventTask event = new EventTask("Event", now, now.plusHours(2));
        TodoTask todo = new TodoTask("Todo task");

        taskList.addTask(event);
        assertNull(taskList.hasTimeClash(todo));
    }
}
