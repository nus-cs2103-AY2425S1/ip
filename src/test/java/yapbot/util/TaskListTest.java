package yapbot.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import yapbot.exceptions.YapBotException;
import yapbot.tasks.Deadline;
import yapbot.tasks.Event;
import yapbot.tasks.Task;
import yapbot.tasks.ToDo;

public class TaskListTest {
    private TaskList tasks;
    private Task sampleTask;

    @BeforeEach
    public void makeTaskList() {
        try {
            tasks = new TaskList();
            sampleTask = new ToDo("Find nemo");

            tasks.addTask(sampleTask);
            tasks.addTask(new Event("Find nemo", "2PM 2024/09/03", "1AM 2024/09/03"));
            tasks.addTask(new Deadline("Find dory", "1PM 2024/09/03"));
        } catch (YapBotException e) {
            fail("Failed to create tasks on startup.");
        }
    }

    @Test
    public void listTasks_threeTasks_success() {
        try {
            String expected = "  1.[T][ ] Find nemo"
                    + "\n  2.[E][ ] Find nemo (From: 2PM 03 Sep 2024 To: 1AM 03 Sep 2024)"
                    + "\n  3.[D][ ] Find dory (by: 1PM 03 Sep 2024)";

            assertEquals(expected, tasks.listTasks());
        } catch (YapBotException e) {
            fail("Test Case Error.");
        }
    }

    @Test
    public void listTasks_noTasks_exceptionThrown() {
        try {
            TaskList taskListWithoutTasks = new TaskList();

            assertEquals("", taskListWithoutTasks.listTasks());
            fail();
        } catch (YapBotException e) {
            assertEquals("Error, no Tasks found in database." , e.getMessage());
        }

    }

    @Test
    public void markTask_validTask_success() {
        try {
            assertEquals(sampleTask, tasks.markTask(0));
        } catch (YapBotException e) {
            fail("Test Case Error.");
        }

    }

    @Test
    public void markTask_noTasks_exceptionThrown() {
        try {
            TaskList taskListWithoutTasks = new TaskList();

            assertEquals("", taskListWithoutTasks.markTask(1));
            fail();
        } catch (YapBotException e) {
            assertEquals("Finding Task...Failure\nError, requested Task does not exist"
                    + ".\nUse command \"list\" to view your tasks." , e.getMessage());
        }

    }

    @Test
    public void deleteTask_validTask_success() {
        try {
            assertEquals(sampleTask, tasks.deleteTask(0));
        } catch (YapBotException e) {
            fail("Test Case Error.");
        }
    }

    @Test
    public void deleteTask_invalidTaskNumber_exceptionThrown() {
        try {
            assertEquals("", tasks.deleteTask(-5));
            fail();
        } catch (YapBotException e) {
            assertEquals("Finding Task...Failure\nError, requested Task does not exist"
                    + ".\nUse command \"list\" to view your tasks." , e.getMessage());
        }

    }

}
