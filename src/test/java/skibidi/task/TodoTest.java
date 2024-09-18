package skibidi.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TodoTest {

    @Test
    public void testWriteToFile() {
        Todo todo = new Todo("read book");
        assertEquals("T | 0 | read book", todo.writeToFile());
    }

    @Test
    public void testToString() {
        Todo todo = new Todo("read book");
        assertEquals(" [T][ ] ReAd bOoK", todo.toString());
    }

    @Test
    public void testMarkAsDone() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        assertEquals(" [T][X] ReAd bOoK", todo.toString());
    }
}
