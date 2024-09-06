package elara.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the ToDoTask class.
 */
public class ToDoTest {

    /**
     * Tests that the toFileFormat() method of ToDoTask returns the correct file format.
     * The test checks that a ToDo task with description "read book" and an incomplete status
     * produces the expected string "T | 0 | read book".
     */
    @Test
    public void testFileFormat() {
        assertEquals("T | 0 | read book", new ToDoTask("read book").toFileFormat());
    }
}
