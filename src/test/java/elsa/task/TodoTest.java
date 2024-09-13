package elsa.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("Sleep", false);
        assertEquals("[T][ ] Sleep", todo.toString());
    }

    @Test
    public void testTodoCreationWhenDone() {
        Todo todo = new Todo("Graduate from CS!", true);
        assertEquals("[T][X] Graduate from CS!", todo.toString());
    }
}
