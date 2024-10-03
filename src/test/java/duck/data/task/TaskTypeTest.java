package duck.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;



class TaskTypeTest {

    /**
     * Tests that the TaskTypes are correctly represented.
     */
    @Test
    public void testTaskType() {
        assertEquals("DEADLINE", TaskType.DEADLINE.toString(), "The 'deadline' task type should match.");
        assertEquals("EVENT", TaskType.EVENT.toString(), "The 'event' task type should match.");
        assertEquals("TODO", TaskType.TODO.toString(), "The 'todo' task type should match.");
    }

    /**
     * Tests that an exception is thrown when an invalid Task Type is used.
     */
    @Test
    public void testCommandWords_invalidWord_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            TaskType taskType = TaskType.valueOf("INVALID");
        });
    }
}
