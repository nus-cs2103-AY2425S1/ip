package blacknut.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testToFileFormat() {
        Todo todo = new Todo("Buy groceries");
        String expectedOutput = "T | 0 | Buy groceries";
        assertEquals(expectedOutput, todo.toFileFormat());

        todo.markAsDone();
        String expectedOutputAfterDone = "T | 1 | Buy groceries";
        assertEquals(expectedOutputAfterDone, todo.toFileFormat());
    }

    @Test
    public void testToString() {
        Todo todo = new Todo("Buy groceries");
        String expectedOutput = "[T][ ] Buy groceries";
        assertEquals(expectedOutput, todo.toString());

        todo.markAsDone();
        String expectedOutputAfterDone = "[T][X] Buy groceries";
        assertEquals(expectedOutputAfterDone, todo.toString());
    }
}
