package skd.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {

    @Test
    public void testToDoCreation() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void testMarkAsDone() {
        ToDo todo = new ToDo("read book");
        todo.markAsDone();
        assertTrue(todo.isDone());
        assertEquals("[T][X] read book", todo.toString());
    }

    @Test
    public void testToDoWithIsDone() {
        ToDo todo = new ToDo("read book", true);
        assertTrue(todo.isDone());
        assertEquals("[T][X] read book", todo.toString());
    }
}