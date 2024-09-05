package voidcat.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoTest {
    @Test
    public void testMarkAsDone() {
        Task todo = new ToDo("Test todo mark");
        assertFalse(todo.isDone);
        todo.markAsDone();
        assertTrue(todo.isDone);
    }

    @Test
    public void testUnmarkAsDone() {
        Task todo = new ToDo("Test todo unmark");
        assertFalse(todo.isDone);
        todo.markAsDone();
        assertTrue(todo.isDone);
        todo.unmarkAsDone();
        assertFalse(todo.isDone);
    }
}
