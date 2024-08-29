package lict.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@code Todo}.
 */
public class TodoTest {

    /**
     * Tests the {@code toString()} method of the {@code Todo} class.
     * Verifies that the string representation of the task is correct before and after marking it as done.
     */
    @Test
    public void testToString() {
        Task todo = new Todo("Read book");
        assertEquals("[T][ ] Read book", todo.toString());
        todo.isMarked(true);
        assertEquals("[T][X] Read book", todo.toString());
    }

    /**
     * Tests the {@code toData()} method of the {@code Todo} class.
     * Verifies that the data format of the task is correct before and after marking it as done.
     */
    @Test
    public void testToData() {
        Task todo = new Todo("Read book");
        assertEquals("TODO | 0 | Read book\n", todo.toData());
        todo.isMarked(true);
        assertEquals("TODO | 1 | Read book\n", todo.toData());
    }

    /**
     * Tests the {@code loadTask()} method of the {@code Todo} class.
     * Verifies that the task is correctly loaded from a string and its string representation is accurate.
     */
    @Test
    public void testLoadTask() {
        Todo todo = Todo.loadTask("Finish homework");
        assertEquals("[T][ ] Finish homework", todo.toString());
    }
}
