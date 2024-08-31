package weeny;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Sample Task");

        taskList.addTask(task);
        List<Task> tasks = taskList.getTasks();

        assertEquals(1, tasks.size());
        assertEquals("Sample Task", tasks.get(0).getDescription());
        assertFalse(tasks.get(0).isDone);
    }

    @Test
    public void testAddMultipleTasks() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");

        taskList.addTask(task1);
        taskList.addTask(task2);
        List<Task> tasks = taskList.getTasks();

        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getDescription());
        assertEquals("Task 2", tasks.get(1).getDescription());
    }
}
