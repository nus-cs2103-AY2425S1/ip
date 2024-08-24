package zaibot.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zaibot.Storage;
import zaibot.TaskList;
import zaibot.Ui;
import zaibot.task.ToDoTask;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class TaskFindCommandTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final TaskList tasks = new TaskList();
    private final Storage storage = new Storage(tasks);

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        tasks.clearTasks();
    }

    @Test
    public void execute_noTasksMatch() {
        tasks.addTask(new ToDoTask("one"));
        tasks.addTask(new ToDoTask("two"));
        HashMap<String, String> options = new HashMap<>();
        options.put("name", "three");

        TaskFindCommand command = new TaskFindCommand("find", options);
        try {
            command.execute(tasks, storage);
        } catch (Exception e) {
            Ui.displayError(e);
        } finally {
            String expected = "Filtered the tasks for you. Good enough?\n";
            Assertions.assertEquals(expected.trim(),
                    outputStreamCaptor.toString().trim());
        }
    }

    @Test
    public void execute_emptyList() {
        HashMap<String, String> options = new HashMap<>();
        options.put("name", "three");

        TaskFindCommand command = new TaskFindCommand("find", options);
        try {
            command.execute(tasks, storage);
        } catch (Exception e) {
            Ui.displayError(e);
        } finally {
            String expected = "Filtered the tasks for you. Good enough?\n";
            Assertions.assertEquals(expected.trim(),
                    outputStreamCaptor.toString().trim());
        }
    }

    @Test
    public void execute_oneTaskMatch() {
        tasks.addTask(new ToDoTask("one"));
        tasks.addTask(new ToDoTask("two"));
        HashMap<String, String> options = new HashMap<>();
        options.put("name", "one");

        TaskFindCommand command = new TaskFindCommand("find", options);
        try {
            command.execute(tasks, storage);
        } catch (Exception e) {
            Ui.displayError(e);
        } finally {
            String expected = """
                    Filtered the tasks for you. Good enough?
                    1. [T][ ] one
                    """;
            Assertions.assertEquals(expected.trim(),
                    outputStreamCaptor.toString().trim());
        }
    }

    @Test
    public void execute_multipleTaskMatch() {
        tasks.addTask(new ToDoTask("one"));
        tasks.addTask(new ToDoTask("one more"));
        HashMap<String, String> options = new HashMap<>();
        options.put("name", "one");

        TaskFindCommand command = new TaskFindCommand("find", options);
        try {
            command.execute(tasks, storage);
        } catch (Exception e) {
            Ui.displayError(e);
        } finally {
            String expected = """
                    Filtered the tasks for you. Good enough?
                    1. [T][ ] one
                    2. [T][ ] one more
                    """;
            Assertions.assertEquals(expected.trim(),
                    outputStreamCaptor.toString().trim());
        }
    }
}
