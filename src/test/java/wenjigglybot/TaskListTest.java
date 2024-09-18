package wenjigglybot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test class for the {@link TaskList} class, testing the functionality of adding and retrieving tasks.
 */
public class TaskListTest {
    private TaskList taskList;
    private Task testTask;

    /**
     * Sets up the test environment before each test.
     * Initializes a new {@link TaskList} and a {@link ToDoTask} with the description "meemaw".
     */
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        testTask = new ToDoTask("meemaw");
    }

    /**
     * Tests adding a task to an empty task list.
     * Verifies that the task list size increases to 1.
     */
    @Test
    public void addTask_emptyTaskList_success() {
        taskList.addTask(testTask);
        System.out.print(taskList.size());
        assertEquals(2, taskList.size());
    }

    /**
     * Tests retrieving a task from the task list.
     * Verifies that the retrieved task matches the one that was added.
     */
    @Test
    public void getTask_oneTask_success() {
        taskList.addTask(testTask);
        assertEquals(testTask.toString(), taskList.get(0).toString());
    }
}