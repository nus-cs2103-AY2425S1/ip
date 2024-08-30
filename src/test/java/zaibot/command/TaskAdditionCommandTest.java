package zaibot.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zaibot.utils.Storage;
import zaibot.utils.TaskList;
import zaibot.utils.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class TaskAdditionCommandTest {
    private final TaskList tasks = new TaskList();
    private final Storage storage = new Storage(tasks);

    @BeforeEach
    public void setUp() {
        tasks.clearTasks();
    }

    @Test
    public void execute_testInvalidAdditionType() {
        TaskAdditionCommand command = new TaskAdditionCommand("random",
                new HashMap<String, String>());

        String outputMessage;
        try {
            outputMessage = command.execute(tasks, storage);
        } catch (Exception e) {
            outputMessage = e.getMessage();
        }
        Assertions.assertEquals("Invalid command.".trim(),
                outputMessage.trim());
    }

    @Test
    public void execute_todo_success() {
        HashMap<String, String> optionMap = new HashMap<String, String>();
        optionMap.put("name", "test");

        TaskAdditionCommand command = new TaskAdditionCommand("todo",
                optionMap);

        String expected = "[T][ ] test\n"
                + "Another day, another task. Added.\n"
                + "You have 1 task(s). Get moving.\n";

        String outputMessage;
        try {
            outputMessage = command.execute(tasks, storage);
        } catch (Exception e) {
            outputMessage = e.getMessage();
        }
        Assertions.assertEquals(expected.trim(), outputMessage.trim());
    }

    @Test
    public void execute_deadline_wrongArguments() {
        HashMap<String, String> optionMap = new HashMap<String, String>();
        optionMap.put("name", "test");

        TaskAdditionCommand command = new TaskAdditionCommand("deadline",
                optionMap);

        String expected = "Deadline must have option /by.";

        String outputMessage;
        try {
            outputMessage = command.execute(tasks, storage);
        } catch (Exception e) {
            outputMessage = e.getMessage();
        }
        Assertions.assertEquals(expected.trim(), outputMessage.trim());
    }

    @Test
    public void execute_deadline_success() {
        HashMap<String, String> optionMap = new HashMap<String, String>();
        optionMap.put("name", "test");
        optionMap.put("by", "2024-12-01 18:00");

        TaskAdditionCommand command = new TaskAdditionCommand("deadline",
                optionMap);

        String expected = "[D][ ] test (by: Dec 01 2024 1800)\n"
                + "Another day, another task. Added.\n"
                + "You have 1 task(s). Get moving.\n";

        String outputMessage;
        try {
            outputMessage = command.execute(tasks, storage);
        } catch (Exception e) {
            outputMessage = e.getMessage();
        }
        Assertions.assertEquals(expected.trim(), outputMessage.trim());
    }

    @Test
    public void execute_event_wrongArguments() {

        HashMap<String, String> optionMap = new HashMap<String, String>();
        optionMap.put("name", "test");

        TaskAdditionCommand command = new TaskAdditionCommand("event",
                optionMap);

        String expected = "Event must have option /from and /to.";

        String outputMessage;
        try {
            outputMessage = command.execute(tasks, storage);
        } catch (Exception e) {
            outputMessage = e.getMessage();
        }
        Assertions.assertEquals(expected.trim(), outputMessage.trim());
    }

    @Test
    public void execute_event_success() {
        HashMap<String, String> optionMap = new HashMap<String, String>();
        optionMap.put("name", "test");
        optionMap.put("from", "2024-12-01 18:00");
        optionMap.put("to", "2024-12-01 20:00");

        TaskAdditionCommand command = new TaskAdditionCommand("event",
                optionMap);

        String expected = "[E][ ] test (from: Dec 01 2024 1800 to: Dec 01 2024 2000)\n"
                + "Another day, another task. Added.\n"
                + "You have 1 task(s). Get moving.\n";

        String outputMessage;
        try {
            outputMessage = command.execute(tasks, storage);
        } catch (Exception e) {
            outputMessage = e.getMessage();
        }
        Assertions.assertEquals(expected.trim(), outputMessage.trim());
    }
}
