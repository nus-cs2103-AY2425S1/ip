package streams.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import streams.task.*;
import streams.util.*;
import streams.exception.StreamsException;

public class DeleteCommandTest {
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
    void testDeleteTask() throws StreamsException {
        taskList.addTask(new ToDoTask("Task 1"));
        taskList.addTask(new ToDoTask("Task 2"));

        Command deleteCommand = new DeleteCommand("1");
        deleteCommand.execute(taskList, ui, storage);

        assertEquals(1, taskList.size());
        assertEquals("Task 2", taskList.getTask(0).getDescription());
    }

    @Test
    void testDeleteInvalidTask() throws StreamsException {
        taskList.addTask(new ToDoTask("Task 1"));

        Command deleteCommand = new DeleteCommand("2");
        assertThrows(StreamsException.class, () -> deleteCommand.execute(taskList, ui, storage));
    }
}