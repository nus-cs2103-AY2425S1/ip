package devon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void dbReadableFormat_undone_success() {
        Todo todo = new Todo("desc");
        assertEquals("Todo|0|desc", todo.dbReadableFormat());
    }

    @Test
    public void toString_undone_success() {
        Todo todo = new Todo("desc");
        assertEquals("[T][ ] desc", todo.toString());
    }

    @Test
    public void dbReadableFormat_done_success() {
        Todo todo = new Todo("desc");
        todo.markAsDone();
        assertEquals("Todo|1|desc", todo.dbReadableFormat());
    }

    @Test
    public void toString_done_success() {
        Todo todo = new Todo("desc");
        todo.markAsDone();
        assertEquals("[T][X] desc", todo.toString());
    }
}
