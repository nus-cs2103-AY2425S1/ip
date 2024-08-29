package task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Some Task");
        taskList.addTask(task);
        assertEquals(task, taskList.getTasks().get(0));
    }
}
