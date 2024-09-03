import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import skywalker.task.Todo;


public class TodoTest {

    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void testMarkDone() {
        Todo todo = new Todo("read book");
        todo.markDone();
        assertEquals("[T][X] read book", todo.toString());
    }
}
