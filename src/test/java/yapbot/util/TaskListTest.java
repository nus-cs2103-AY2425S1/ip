package yapbot.util;

import org.junit.jupiter.api.Test;
import yapbot.exceptions.YapBotException;
import yapbot.tasks.Deadline;
import yapbot.tasks.Event;
import yapbot.tasks.Task;
import yapbot.tasks.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void listTasks_threeTasks_success() {
        try {
            TaskList tasks = new TaskList();

            tasks.addTask(new Event("Find nemo", "2PM", "1AM"));
            tasks.addTask(new Deadline("Find dory", "1PM"));
            tasks.addTask(new ToDo("Find nemo"));

            String expected = "  1.[E][ ] Find nemo (From: 2PM 03 Sep 2024 To: 1AM 03 Sep 2024)"
                    + "\n  2.[D][ ] Find dory (by: 1PM 03 Sep 2024)"
                    + "\n  3.[T][ ] Find nemo";

            assertEquals(expected, tasks.listTasks());
        } catch (YapBotException e) {
            fail();
        }
    }

    @Test
    public void listTasks_noTasks_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();

            assertEquals("", tasks.listTasks());
            fail();
        } catch (YapBotException e) {
            assertEquals("Error, no Tasks found in database." , e.getMessage());
        }

    }

    @Test
    public void markTask_validTask_success() {
        try {
            TaskList tasks = new TaskList();
            Task task = new ToDo("Find nemo");
            tasks.addTask(task);

            assertEquals(task, tasks.markTask(0));
        } catch (YapBotException e) {
            fail();
        }

    }

    @Test
    public void markTask_noTasks_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();

            assertEquals("", tasks.markTask(1));
            fail();
        } catch (YapBotException e) {
            assertEquals("Finding Task...Failure\nError, requested Task does not exist"
                    + ".\nUse command \"list\" to view your tasks." , e.getMessage());
        }

    }

}
