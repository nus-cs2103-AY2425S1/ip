package dude.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    private ToDo todo;

    @BeforeEach
    public void setup() {
        todo = new ToDo("task 1");
    }

    @Test
    public void testConstructor() {
        todo = new ToDo("task 2");

        assertEquals("task 2", todo.description);
        assertFalse(todo.isDone);
    }

    @Test
    public void testMarkAsDone() {
        todo.markAsDone();

        assertTrue(todo.isDone);
        assertEquals("X", todo.getStatusIcon());
    }

    @Test
    public void testMarkAsNotDone() {
        todo.markAsDone();
        todo.markAsNotDone();

        assertFalse(todo.isDone);
        assertEquals(" ", todo.getStatusIcon());
    }

    @Test
    public void testGetStatusIcon() {
        assertEquals(" ", todo.getStatusIcon());

        todo.markAsDone();

        assertEquals("X", todo.getStatusIcon());
    }

    @Test
    public void testTaskToStringData() {
        assertEquals("T| |task 1", todo.taskToStringData());

        todo.markAsDone();

        assertEquals("T|X|task 1", todo.taskToStringData());
    }

    @Test
    public void testToString() {
        assertEquals("[T][ ] task 1", todo.toString());

        todo.markAsDone();

        assertEquals("[T][X] task 1", todo.toString());
    }

    @Test
    public void testEquals() {
        ToDo sameTask = new ToDo("task 1");
        ToDo differentTask = new ToDo("task 2");

        assertEquals(todo, sameTask);
        assertNotEquals(todo, differentTask);
    }
}
