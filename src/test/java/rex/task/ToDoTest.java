package rex.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testFormattedNotDone() {
        assertEquals("T | 0 | do something", new ToDo("do something", false).formatter());
    }

    @Test
    public void testFormattedDone() {
        assertEquals("T | 1 | do something else", new ToDo("do something else", true).formatter());
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
