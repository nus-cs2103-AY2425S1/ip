package streams.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import streams.task.*;
import streams.util.*;
import streams.exception.StreamsException;

public class SortDeadlineCommandTest {
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
    void testSortDeadlinesWithNoDeadlineTasks() throws StreamsException {
        taskList.addTask(new ToDoTask("Task 1"));

        SortDeadlineCommand sortCommand = new SortDeadlineCommand();
        sortCommand.execute(taskList, ui, storage);

    }
}