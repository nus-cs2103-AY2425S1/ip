package myapp.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTest {

    private Todo todo;

    @BeforeEach
    public void setUp() {
        this.todo = new Todo("Sample Todo Task");
    }

    @Test
    public void testConstructor() {
        assertEquals("Sample Todo Task", this.todo.getDescription());
        assertFalse(this.todo.isDone(), "Task should not be marked as done initially");
    }

    @Test
    public void testMarkAsDone() {
        this.todo.markAsDone();
        assertTrue(this.todo.isDone(), "Task should be marked as done");
    }

    @Test
    public void testMarkAsNotDone() {
        todo.markAsDone();
        todo.markAsNotDone();
        assertFalse(todo.isDone(), "Task should be marked as not done");
    }

    @Test
    public void testToString_notDone() {
        String expected = "[T][ ] Sample Todo Task";
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testToString_done() {
        todo.markAsDone();
        String expected = "[T][X] Sample Todo Task";
        assertEquals(expected, todo.toString());
    }
}
