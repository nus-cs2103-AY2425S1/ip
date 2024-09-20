package bottle.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TodoTest {

    private Todo todo;

    @BeforeEach
    public void setUp() {
        todo = new Todo("Finish homework");
    }

    @Test
    public void testTodoConstructor() {
        assertEquals("Finish homework", todo.toString().substring(7));
        assertFalse(todo.isChecked); // Task should be unchecked by default
    }

    @Test
    public void testToString() {
        String expectedOutput = "[T][ ] Finish homework";
        assertEquals(expectedOutput, todo.toString());

        // Mark the task as done and check the output
        todo.mark();
        expectedOutput = "[T][X] Finish homework";
        assertEquals(expectedOutput, todo.toString());
    }

    @Test
    public void testToSaveFormat() {
        String expectedOutput = "T | 0 | Finish homework | ";
        assertEquals(expectedOutput, todo.toSaveFormat());

        // Mark the task as done and check the save format
        todo.mark();
        expectedOutput = "T | 1 | Finish homework | ";
        assertEquals(expectedOutput, todo.toSaveFormat());
    }

    @Test
    public void testMarkAsDone() {
        todo.mark();
        assertTrue(todo.isChecked);
        assertEquals("[T][X] Finish homework", todo.toString());
    }

    @Test
    public void testMarkAsNotDone() {
        todo.unMark();
        assertEquals("[T][ ] Finish homework", todo.toString());
    }
}

