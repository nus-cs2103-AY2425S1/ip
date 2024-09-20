package eevee;

import eevee.task.Task;
import eevee.task.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Represents the JUnit test for {@link TaskList}.
 */
public class TaskListTest {
    private TaskList t;

    /**
     * Initialises a new TaskList instance before each test.
     */
    @BeforeEach
    public void setUp() {
        t = new TaskList();
    }

    /**
     * Tests the {@link TaskList#removeTask(int)} method with an invalid task number.
     * Verifies that an {@link EeveeException} is thrown.
     */
    @Test
    public void testRemoveTask_invalidTaskNumber_throwsException() {
        Task task = new Task("Test task");
        t.addTask(task);

        EeveeException invalidTaskNumber = assertThrows(EeveeException.class, () -> t.removeTask(2));
        assertEquals("No task under the given task number. "
                + "Did you type the wrong number?", invalidTaskNumber.getMessage());
    }

    /**
     * Tests the {@link TaskList#getTask(int)} method when a negative index is given.
     * Verifies that an {@link EeveeException} is thrown.
     */
    @Test
    public void testGetTask_negativeIndex_throwsException() {
        Task task = new Task("Test task");
        t.addTask(task);

        EeveeException negativeIndex = assertThrows(EeveeException.class, () -> t.getTask(-1));
        assertEquals("No task under the given task number. "
                + "Did you type the wrong number?", negativeIndex.getMessage());
    }
}
