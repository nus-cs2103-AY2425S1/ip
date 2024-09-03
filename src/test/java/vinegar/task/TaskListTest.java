package vinegar.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTask_validTask_success() {
        Task task = new Todo("read book");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        assertTrue(taskList.getTasks().contains(task));
    }

    @Test
    public void testMarkTaskAsDone_success() {
        Task task = new Todo("read book");
        taskList.addTask(task);
        task.markAsDone();

        // Test if the task is marked as done
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testMarkTaskAsNotDone_success() {
        Task task = new Todo("read book");
        taskList.addTask(task);
        task.markAsDone();  // Mark as done first
        task.markAsNotDone();  // Then mark as not done

        // Test if the task is marked as not done
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void testAddNullTask_failure() {
        assertThrows(NullPointerException.class, () -> taskList.addTask(null));
    }
}