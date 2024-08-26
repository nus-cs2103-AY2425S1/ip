package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ToDoTest {

    @Test
    void testConstructor() {
        ToDo todo = new ToDo("Read a book");
        assertEquals("Read a book", todo.description);
        assertFalse(todo.isDone);

    }

    @Test
    void testMarkToDo() {
        ToDo todo = new ToDo("Write a report");
        todo.mark();
        assertTrue(todo.isDone);
        assertEquals("[T][X] Write a report", todo.toString());

    }

    @Test
    void testUnmarkToDo() {
        ToDo todo = new ToDo("Complete assignment");
        todo.mark();  // Mark as done
        todo.unmark();  // Then unmark
        assertFalse(todo.isDone);
        assertEquals("[T][ ] Complete assignment", todo.toString());

    }

    @Test
    void testToDataFormat() {
        ToDo todo = new ToDo("Go for a run");
        String expected = "todo | 0 | Go for a run";
        assertEquals(expected, todo.toDataFormat());

    }

    @Test
    void testToStringWhenNotDone() {
        ToDo todo = new ToDo("Attend meeting");
        String expected = "[T][ ] Attend meeting";
        assertEquals(expected, todo.toString());

    }

    @Test
    void testToStringWhenDone() {
        ToDo todo = new ToDo("Buy groceries");
        todo.mark();
        String expected = "[T][X] Buy groceries";
        assertEquals(expected, todo.toString());

    }
}