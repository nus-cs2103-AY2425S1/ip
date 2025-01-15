package streams.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import streams.task.*;
import streams.util.*;
import streams.exception.StreamsException;

public class ListDateCommandTest {
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
    void testListDateWithNoTasks() throws StreamsException {
        ListDateCommand listDateCommand = new ListDateCommand("2023-12-31");
        listDateCommand.execute(taskList, ui, storage);
        // You might need to modify Ui to capture output for verification
    }
}