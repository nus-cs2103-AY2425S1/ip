package moimoi.task;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TodoTest {

    String description = "dummy";

    @Test
    public void testMarkUnmark() {
        Todo todo = new Todo(this.description);
        assertEquals(" ", todo.getStatusIcon());
        todo.mark();
        assertEquals("X", todo.getStatusIcon());
        todo.mark();
        assertEquals("X", todo.getStatusIcon());
        todo.unmark();
        assertEquals(" ", todo.getStatusIcon());
        todo.unmark();
        assertEquals(" ", todo.getStatusIcon());
    }

    @Test
    public void occurringOn_date_returnsFalse() {
        Todo todo = new Todo(this.description);
        assertFalse(todo.occurringOn(LocalDate.parse("2024-08-24")));
    }

    @Test
    public void testStringUI() {
        Todo todo = new Todo(this.description);
        assertEquals("[T][ ] " + this.description, todo.stringUI());
    }

    @Test
    public void testStringStorage() {
        Todo todo = new Todo(this.description);
        todo.mark();
        assertEquals("T | X | " + this.description, todo.stringStorage());
    }

}
