package zaibot.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zaibot.utils.Storage;
import zaibot.utils.TaskList;
import zaibot.utils.Ui;
import zaibot.task.ToDoTask;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TaskListingCommandTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final TaskList tasks = new TaskList();
    private final Storage storage = new Storage(tasks);

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        tasks.clearTasks();
    }

    @Test
    public void execute_noTask() {
        TaskListingCommand command = new TaskListingCommand();
        try {
            command.execute(tasks, storage);
        } catch (Exception e) {
            Ui.displayError(e);
        } finally {
            Assertions.assertEquals("",
                    outputStreamCaptor.toString().trim());
        }
    }

    @Test
    public void execute_oneTask() {
        tasks.addTask(new ToDoTask("one"));
        TaskListingCommand command = new TaskListingCommand();
        try {
            command.execute(tasks, storage);
        } catch (Exception e) {
            Ui.displayError(e);
        } finally {
            String expected = "1. [T][ ] one";
            Assertions.assertEquals(expected.trim(),
                    outputStreamCaptor.toString().trim());
        }
    }

    @Test
    public void execute_multipleTasks() {
        tasks.addTask(new ToDoTask("one"));
        tasks.addTask(new ToDoTask("two"));
        tasks.addTask(new ToDoTask("three"));
        TaskListingCommand command = new TaskListingCommand();
        try {
            command.execute(tasks, storage);
        } catch (Exception e) {
            Ui.displayError(e);
        } finally {
            String expected = "1. [T][ ] one\n" +
                    "2. [T][ ] two\n" +
                    "3. [T][ ] three";
            Assertions.assertEquals(expected.trim(),
                    outputStreamCaptor.toString().trim());
        }
    }
}
