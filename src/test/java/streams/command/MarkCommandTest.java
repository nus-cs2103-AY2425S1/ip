package streams.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import streams.task.*;
import streams.util.*;
import streams.exception.StreamsException;

public class MarkCommandTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage("test.txt");
    }

    @Test
    void testMarkTaskAsDone() throws StreamsException {
        taskList.addTask(new ToDoTask("Task 1"));

        Command markCommand = new MarkCommand("1", true);
        markCommand.execute(taskList, ui, storage);

        assertTrue(taskList.getTask(0).isCompleted());
    }

    @Test
    void testMarkTaskAsUndone() throws StreamsException {
        Task task = new ToDoTask("Task 1");
        task.markAsDone();
        taskList.addTask(task);

        Command markCommand = new MarkCommand("1", false);
        markCommand.execute(taskList, ui, storage);

        assertFalse(taskList.getTask(0).isCompleted());
    }

    @Test
    void testMarkInvalidTask() throws StreamsException {
        Command markCommand = new MarkCommand("1", true);
        assertThrows(StreamsException.class, () -> markCommand.execute(taskList, ui, storage));
    }
}