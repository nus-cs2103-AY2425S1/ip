package chatbot.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void encode_unmarked() {
        Todo todo = new Todo("test");
        assertEquals(todo.encode(),"T|0|test");
    }

    @Test
    public void encode_marked() {
        Todo todo = new Todo("test", true);
        assertEquals(todo.encode(),"T|1|test");
    }
}
