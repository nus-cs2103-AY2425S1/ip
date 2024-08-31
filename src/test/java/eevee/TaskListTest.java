package eevee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    private TaskList t;

    @BeforeEach
    public void setUp() {
        t = new TaskList();
    }

    @Test
    public void testRemoveTask_invalidTaskNumber_throwsException() {
        Task task = new Task("Test task");
        t.addTask(task);

        EeveeException invalidTaskNumber = assertThrows(EeveeException.class, () -> t.removeTask(2));
        assertEquals("No task under the given task number. "
                + "Did you type the wrong number?", invalidTaskNumber.getMessage());
    }

    @Test
    public void testGetTask_negativeIndex_throwsException() {
        Task task = new Task("Test task");
        t.addTask(task);

        EeveeException negativeIndex = assertThrows(EeveeException.class, () -> t.getTask(-1));
        assertEquals("No task under the given task number. "
                + "Did you type the wrong number?", negativeIndex.getMessage());
    }
}
