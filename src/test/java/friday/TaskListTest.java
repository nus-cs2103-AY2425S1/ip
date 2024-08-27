package friday;

import friday.task.Task;
import friday.task.Todo;
import friday.util.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        taskList.addTask(new Todo("Task 1"));
        taskList.addTask(new Todo("Task 2"));
    }

    @Test
    public void testDeleteTask_ValidIndex() {
        Task removedTask = taskList.deleteTask(0);
        assertNotNull(removedTask);
        assertEquals("Task 1", removedTask.getDescription());
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void testDeleteTask_InvalidIndex() {
        Task removedTask = taskList.deleteTask(5);
        assertNull(removedTask);
        assertEquals(2, taskList.getSize());
    }
}

