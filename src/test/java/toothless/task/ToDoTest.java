package toothless.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for ToDo class.
 */
public class ToDoTest {

    /**
     * Tests if the ToDo is created correctly with a description.
     */
    @Test
    public void testToDoCreationWithDescription() {
        ToDo todo = new ToDo("Test task");
        assertEquals("Test task", todo.getDescription());
    }

    /**
     * Tests if the ToDo is created correctly with a description and status.
     */
    @Test
    public void testToDoCreationWithDescriptionAndStatus() {
        ToDo todo = new ToDo("Test task", true);
        assertEquals("Test task", todo.getDescription());
    }

    /**
     * Tests the toString method.
     */
    @Test
    public void testToString() {
        ToDo todo = new ToDo("Test task");
        assertEquals("[T][ ] Test task", todo.toString());

        ToDo completedTodo = new ToDo("Completed task", true);
        assertEquals("[T][X] Completed task", completedTodo.toString());
    }
}
