package chatbaby;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void addTaskTest() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Test task");
        taskList.addTask(task);

        assertEquals(1, taskList.size());
        assertEquals(task, taskList.taskAt(0));
    }
}
