package Bellroy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("Read a book");
        assertEquals("[T][ ] Read a book", todo.toString());
        assertFalse(todo.isDone);
    }

    @Test
    public void testMarkDone() {
        Todo todo = new Todo("swim");
        assertFalse(todo.isDone);
        todo.markDone();
        assertTrue(todo.isDone);
    }

}

