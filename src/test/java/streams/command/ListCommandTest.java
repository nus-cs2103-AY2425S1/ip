package streams.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import streams.task.*;
import streams.util.*;
import streams.exception.StreamsException;

public class ListCommandTest {
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
    void testListEmptyTasks() throws StreamsException {
        ListCommand listCommand = new ListCommand();
        listCommand.execute(taskList, ui, storage);
    }

    @Test
    void testListTasks() throws StreamsException {
        taskList.addTask(new ToDoTask("Task 1"));
        taskList.addTask(new ToDoTask("Task 2"));

        ListCommand listCommand = new ListCommand();
        listCommand.execute(taskList, ui, storage);
    }
}