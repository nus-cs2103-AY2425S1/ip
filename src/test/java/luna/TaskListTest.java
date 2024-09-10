package luna;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import luna.task.Deadline;
import luna.task.Todo;

public class TaskListTest {
    @Test
    public void deleteTask_negativeTaskNumber_exceptionThrown() {
        try {
            Storage storage = new Storage();
            assertEquals(new Todo("return book"), new TaskList().deleteTask(-1, storage));
            fail();
        } catch (LunaException e) {
            assertEquals("Invalid task number. Type \"list\" to view tasks.", e.getMessage());
        }
    }

    @Test
    public void deleteTask_exceedTotalTask_exceptionThrown() {
        try {
            Storage storage = new Storage();
            assertEquals(new Todo("return book"), new TaskList().deleteTask(1, storage));
            fail();
        } catch (LunaException e) {
            assertEquals("Invalid task number. Type \"list\" to view tasks.", e.getMessage());
        }
    }

    @Test
    public void addTask_deadlineTask_success() {
        Storage storage = new Storage();
        Deadline deadline = new Deadline("Buy groceries", LocalDateTime.now());
        String taskSize = "Got it. I've added this task:\n"
                + "  " + deadline + "\n"
                + "Now you have 1 tasks in the list.";

        assertEquals(taskSize, new TaskList().addTask(deadline, storage));
    }
}

