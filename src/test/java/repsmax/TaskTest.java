package repsmax;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testToString_taskNotDone_correctFormat() {
        Task task = new Task("Write code");
        assertEquals("[ ] Write code", task.toString());
    }

    @Test
    public void testToString_taskDone_correctFormat() {
        Task task = new Task("Write code");
        task.setDone();
        assertEquals("[X] Write code", task.toString());
    }

    @Test
    public void testFromFileFormat_validFormat_taskCreated() {
        Task task = Task.fromFileFormat("T | 1 | Read book");
        assertEquals("[X] Read book", task.toString());
    }

    @Test
    public void testFromFileFormat_invalidFormat_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Task.fromFileFormat("Invalid format string");
        });
    }
}
