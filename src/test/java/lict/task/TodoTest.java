package lict.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testToString() {
        Task todo = new Todo("Read book");
        assertEquals("[T][ ] Read book", todo.toString());
        todo.isMarked(true);
        assertEquals("[T][X] Read book", todo.toString());
    }

    @Test
    public void testToData() {
        Task todo = new Todo("Read book");
        assertEquals("TODO | 0 | Read book\n", todo.toData());
        todo.isMarked(true);
        assertEquals("TODO | 1 | Read book\n", todo.toData());
    }

    @Test
    public void testLoadTask() {
        Todo todo = Todo.loadTask("Finish homework");
        assertEquals("[T][ ] Finish homework", todo.toString());
    }
}
