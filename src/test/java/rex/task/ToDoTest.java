package rex.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testFormattedNotDone() {
        assertEquals("T | 0 | do something", new ToDo("do something", false).formatted());
    }

    @Test
    public void testFormattedDone() {
        assertEquals("T | 1 | do something else", new ToDo("do something else", true).formatted());
    }

    @Test
    void testToStringNotDone() {
        assertEquals("[T][ ] Complete assignment", new ToDo("Complete assignment", false).toString());
    }

    @Test
    void testToStringDone() {
        assertEquals("[T][X] Complete homework", new ToDo("Complete homework", true).toString());
    }
}
