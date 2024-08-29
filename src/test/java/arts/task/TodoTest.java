package arts.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    private Todo todo;

    @BeforeEach
    public void setUp() {
        todo = new Todo("Buy groceries");
    }

    @Test
    public void testToString() {
        String expected = "[T][ ] Buy groceries";
        assertEquals(expected, todo.toString(), "The string representation of the todo is incorrect.");
    }

    @Test
    public void testToFileFormat() {
        String expected = "T | 0 | Buy groceries";
        assertEquals(expected, todo.toFileFormat(), "The file format of the todo is incorrect.");
    }

    @Test
    public void testMarkAsDone() {
        todo.markAsDone();
        String expected = "[T][X] Buy groceries";
        assertEquals(expected, todo.toString(), "The todo should be marked as done.");
    }
}