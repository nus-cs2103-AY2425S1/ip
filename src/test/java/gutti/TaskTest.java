package gutti;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskTest {

    @Test
    public void testMarkAsDone() {
        Task task = new Task("Read a book", true);
        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testToStringNotDone() {
        Task task = new Task("Read a book", false);
        assertEquals("[ ] Read a book", task.toString());
    }

    @Test
    public void testToStringDone() {
        Task task = new Task("Read a book", true);
        task.markAsDone();
        assertEquals("[X] Read a book", task.toString());
    }
}
