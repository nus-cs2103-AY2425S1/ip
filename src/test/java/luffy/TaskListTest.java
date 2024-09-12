package luffy;
import luffy.task.Task;
import luffy.task.TaskList;
import luffy.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("read book", false);
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("read book", false);
        taskList.addTask(task);
        taskList.deleteTask(0);
        assertEquals(0, taskList.size());
    }

    @Test
    public void testMarkTaskAsDone() {
        TaskList taskList = new TaskList();
        Task task = new Todo("read book", false);
        taskList.addTask(task);
        task.markDone();
        assertTrue(task.checkIsDone());
    }

    @Test
    public void testUnmarkTaskAsNotDone() {
        TaskList taskList = new TaskList();
        Task task = new Todo("read book", false);
        taskList.addTask(task);
        task.markUndone();
        assertFalse(task.checkIsDone());
    }
}
