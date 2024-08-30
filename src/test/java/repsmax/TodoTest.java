package repsmax;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void toString_validTodo_correctFormat() {
        Todo todo = new Todo("Buy groceries");
        assertEquals("[T][ ] Buy groceries", todo.toString());
    }

    @Test
    public void toString_doneTodo_correctFormat() {
        Todo todo = new Todo("Buy groceries");
        todo.setDone();
        assertEquals("[T][X] Buy groceries", todo.toString());
    }
}
