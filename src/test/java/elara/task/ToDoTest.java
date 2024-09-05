package elara.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testFileFormat() {
        assertEquals("T | 0 | read book", new ToDoTask("read book").toFileFormat());
    }
}
