package yapyap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void todo_validDescription_todoCreatedSuccessfully() {
        Todo todo = new Todo("read book");
        assertEquals("read book", todo.getDescription());
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void mark_todoMarked_todoMarkedSuccessfully() {
        Todo todo = new Todo("read book");
        todo.mark();
        assertTrue(todo.getIsDone());
        assertEquals("[T][X] read book", todo.toString());
    }

    @Test
    public void unmark_todoUnmarked_todoUnmarkedSuccessfully() {
        Todo todo = new Todo("read book", true);
        assertTrue(todo.getIsDone());
        assertEquals("[T][X] read book", todo.toString());
        todo.unmark();
        assertFalse(todo.getIsDone());
        assertEquals("[T][ ] read book", todo.toString());
    }
}

