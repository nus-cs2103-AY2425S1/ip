package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("Test task");
        assertEquals("Test task", todo.getName());
        assertFalse(todo.isDone());
    }

    @Test
    public void testTodoMarking() {
        Todo todo = new Todo("Test task");
        todo.mark();
        assertTrue(todo.isDone());
    }

    @Test
    public void testTodoUnmarking() {
        Todo todo = new Todo("Test task");
        todo.mark();
        todo.unmark();
        assertFalse(todo.isDone());
    }
}
