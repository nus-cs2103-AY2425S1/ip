package krona.task;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ToDoTest {
    @Test
    public void testMarkToDoAsDone() {
        ToDo todo = new ToDo("read a book");
        todo.markDone();
        assertTrue(todo.isDone());
    }

    @Test
    public void testToDoCreation() {
        ToDo todo = new ToDo("read a book");
        assertEquals("read a book", todo.getDescription());
        assertFalse(todo.isDone());
    }

    @Test
    public void testToDoStringRepresentation() {
        ToDo todo = new ToDo("read a book");
        assertEquals("[T][ ] read a book", todo.toString());

        todo.markDone();
        assertEquals("[T][X] read a book", todo.toString());
    }
}
