package streams.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import streams.task.*;
import streams.util.*;
import streams.exception.StreamsException;

public class AddCommandTest {
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
    void testAddToDoTask() throws StreamsException {
        Command addCommand = new AddCommand(new ToDoTask("Read book"));
        addCommand.execute(taskList, ui, storage);
        assertEquals(1, taskList.size());
        assertTrue(taskList.getTask(0) instanceof ToDoTask);
        assertEquals("Read book", taskList.getTask(0).getDescription());
    }
}
