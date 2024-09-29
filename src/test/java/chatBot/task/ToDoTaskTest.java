package chatbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ToDoTaskTest {
    @Test
    public void testToString() {
        try {
            assertEquals("[T][ ] read book", new ToDoTask("read book").toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void exceptionThrownSuccess() {
        try {
            assertEquals("dummy", new ToDoTask("").toString());
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Task Description cannot be empty", e.getMessage());
        }
    }
}
