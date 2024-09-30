package BRMO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import BRMO.task.Todo;

public class TodoTest {
    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("Read a book");
        assertEquals("[T][ ] Read a book", todo.toString());
        assertEquals("T | 0 | Read a book", todo.toText());
    }

    @Test
    public void testMarkAsDone() {
        Todo todo = new Todo("Read a book");

        for (int i = 0; i < 2; i++) {
            assertEquals("[T][ ] Read a book", todo.toString());
            assertEquals("T | 0 | Read a book", todo.toText());
            todo.mark();

            assertEquals("[T][X] Read a book", todo.toString());
            assertEquals("T | 1 | Read a book", todo.toText());
            todo.unmark();
        }
    }
}