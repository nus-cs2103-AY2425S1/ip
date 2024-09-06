package mysutong;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void testToString_whenNotDone() {
        // Create a new Todo task
        Todo todo = new Todo("Read a book");

        // Expected output when the task is not done
        String expected = "[T][ ] Read a book";

        // Assert that the toString method produces the expected output
        assertEquals(expected, todo.toString());
    }

    @Test
    public void testMarkAsDone() {
        // Create a new Todo task
        Todo todo = new Todo("Complete assignment");

        // Initially, the task should not be done
        assertFalse(todo.isDone());

        // Mark the task as done
        todo.markAsDone();

        // Assert that the task is marked as done
        assertTrue(todo.isDone());

        // Check that toString reflects the done status
        String expected = "[T][X] Complete assignment";
        assertEquals(expected, todo.toString());
    }
}

