package moimoi.util.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TodoTest {

    private String description = "dummy";

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
        assertFalse(todo.occursOn(LocalDate.parse("2024-08-24")));
    }

    @Test
    public void hasKeyword() {

        Todo todoWithoutWhiteSpace = new Todo(this.description);
        Todo todoWithWhiteSpace = new Todo(this.description + " " + this.description);

        assertTrue(todoWithoutWhiteSpace.hasKeyword("dummy"));
        assertTrue(todoWithoutWhiteSpace.hasKeyword("Dum"));
        assertTrue(todoWithWhiteSpace.hasKeyword(" "));

        assertFalse(todoWithoutWhiteSpace.hasKeyword("dummies"));
        assertFalse(todoWithoutWhiteSpace.hasKeyword("?"));
        assertFalse(todoWithoutWhiteSpace.hasKeyword("dummy "));
        assertFalse(todoWithoutWhiteSpace.hasKeyword(" "));

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
