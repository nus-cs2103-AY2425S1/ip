package duck.data;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duck.data.exception.DuckException;
import duck.data.task.Task;
import duck.data.task.TaskStub;
import duck.storage.Storage;
import duck.storage.StorageStub;

/**
 * Tests the TaskList class.
 */
public class TaskListTest {

    private Storage storage;

    /**
     * Sets up the test environment.
     */
    @BeforeEach
    void setUp() {
        try {
            storage = new StorageStub();
        } catch (DuckException e) {
            e.printStackTrace();
            fail("Failed to create storage stub.");
        }
    }

    /**
     * Tests the addTask method of the TaskList class with valid arguments.
     */
    @Test
    public void addTask_taskStub_success() {
        TaskList taskList = new TaskList();

        int initialSize = taskList.size();

        Task task = new TaskStub();
        assertDoesNotThrow(() -> {
            taskList.addTask(task, storage);
        });

        int finalSize = taskList.size();
        assertEquals(initialSize, finalSize - 1);
    }

    /**
     * Tests the updateTaskStatus method of the TaskList class with invalid index.
     */
    @Test
    public void updateTaskStatus_invalidIndex_exceptionThrown() {
        TaskList taskList = new TaskList(); // empty task list
        assertThrows(DuckException.class, () -> {
            taskList.updateTaskStatus(1, true);
        });
    }

    /**
     * Tests the updateTaskStatus method of the TaskList class with valid arguments.
     */
    @Test
    public void updateTaskStatus_taskStub_success() {
        Task task = new TaskStub();
        TaskList taskList = new TaskList();

        // List's add method. Should not throw any exceptions.
        taskList.add(task);

        assertDoesNotThrow(() -> {
            taskList.updateTaskStatus(0, true);
        });
    }


    /**
     * Tests the deleteTask method of the TaskList class with invalid index.
     */
    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        TaskList taskList = new TaskList(); // empty task list
        assertThrows(DuckException.class, () -> {
            taskList.deleteTask(1, storage);
        });
    }

    /**
     * Tests the deleteTask method of the TaskList class with valid arguments.
     */
    @Test
    public void deleteTask_taskStub_success() {

        TaskList taskList = new TaskList();
        Task task = new TaskStub();
        // List's add method. Should not throw any exceptions.
        taskList.add(task);

        int initialSize = taskList.size();
        assertDoesNotThrow(() -> {
            taskList.deleteTask(0, storage);
        });
        int finalSize = taskList.size();
        assertEquals(initialSize, finalSize + 1);
    }


    /**
     * Tests the findTasks method of the TaskList class finding correct number of tasks
     */
    @Test
    public void findTasks() {
        TaskList taskList = new TaskList();
        Task task1 = new TaskStub();
        Task task2 = new TaskStub();
        Task task3 = new TaskStub();

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        TaskList foundTasks = taskList.findTasks("invalid");
        TaskList foundTasks1 = taskList.findTasks("Stub");
        TaskList foundTasks2 = taskList.findTasks("taskStub");

        assertEquals(0, foundTasks.size());
        assertEquals(3, foundTasks1.size());
        assertEquals(3, foundTasks2.size());
    }

    /**
     * Tests the sortTasks method of the TaskList class with null arguments.
     */
    @Test
    public void sortTasks_nullComparator_exceptionThrown() {
        TaskList taskList = new TaskList();
        assertThrows(DuckException.class, () -> {
            taskList.sortTasks(null, "target");
        });
    }
}
