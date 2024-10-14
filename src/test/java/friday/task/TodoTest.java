package friday.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    void markAsDone_setsTaskAsDone() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        Assertions.assertTrue(todo.isDone());
    }

    @Test
    void toFileFormat_returnsCorrectFormat() {
        Todo todo = new Todo("read book");
        Assertions.assertEquals("T | 0 | read book", todo.toFileFormat());
    }

    @Test
    void toString_returnsCorrectString() {
        Todo todo = new Todo("read book");
        Assertions.assertEquals("[T][ ] read book", todo.toString());
    }
}
