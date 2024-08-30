package chobo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {
    @Test
    void addTaskTest() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("playgame",false);
        taskList.addTask(task);
        assertEquals(1, taskList.getTotalTask());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    void deleteTaskTest() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("send help", true);
        taskList.addTask(task);
        taskList.deleteTask(0);

        assertEquals(0, taskList.getTotalTask());
    }


}
