package luna;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import luna.task.Deadline;
import luna.task.Task;

public class TaskListTest {
    @Test
    public void deleteTask_negativeTaskNumber_exceptionThrown() {
        try {
            assertEquals(new ArrayList<Task>(), new TaskList().deleteTask(-1));
            fail();
        } catch (LunaException e) {
            assertEquals("Invalid task number. Type \"list\" to view tasks.", e.getMessage());
        }
    }

    @Test
    public void deleteTask_exceedTotalTask_exceptionThrown() {
        try {
            assertEquals(new ArrayList<Task>(), new TaskList().deleteTask(1));
            fail();
        } catch (LunaException e) {
            assertEquals("Invalid task number. Type \"list\" to view tasks.", e.getMessage());
        }
    }

    @Test
    public void addTask_deadlineTask_success() {
        Deadline deadline = new Deadline("Buy groceries", LocalDateTime.now());
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(deadline);
        assertEquals(tasks, new TaskList().addTask(deadline));
    }
}

