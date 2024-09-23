package llama.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][ ] borrow book", new Todo("borrow book", false).toString());
    }

    @Test
    public void testSaveStringConversion() {
        assertEquals("T|0|borrow book", new Todo("borrow book", false).getSaveTaskString());
        assertEquals("T|1|borrow book", new Todo("borrow book", true).getSaveTaskString());
    }

    @Test
    public void markDone_success() {
        Task task = new Todo("borrow book", false);
        task.markDone();
        assertEquals("[T][X] borrow book", task.toString());
    }

    @Test
    public void markNotDone_success() {
        Task task = new Todo("yes", false);
        task.markDone();
        task.markNotDone();
        assertEquals("[T][ ] yes", task.toString());
    }
}
