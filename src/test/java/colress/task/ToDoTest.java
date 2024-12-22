package colress.task;

import static colress.TestUtil.VALID_DESCRIPTION_ONE;
import static colress.TestUtil.VALID_DESCRIPTION_TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toStringTest() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE, false);
        assertEquals(todo.toString(), String.format("[ ][T] %s", VALID_DESCRIPTION_ONE));

        todo = new ToDo(VALID_DESCRIPTION_ONE, true);
        assertEquals(todo.toString(), String.format("[X][T] %s", VALID_DESCRIPTION_ONE));
    }

    @Test
    public void toTextFileTest() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE, false);
        assertEquals(todo.toTextFile(),
                String.format("[ ] | TODO | %s", VALID_DESCRIPTION_ONE));

        todo = new ToDo(VALID_DESCRIPTION_ONE, true);
        assertEquals(todo.toTextFile(),
                String.format("[X] | TODO | %s", VALID_DESCRIPTION_ONE));
    }

    @Test
    public void equalsTest() {
        ToDo todo = new ToDo(VALID_DESCRIPTION_ONE);

        // same values -> returns true
        assertTrue(todo.equals(new ToDo(VALID_DESCRIPTION_ONE)));

        // same object -> returns true
        assertTrue(todo.equals(todo));

        // null -> returns false
        assertFalse(todo.equals(null));

        // different types -> returns false
        assertFalse(todo.equals(17));

        // different values -> returns false
        assertFalse(todo.equals(new ToDo(VALID_DESCRIPTION_TWO)));
    }
}
