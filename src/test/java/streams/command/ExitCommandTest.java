package streams.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import streams.task.*;
import streams.util.*;

public class ExitCommandTest {
    @Test
    void testIsExit() {
        ExitCommand exitCommand = new ExitCommand();
        assertTrue(exitCommand.isExit());
    }

    @Test
    void testExecute() {
        ExitCommand exitCommand = new ExitCommand();
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");

        // This should not throw any exception
        assertDoesNotThrow(() -> exitCommand.execute(taskList, ui, storage));
    }
}