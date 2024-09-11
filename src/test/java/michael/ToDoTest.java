package michael;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoTest {
    @Test
    public void Test1() {
        ToDo task = new ToDo("Test");
        task.doTask();
        assertEquals("[T][X] Test", task.toString());
    }

    @Test
    public void Test2() {
        ToDo task = new ToDo("");
        task.doTask();
        assertTrue(task.isDone());
    }
}
