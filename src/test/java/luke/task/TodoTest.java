package luke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("read book", false);
        assertEquals("[T][ ] read book", todo.taskDescription());
        assertEquals("- | todo | read book\n", todo.taskInSaveData());
    }

    @Test
    public void testMarkedTodoCreation() {
        Todo todo = new Todo("read book", true);
        assertEquals("[T][X] read book", todo.taskDescription());
        assertEquals("X | todo | read book\n", todo.taskInSaveData());
    }
}
