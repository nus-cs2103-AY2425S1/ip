import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dave.task.Todo;

public class TodoTest {

    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("Finish assignment");
        assertEquals("Finish assignment", todo.getDescription());
        assertFalse(todo.getIsDone());
    }

    @Test
    public void testMarkAsDone() {
        Todo todo = new Todo("Finish assignment");
        todo.markAsDone();
        assertTrue(todo.getIsDone());
    }

    @Test
    public void testTodoToString() {
        Todo todo = new Todo("Finish assignment");
        assertEquals("[T][ ] Finish assignment", todo.toString());

        todo.markAsDone();
        assertEquals("[T][X] Finish assignment", todo.toString());
    }

    @Test
    public void testTodoWrite() {
        Todo todo = new Todo("Finish assignment");
        String expectedOutput = "T | 0 | Finish assignment\n";
        assertEquals(expectedOutput, todo.write());

        todo.markAsDone();
        expectedOutput = "T | 1 | Finish assignment\n";
        assertEquals(expectedOutput, todo.write());
    }
}
