package Gutti;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskTest {

    @Test
    public void testMarkAsDone() {
        Task task = new Task("Read a book", true);
        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testToString_NotDone() {
        Task task = new Task("Read a book", false);
        assertEquals("[ ] Read a book", task.toString());
    }

    @Test
    public void testToString_Done() {
        Task task = new Task("Read a book", true);
        task.markAsDone();
        assertEquals("[X] Read a book", task.toString());
    }
}
