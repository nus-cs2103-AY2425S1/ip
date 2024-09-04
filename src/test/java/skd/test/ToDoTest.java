package skd.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import task.ToDo;


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
