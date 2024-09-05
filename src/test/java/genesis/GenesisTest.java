package genesis;
import org.junit.jupiter.api.Test;
import task.Task;
import taskmanager.TaskManager;
import todo.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class GenesisTest {
    private TaskManager taskManager;


    /*@Test
    public void testAddTask() {
        Task todo = new Todo("Test Task", "todo Test Task");
        taskManager = new TaskManager();
        taskManager.addTask(todo, true);
        assertEquals(1, taskManager.getTasks().size());
        assertEquals("Test Task", taskManager.getTasks().get(0).description);
    }

    @Test
    public void testMarkTask() {
        Task todo = new Todo("Test Task", "todo Test Task");
        taskManager = new TaskManager();
        taskManager.addTask(todo, true);
        taskManager.markTask(0);
        assertTrue(taskManager.getTasks().get(0).isComplete);
    }*/
}
