package chatkaki.tasks;

import chatkaki.tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void fileFormat_validTodo_correctFormat() {
        Todo todo = new Todo(false, "read book");
        assertEquals("TODO,false,read book", todo.fileFormat());
    }

    @Test
    public void toString_validTodo_correctString() {
        Todo todo = new Todo(false, "read book");
        assertEquals("[T][ ] read book", todo.toString());
    }
}