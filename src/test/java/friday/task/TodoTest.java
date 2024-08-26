package friday.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void markAsDone_setsTaskAsDone() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        assertTrue(todo.isDone());
    }

    @Test
    void toFileFormat_returnsCorrectFormat() {
        Todo todo = new Todo("read book");
        assertEquals("T | 0 | read book", todo.toFileFormat());
    }

    @Test
    void toString_returnsCorrectString() {
        Todo todo = new Todo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }
}
