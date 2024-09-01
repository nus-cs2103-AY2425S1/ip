package alice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Todo} class.
 * Verifies that the Todo class correctly handles task saving functionality.
 */
public class TodoTest {
    /**
     * Tests the functionality of the {@link Todo#saveString()} method.
     * <p>
     * This test creates a new {@link Todo} task with a description and verifies that the
     * saved string representation of the task matches the expected format.
     * The expected format for a new, not done task is "T | 0 | description".
     * </p>
     */
    @Test
    public void saveStringTest() {
        Todo todo = new Todo("description");
        assertEquals(todo.saveString(), "T | 0 | description");
    }
}
