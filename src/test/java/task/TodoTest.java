package task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import exceptions.LightException;
import task.Todo;

public class TodoTest {

    @Test
    public void testTodoConstructor() throws LightException {
        // Create a Todo task
        Todo todo = new Todo("read book");

        // Verify the description
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void testTodoToString() throws LightException {
        // Create a Todo task
        Todo todo = new Todo("read book");

        // Verify the string representation
        assertEquals("[T][ ] read book", todo.toString());
    }
}