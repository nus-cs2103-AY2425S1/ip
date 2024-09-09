package bottleopener;

import bottleopener.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTest {
    @Test
    void testConstructor_withDescription() {
        ToDo todo = new ToDo("Read a book");
        assertEquals("Read a book", todo.getDescription());
        assertFalse(todo.isDone());
    }

    @Test
    void testConstructor_withDescriptionAndStatus() {
        ToDo todo = new ToDo("Read a book", true);
        assertEquals("Read a book", todo.getDescription());
        assertTrue(todo.isDone());
    }

    @Test
    void testMarkAsDone() {
        ToDo todo = new ToDo("Read a book");
        ToDo doneTodo = todo.markAsDone();
        assertTrue(doneTodo.isDone());
        assertEquals("Read a book", doneTodo.getDescription());
    }

    @Test
    void testMarkAsUndone() {
        ToDo todo = new ToDo("Read a book", true);
        ToDo undoneTodo = todo.markAsUndone();
        assertFalse(undoneTodo.isDone());
        assertEquals("Read a book", undoneTodo.getDescription());
    }

    @Test
    void testGetType() {
        ToDo todo = new ToDo("Read a book");
        assertEquals("T", todo.getType());
    }

    @Test
    void testGetTime() {
        ToDo todo = new ToDo("Read a book");
        assertEquals("", todo.getTime());
    }

}