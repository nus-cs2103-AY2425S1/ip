package zaibot.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zaibot.Storage;
import zaibot.TaskList;
import zaibot.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class TaskAdditionCommandTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final TaskList tasks = new TaskList();
    private final Storage storage = new Storage(tasks);

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        tasks.clearTasks();
    }

    @Test
    public void execute_testInvalidAdditionType() {
        TaskAdditionCommand command = new TaskAdditionCommand("random",
                new HashMap<String, String>());
        try {
            command.execute(tasks, storage);
        } catch (Exception e) {
            Ui.displayError(e);
        } finally {
            Assertions.assertEquals("Invalid command.",
                    outputStreamCaptor.toString().trim());
        }
    }

    @Test
    public void execute_todo_success() {
        HashMap<String, String> optionMap = new HashMap<String, String>();
        optionMap.put("name", "test");

        TaskAdditionCommand command = new TaskAdditionCommand("todo",
                optionMap);
        try {
            command.execute(tasks, storage);
        } catch (Exception e) {
            Ui.displayError(e);
        } finally {
            String expected = "-----------------------------------------------------------\n"
                    + "[T][ ] test\n"
                    + "Another day, another task. Added.\n"
                    + "You have 1 task(s). Get moving.\n"
                    + "-----------------------------------------------------------\n";
            Assertions.assertEquals(expected.trim(),
                    outputStreamCaptor.toString().trim());
        }
    }

    @Test
    public void execute_deadline_wrongArguments() {
        HashMap<String, String> optionMap = new HashMap<String, String>();
        optionMap.put("name", "test");

        TaskAdditionCommand command = new TaskAdditionCommand("deadline",
                optionMap);
        try {
            command.execute(tasks, storage);
        } catch (Exception e) {
            Ui.displayError(e);
        } finally {
            String expected = "Deadline must have option /by.";
            Assertions.assertEquals(expected.trim(),
                    outputStreamCaptor.toString().trim());
        }
    }

    @Test
    public void execute_deadline_success() {
        HashMap<String, String> optionMap = new HashMap<String, String>();
        optionMap.put("name", "test");
        optionMap.put("by", "2024-12-01 18:00");

        TaskAdditionCommand command = new TaskAdditionCommand("deadline",
                optionMap);
        try {
            command.execute(tasks, storage);
        } catch (Exception e) {
            Ui.displayError(e);
        } finally {
            String expected = "-----------------------------------------------------------\n"
                    + "[D][ ] test (by: Dec 01 2024 1800)\n"
                    + "Another day, another task. Added.\n"
                    + "You have 1 task(s). Get moving.\n"
                    + "-----------------------------------------------------------\n";
            Assertions.assertEquals(expected.trim(),
                    outputStreamCaptor.toString().trim());
        }
    }

    @Test
    public void execute_event_wrongArguments() {

        HashMap<String, String> optionMap = new HashMap<String, String>();
        optionMap.put("name", "test");

        TaskAdditionCommand command = new TaskAdditionCommand("event",
                optionMap);
        try {
            command.execute(tasks, storage);
        } catch (Exception e) {
            Ui.displayError(e);
        } finally {
            String expected = "Event must have option /from and /to.";
            Assertions.assertEquals(expected.trim(),
                    outputStreamCaptor.toString().trim());
        }
    }

    @Test
    public void execute_event_success() {
        HashMap<String, String> optionMap = new HashMap<String, String>();
        optionMap.put("name", "test");
        optionMap.put("from", "2024-12-01 18:00");
        optionMap.put("to", "2024-12-01 20:00");

        TaskAdditionCommand command = new TaskAdditionCommand("event",
                optionMap);
        try {
            command.execute(tasks, storage);
        } catch (Exception e) {
            Ui.displayError(e);
        } finally {
            String expected = "-----------------------------------------------------------\n"
                    + "[E][ ] test (from: Dec 01 2024 1800 to: Dec 01 2024 2000)\n"
                    + "Another day, another task. Added.\n"
                    + "You have 1 task(s). Get moving.\n"
                    + "-----------------------------------------------------------\n";
            Assertions.assertEquals(expected.trim(),
                    outputStreamCaptor.toString().trim());
        }
    }
}
