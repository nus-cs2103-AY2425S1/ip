package sentinel.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ToDoTest {

    @Test
    public void testConstructor() {
        String description = "Test ToDo";
        ToDo todo = new ToDo(description);
        assertEquals(description, todo.getDescription(), "Description should match.");
        assertFalse(todo.isDone(), "New ToDo should not be marked as done.");
    }

    @Test
    public void testGetStatusIcon() {
        ToDo todo = new ToDo("Test ToDo");
        assertEquals("[ ]", todo.getStatusIcon(), "Status icon should be '[ ]' for incomplete tasks.");
        todo.setDone();
        assertEquals("[X]", todo.getStatusIcon(), "Status icon should be '[X]' for completed tasks.");
    }

    @Test
    public void testGetTaskType() {
        ToDo todo = new ToDo("Test ToDo");
        assertEquals('T', todo.getTaskType(), "Task type for ToDo should be 'T'.");
    }

    @Test
    public void testListedString() {
        ToDo todo = new ToDo("Test ToDo");
        String listedString = todo.listedString();
        assertTrue(listedString.startsWith("[T]"), "Listed string should start with '[T]'.");
        assertTrue(listedString.contains("[ ] Test ToDo"), "Listed string should contain '[ ] Test ToDo'.");
    }

    @Test
    public void testSetDone() {
        ToDo todo = new ToDo("Test ToDo");
        todo.setDone();
        assertTrue(todo.isDone(), "ToDo should be marked as done.");
    }

    @Test
    public void testSetUndone() {
        ToDo todo = new ToDo("Test ToDo");
        todo.setDone();
        todo.setUndone();
        assertFalse(todo.isDone(), "ToDo should be marked as undone.");
    }

    @Test
    public void testLocalDateTimeToString() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 30, 15, 45);
        ToDo todo = new ToDo("Test ToDo");
        String dateTimeString = todo.localDateTimeToString(dateTime);
        assertEquals("30 AUGUST 2024 1545 Hours (FRIDAY 03:45pm)", dateTimeString,
                "LocalDateTime to string conversion is incorrect.");
    }
}
