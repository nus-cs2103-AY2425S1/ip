package rizz.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testToString() {
        ToDo todo1 = new ToDo("Buy groceries", false);
        ToDo todo2 = new ToDo("Buy toys", true);

        String str1 = todo1.toString();
        String str2 = todo2.toString();

        assertEquals("[T][ ] Buy groceries", str1);
        assertEquals("[T][X] Buy toys", str2);
    }

    @Test
    public void testMarkUnmark() {

        ToDo todo = new ToDo("Finish homework", false);

        todo.markAsDone();

        assertTrue(todo.getStatus());

        todo.unmarkAsDone();

        assertFalse(todo.getStatus());
    }


    @Test
    public void testExport() {
        ToDo todo = new ToDo("Finish homework", true);

        String export = todo.export();

        assertEquals("T | 1 | Finish homework", export);
    }
}

