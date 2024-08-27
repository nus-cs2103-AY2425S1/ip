package max.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    void testToString() {
        Todo todo = new Todo("Read a book");

        String result = todo.toString();

        assertEquals("[T][ ] Read a book", result);
    }

    @Test
    void testToStringWhenDone() {
        Todo todo = new Todo("Read a book");
        todo.markDone();

        String result = todo.toString();

        assertEquals("[T][X] Read a book", result);
    }
    @Test
    void testToFileFormat() {
        Todo todo = new Todo("Read a book");

        String result = todo.toFileFormat();

        assertEquals("T | 0 | Read a book", result);
    }

    @Test
    void testToFileFormatWhenDone() {
        Todo todo = new Todo("Read a book");
        todo.markDone();

        String result = todo.toFileFormat();

        assertEquals("T | 1 | Read a book", result);
    }
}
