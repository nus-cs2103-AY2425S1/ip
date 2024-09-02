
import echo.EchoException;
import echo.Task;
import echo.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testAddTask() {
        taskList.addTodo("Test Task");
        String des = taskList.getTasks().get(0).getTaskDes();
        assertEquals(des, "Test Task");
    }

    @Test
    void testDeleteTask() throws EchoException {
        taskList.addTodo("Test Task");
        taskList.deleteTask(1);
        assertEquals(0, taskList.size());
    }

    @Test
    void testGetTasks() {
        taskList.addTodo("Test Task");
        List<Task> tasks = taskList.getTasks();
        assertEquals(1, tasks.size());
    }


}
