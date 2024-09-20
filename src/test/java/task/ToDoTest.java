package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ai.task.ToDo;

/**
 * Test ToDo Class for its public methods with return values.
 */
public class ToDoTest {
    @Test
    public void toDo_toStringNotDone_success() {
        assertEquals("[T][ ] borrow book", new ToDo("borrow book").toString());
    }

    @Test
    public void toDo_toStringDone_success() {
        assertEquals("[T][X] borrow book", new ToDo("borrow book", true).toString());
    }

    @Test
    public void toDo_stringDataNotDone_success() {
        assertEquals("T | 0 | borrow book", new ToDo("borrow book").stringData());
    }

    @Test
    public void toDo_stringDataDone_success() {
        assertEquals("T | 1 | borrow book", new ToDo("borrow book", true).stringData());
    }
}
