package ponderpika;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import ponderpika.task.Todo;

public class TodoTest {

    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("Finish homework");
        assertEquals("Finish homework", todo.getDescription());
        assertFalse(todo.isDone(), "New todo should not be marked as done.");
    }

    @Test
    public void testSaveFullDetails() {
        Todo todo = new Todo("Finish homework");
        assertEquals("T | false | Finish homework | ()", todo.saveFullDetails());

        todo.markDone(); // Simulate marking the task as done
        assertEquals("T | true | Finish homework | ()", todo.saveFullDetails());
    }

    @Test
    public void testToString() {
        Todo todo = new Todo("Finish homework");
        assertEquals("[T][ ] Finish homework", todo.toString());

        todo.markDone(); // Simulate marking the task as done
        assertEquals("[T][X] Finish homework", todo.toString());
    }

    @Test
    public void testTodoConstructorWithEmptyDescription() {
        Todo todo = new Todo("");
        assertEquals("", todo.getDescription());
        assertFalse(todo.isDone(), "Todo with empty description should not be marked as done.");
    }
}
