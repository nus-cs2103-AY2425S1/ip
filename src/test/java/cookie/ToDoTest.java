package cookie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import cookie.task.ToDo;

public class ToDoTest {
    @Test
    public void testConstructor() {
        ToDo todo = new ToDo("Read book");
        assertEquals("Read book", todo.getDescription());
    }
    @Test
    void testGetStatus() {
        ToDo todo = new ToDo("Read a book");
        assertEquals("", todo.getStatus());
        todo.markDone();
        assertEquals("X", todo.getStatus());
    }
    @Test
    void testMarkDone() {
        ToDo todo = new ToDo("Read a book");
        todo.markDone();
        assertEquals("X", todo.getStatus());
    }

    @Test
    void testUnmarkDone() {
        ToDo todo = new ToDo("Read a book");
        todo.markDone();
        todo.unmarkDone();
        assertEquals("", todo.getStatus());
    }

    @Test
    void testGetDescription() {
        ToDo todo = new ToDo("Read a book");
        assertEquals("Read a book", todo.getDescription());
    }

    @Test
    void testToString() {
        ToDo todo = new ToDo("Read a book");
        assertEquals("[T][] Read a book", todo.toString());
        todo.markDone();
        assertEquals("[T][X] Read a book", todo.toString());
    }
}
