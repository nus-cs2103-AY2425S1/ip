package streams.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import streams.task.*;
import streams.util.*;
import streams.exception.StreamsException;
import java.time.LocalDate;

public class ListWeekCommandTest {
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
    void testListWeekWithNoTasks() throws StreamsException {
        ListWeekCommand listWeekCommand = new ListWeekCommand();
        listWeekCommand.execute(taskList, ui, storage);
    }
}