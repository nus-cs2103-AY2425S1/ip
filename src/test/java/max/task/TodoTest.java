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
    public void testToFileFormat_NotDone_NoTags() {
        Todo todo = new Todo("Test Todo");

        String result = todo.toFileFormat();

        assertEquals("T | 0 | Test Todo", result);
    }

    @Test
    public void testToFileFormat_Done_NoTags() {
        Todo todo = new Todo("Test Todo");
        todo.markDone();

        String result = todo.toFileFormat();

        assertEquals("T | 1 | Test Todo", result);
    }

    @Test
    public void testToFileFormat_NotDone_WithTags() {
        Todo todo = new Todo("Test Todo");
        todo.addTag("important");
        todo.addTag("urgent");

        String result = todo.toFileFormat();

        assertEquals("T | 0 | Test Todo | important,urgent", result);
    }

    @Test
    public void testToFileFormat_Done_WithTags() {
        Todo todo = new Todo("Test Todo");
        todo.markDone();
        todo.addTag("important");
        todo.addTag("urgent");

        String result = todo.toFileFormat();

        assertEquals("T | 1 | Test Todo | important,urgent", result);
    }
}
