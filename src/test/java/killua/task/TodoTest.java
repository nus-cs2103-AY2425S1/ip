package killua.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testTodoToSave() {
        Todo todo = new Todo("Sample Task");
        String expectedSaveFormat = "T | 0 | Sample Task";
        assertEquals(expectedSaveFormat, todo.toSave());
    }

    @Test
    public void testTodoToString() {
        Todo todo = new Todo("Sample Task");
        String expectedStringFormat = "[T][ ] Sample Task";
        assertEquals(expectedStringFormat, todo.toString());
    }
}
