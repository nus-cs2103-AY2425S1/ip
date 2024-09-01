package mummy.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    
    @Test
    void testTaskConstructor() {
        Task task = new StubTask("Sample Task");
        assertEquals("Sample Task", task.getDescription());
        assertFalse(task.isDone());
    }

    @Test
    void testTaskConstructorWithStatus() {
        Task task = new StubTask("Sample Task", true);
        assertEquals("Sample Task", task.getDescription());
        assertTrue(task.isDone());
    }

    @Test
    void testSetAsDone() {
        Task task = new StubTask("Sample Task");
        assertFalse(task.isDone());
        task.setAsDone();
        assertTrue(task.isDone());
    }

    @Test
    void testSetAsUndone() {
        Task task = new StubTask("Sample Task", true);
        assertTrue(task.isDone());
        task.setAsUndone();
        assertFalse(task.isDone());
    }

    private static class StubTask extends Task {
        public StubTask(String description) {
            super(description);
        }

        public StubTask(String description, boolean isDone) {
            super(description, isDone);
        }

        @Override
        public String toFileRecord() {
            return "";
        }
    }
}