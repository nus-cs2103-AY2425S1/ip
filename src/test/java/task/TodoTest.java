package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit test for the Todo class.
 */
public class TodoTest {

    /**
     * Tests the creation of a Todo task and verifies that the name is set correctly and the task is not marked as done.
     */
    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("Test task");
        assertEquals("Test task", todo.getName());
        assertFalse(todo.isDone());
    }

    /**
     * Tests marking a Todo task as done.
     */
    @Test
    public void testTodoMarking() {
        Todo todo = new Todo("Test task");
        todo.mark();
        assertTrue(todo.isDone());
    }

    /**
     * Tests unmarking a Todo task as not done.
     */
    @Test
    public void testTodoUnmarking() {
        Todo todo = new Todo("Test task");
        todo.mark();
        todo.unmark();
        assertFalse(todo.isDone());
    }
}
