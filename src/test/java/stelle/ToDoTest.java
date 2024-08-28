package stelle;

import org.junit.jupiter.api.Test;
import stelle.exception.WrongDateFormatException;
import stelle.task.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Represents a class used for testing Stelle.java.
 * @author Lee Ze Hao (A0276123J)
 */

public class ToDoTest {
    @Test
    public void toString_constructorParams_correctName() {
        ToDo toDo = new ToDo("A todo");
        assertEquals("[T][ ] A todo", toDo.toString());
    }

    @Test
    public void markUnmark_noParams_correctStatus() {
        ToDo toDo = new ToDo("A todo");
        toDo.mark();
        assertEquals("[T][X] A todo", toDo.toString());
        assertEquals(true, toDo.getIsDone());
        toDo.unmark();
        assertEquals("[T][ ] A todo", toDo.toString());
        assertEquals(false, toDo.getIsDone());
    }
}
