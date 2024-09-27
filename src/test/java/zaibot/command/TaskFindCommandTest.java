package zaibot.command;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zaibot.exception.ZaibotException;
import zaibot.task.ToDoTask;
import zaibot.utils.Storage;
import zaibot.utils.TaskList;

public class TaskFindCommandTest {
    private final TaskList tasks = new TaskList();
    private final Storage storage = new Storage(tasks);

    @BeforeEach
    public void setUp() {
        tasks.clearTasks();
    }

    @Test
    public void execute_noTasksMatch() {
        try {
            tasks.addTask(new ToDoTask("one"));
            tasks.addTask(new ToDoTask("two"));
        } catch (ZaibotException zaibotException) {
            zaibotException.printStackTrace();
        }

        HashMap<String, String> options = new HashMap<>();
        options.put("name", "three");

        TaskFindCommand command = new TaskFindCommand("find", options);

        String expected = "Filtered the tasks for you. Good enough?\n";

        String outputMessage;
        try {
            outputMessage = command.execute(tasks, storage);
        } catch (Exception e) {
            outputMessage = e.getMessage();
        }
        Assertions.assertEquals(expected.trim(), outputMessage.trim());
    }

    @Test
    public void execute_emptyList() {
        HashMap<String, String> options = new HashMap<>();
        options.put("name", "three");

        TaskFindCommand command = new TaskFindCommand("find", options);

        String expected = "Filtered the tasks for you. Good enough?\n";

        String outputMessage;
        try {
            outputMessage = command.execute(tasks, storage);
        } catch (Exception e) {
            outputMessage = e.getMessage();
        }
        Assertions.assertEquals(expected.trim(), outputMessage.trim());
    }

    @Test
    public void execute_oneTaskMatch() {
        try {
            tasks.addTask(new ToDoTask("one"));
            tasks.addTask(new ToDoTask("two"));
        } catch (ZaibotException zaibotException) {
            zaibotException.printStackTrace();
        }

        HashMap<String, String> options = new HashMap<>();
        options.put("name", "one");

        TaskFindCommand command = new TaskFindCommand("find", options);

        String expected = """
                Filtered the tasks for you. Good enough?
                1. [T][ ] one
                """;

        String outputMessage;
        try {
            outputMessage = command.execute(tasks, storage);
        } catch (Exception e) {
            outputMessage = e.getMessage();
        }
        Assertions.assertEquals(expected.trim(), outputMessage.trim());
    }

    @Test
    public void execute_multipleTaskMatch() {
        try {
            tasks.addTask(new ToDoTask("one"));
            tasks.addTask(new ToDoTask("one more"));
        } catch (ZaibotException zaibotException) {
            zaibotException.printStackTrace();
        }
        HashMap<String, String> options = new HashMap<>();
        options.put("name", "one");

        TaskFindCommand command = new TaskFindCommand("find", options);

        String expected = """
                Filtered the tasks for you. Good enough?
                1. [T][ ] one
                2. [T][ ] one more
                """;

        String outputMessage;
        try {
            outputMessage = command.execute(tasks, storage);
        } catch (Exception e) {
            outputMessage = e.getMessage();
        }
        Assertions.assertEquals(expected.trim(), outputMessage.trim());
    }
}
