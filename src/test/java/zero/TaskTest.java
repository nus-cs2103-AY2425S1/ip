package zero;

import zero.task.Task;
import zero.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testTask() {
        Task task = new Task("Test task");
        assertEquals("[ ] Test task", task.toString());
    }

    @Test
    public void testTodo() {
        Todo todo = new Todo("Study");
        assertEquals("[T][ ] Study", todo.toString());
    }


}