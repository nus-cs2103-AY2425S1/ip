package stan;
import stan.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testAddTask() {
        Task task = new MockTask("Test Task");
        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    @Test
    void testRemoveTask() {
        Task task1 = new MockTask("Task 1");
        Task task2 = new MockTask("Task 2");
        taskList.add(task1);
        taskList.add(task2);
        Task removedTask = taskList.remove(0);
        assertEquals(1, taskList.size());
        assertEquals(task1, removedTask);
        assertEquals(task2, taskList.get(0));
    }

    @Test
    void testGetTask() {
        Task task = new MockTask("Test Task");
        taskList.add(task);
        assertEquals(task, taskList.get(0));
    }

    @Test
    void testSize() {
        assertEquals(0, taskList.size());
        taskList.add(new MockTask("Task 1"));
        assertEquals(1, taskList.size());
    }

    @Test
    void testRemoveTaskInvalidIndex() {
        taskList.add(new MockTask("Task 1"));
        taskList.add(new MockTask("Task 2"));
        assertEquals(2, taskList.size());

        try {
            taskList.remove(2); // This should throw an IndexOutOfBoundsException
            fail("Expected IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            assertEquals(2, taskList.size()); // Ensure size has not changed
        }
    }
}
