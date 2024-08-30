package wenjieBot.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    private ToDo toDo;

    @BeforeEach
    public void setUp() {
        toDo = new ToDo("Complete homework");
    }

    @Test
    public void testToPrettierString() {
        // Assuming that the base class Task.toPrettierString() returns "[ ] Complete homework"
        String expected = "T | 0 | Complete homework";
        assertEquals(expected, toDo.toPrettierString());
    }

    @Test
    public void testToString() {
        // Assuming that the base class Task.toString() returns "[ ] Complete homework"
        String expected = "[T][ ] Complete homework";
        assertEquals(expected, toDo.toString());
    }
}
