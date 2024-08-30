package darkpool.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testToString() {

        Todo todo = new Todo("Complete assignment", false);
        assertEquals("[T][ ] Complete assignment", todo.toString());

        Todo todoDone = new Todo("Read a book", true);
        assertEquals("[T][X] Read a book", todoDone.toString());
    }

    @Test
    public void testToFileString() {

        Todo todo = new Todo("Complete assignment", false);
        assertEquals("T | 0 | Complete assignment\n", todo.toFileString());

        Todo todoDone = new Todo("Read a book", true);
        assertEquals("T | 1 | Read a book\n", todoDone.toFileString());
    }

}