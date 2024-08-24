package zaibot.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zaibot.Storage;
import zaibot.TaskList;
import zaibot.Ui;
import zaibot.task.Task;
import zaibot.task.ToDoTask;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;

public class TaskUpdateCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final TaskList tasks = new TaskList();
    private final Storage storage = new Storage(tasks);

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        tasks.clearTasks();
    }

    @Test
    public void testMark() {
        tasks.addTask(new ToDoTask("one"));
        HashMap<String, String> optionMap = new HashMap<>();

        optionMap.put("number", "1");

        TaskUpdateCommand command = new TaskUpdateCommand("mark", optionMap);
        try {
            command.execute(tasks, storage);
        } catch (Exception e) {
            Ui.displayError(e);
        } finally {
            String expected = "-----------------------------------------------------------\n"
                    + "[T][X] one\n"
                    + "Task done. Finally.\n"
                    + "You have 1 task(s). Get moving.\n"
                    + "-----------------------------------------------------------\n";
            Assertions.assertEquals(expected.trim(),
                    outputStreamCaptor.toString().trim());
        }
    }

    @Test
    public void testUnmark() {
        Task task = new ToDoTask("one");
        task.setDone(true);
        tasks.addTask(task);

        HashMap<String, String> optionMap = new HashMap<>();

        optionMap.put("number", "1");

        TaskUpdateCommand command = new TaskUpdateCommand("unmark", optionMap);
        try {
            command.execute(tasks, storage);
        } catch (Exception e) {
            Ui.displayError(e);
        } finally {
            String expected = "-----------------------------------------------------------\n"
                    + "[T][ ] one\n"
                    + "Task unmarked. Seriously?\n"
                    + "You have 1 task(s). Get moving.\n"
                    + "-----------------------------------------------------------\n";
            Assertions.assertEquals(expected.trim(),
                    outputStreamCaptor.toString().trim());
        }
    }

    @Test
    public void testDelete() {
        Task task = new ToDoTask("one");
        tasks.addTask(task);

        HashMap<String, String> optionMap = new HashMap<>();

        optionMap.put("number", "1");

        TaskUpdateCommand command = new TaskUpdateCommand("delete", optionMap);
        try {
            command.execute(tasks, storage);
        } catch (Exception e) {
            Ui.displayError(e);
        } finally {
            String expected = "You have 0 task(s). Get moving.\n";
            Assertions.assertEquals(expected.trim(),
                    outputStreamCaptor.toString().trim());
        }
    }

    @Test
    public void testInvalidNumber() {
        HashMap<String, String> optionMap = new HashMap<>();

        optionMap.put("number", "1");

        TaskUpdateCommand command = new TaskUpdateCommand("mark", optionMap);
        try {
            command.execute(tasks, storage);
        } catch (Exception e) {
            Ui.displayError(e);
        } finally {
            String expected = "Invalid number of tasks entered.";
            Assertions.assertEquals(expected.trim(),
                    outputStreamCaptor.toString().trim());
        }
    }
}
