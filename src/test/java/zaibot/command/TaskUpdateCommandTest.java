package zaibot.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zaibot.utils.Storage;
import zaibot.utils.TaskList;
import zaibot.task.Task;
import zaibot.task.ToDoTask;

import java.util.HashMap;

public class TaskUpdateCommandTest {
    private final TaskList tasks = new TaskList();
    private final Storage storage = new Storage(tasks);

    @BeforeEach
    public void setUp() {
        tasks.clearTasks();
    }

    @Test
    public void execute_mark_success() {
        tasks.addTask(new ToDoTask("one"));
        HashMap<String, String> optionMap = new HashMap<>();

        optionMap.put("number", "1");

        TaskUpdateCommand command = new TaskUpdateCommand("mark", optionMap);

        String expected = """
                [T][X] one
                Task done. Finally.
                You have 1 task(s). Get moving.
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
    public void execute_unmark_success() {
        Task task = new ToDoTask("one");
        task.setDone(true);
        tasks.addTask(task);

        HashMap<String, String> optionMap = new HashMap<>();

        optionMap.put("number", "1");

        TaskUpdateCommand command = new TaskUpdateCommand("unmark", optionMap);

        String expected = """
                [T][ ] one
                Task unmarked. Seriously?
                You have 1 task(s). Get moving.
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
    public void execute_delete_success() {
        Task task = new ToDoTask("one");
        tasks.addTask(task);

        HashMap<String, String> optionMap = new HashMap<>();

        optionMap.put("number", "1");

        TaskUpdateCommand command = new TaskUpdateCommand("delete", optionMap);

        String expected = "You have 0 task(s). Get moving.\n";
        String outputMessage;

        try {
            outputMessage = command.execute(tasks, storage);
        } catch (Exception e) {
            outputMessage = e.getMessage();
        }

        Assertions.assertEquals(expected.trim(), outputMessage.trim());
    }

    @Test
    public void execute_invalidArgument() {
        HashMap<String, String> optionMap = new HashMap<>();

        optionMap.put("number", "1");

        TaskUpdateCommand command = new TaskUpdateCommand("mark", optionMap);

        String expected = "Invalid number of tasks entered.";
        String outputMessage;

        try {
            outputMessage = command.execute(tasks, storage);
        } catch (Exception e) {
            outputMessage = e.getMessage();
        }

        Assertions.assertEquals(expected.trim(), outputMessage.trim());
    }
}
