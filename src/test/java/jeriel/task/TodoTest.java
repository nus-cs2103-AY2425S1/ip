package jeriel.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("Finish homework");
        assertEquals("Finish homework", todo.getDescription());
        assertFalse(todo.isDone());
    }

    @Test
    public void testMarkAsDone() {
        Todo todo = new Todo("Finish homework");
        todo.markAsDone();
        assertTrue(todo.isDone());
    }

    @Test
    public void testToString() {
        Todo todo = new Todo("Finish homework");
        assertEquals("[T][ ] Finish homework", todo.toString());
        
        todo.markAsDone();
        assertEquals("[T][X] Finish homework", todo.toString());
    }

    @Test
    public void testToSaveFormat() {
        Todo todo = new Todo("Finish homework");
        assertEquals("T | 0 | Finish homework", todo.toSaveFormat());
        
        todo.markAsDone();
        assertEquals("T | 1 | Finish homework", todo.toSaveFormat());
    }
}
