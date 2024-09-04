package genji.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test class to test todo tasks
 */
public class ToDoTest {

    /**
     * Tests if the toString method works properly
     * Creating a todo object and compare with expected output
     */
    @Test
    public void testToString() {
        assertEquals("[T][ ] Test",
                new ToDo("Test").toString());
    }

    /**
     * Tests if the toString method works properly
     * Creating a todo object and compare with expected output
     */
    @Test
    public void testToListString() {
        assertEquals("T | 0 | Test",
                new ToDo("Test").toListString());
    }
}
