package mummy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    void taskConstructor_withStubTask_shouldContainCorrectInfo() {
        Task task = new StubTask("Sample Task");
        assertEquals("Sample Task", task.getDescription());
        assertFalse(task.isDone());
    }

    @Test
    void taskConstructor_withStatus_shouldContainCorrectInfo() {
        Task task = new StubTask("Sample Task", true);
        assertEquals("Sample Task", task.getDescription());
        assertTrue(task.isDone());
    }

    @Test
    void setAsDone_shouldSetTaskAsDone() {
        Task task = new StubTask("Sample Task");
        assertFalse(task.isDone());
        task.setAsDone();
        assertTrue(task.isDone());
    }

    @Test
    void setAsUndone_shouldSetTaskAsUndone() {
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
