package genji.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testToString() {
        assertEquals("[T][ ] Test",
                new ToDo("Test").toString());
    }

    @Test
    public void testToListString() {
        assertEquals("T | 0 | Test",
                new ToDo("Test").toListString());
    }
}
