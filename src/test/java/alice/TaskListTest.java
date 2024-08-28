package alice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void markTaskTest() {
        Todo todo = new Todo("description");
        TaskList list = new TaskList();
        list.addToList(todo);
        list.markTask(1);
        assertEquals(list.getTask().get(0).toString(), "[T][X] description");
    }
}
