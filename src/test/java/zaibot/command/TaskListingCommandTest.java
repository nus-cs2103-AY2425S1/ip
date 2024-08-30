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
    private final TaskList tasks = new TaskList();
    private final Storage storage = new Storage(tasks);

    @BeforeEach
    public void setUp() {
        tasks.clearTasks();
    }

    @Test
    public void execute_noTask() {
        TaskListingCommand command = new TaskListingCommand();
        String expected = "";

        String outputMessage;
        try {
            outputMessage = command.execute(tasks, storage);
        } catch (Exception e) {
            outputMessage = e.getMessage();
        }
        Assertions.assertEquals(expected.trim(), outputMessage.trim());
    }

    @Test
    public void execute_oneTask() {
        tasks.addTask(new ToDoTask("one"));
        TaskListingCommand command = new TaskListingCommand();
        String expected = "1. [T][ ] one";
        String outputMessage;
        try {
            outputMessage = command.execute(tasks, storage);
        } catch (Exception e) {
            outputMessage = e.getMessage();
        }
        Assertions.assertEquals(expected.trim(), outputMessage.trim());
    }

    @Test
    public void execute_multipleTasks() {
        tasks.addTask(new ToDoTask("one"));
        tasks.addTask(new ToDoTask("two"));
        tasks.addTask(new ToDoTask("three"));
        TaskListingCommand command = new TaskListingCommand();
        String expected = "1. [T][ ] one\n" +
                "2. [T][ ] two\n" +
                "3. [T][ ] three";

        String outputMessage;
        try {
            outputMessage = command.execute(tasks, storage);
        } catch (Exception e) {
            outputMessage = e.getMessage();
        }
        Assertions.assertEquals(expected.trim(), outputMessage.trim());
    }
}
