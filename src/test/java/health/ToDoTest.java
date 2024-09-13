package health;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testSaveToFileFormat() {
        ToDo todo = new ToDo("Finish homework");
        String expected = "T | 0 | Finish homework";
        Assertions.assertEquals(expected, todo.saveToFileFormat());
    }

    @Test
    public void testToString() {
        ToDo todo = new ToDo("Finish homework");
        String expected = "[T][ ] Finish homework";
        Assertions.assertEquals(expected, todo.toString());
    }
}
