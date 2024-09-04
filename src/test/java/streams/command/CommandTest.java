package streams.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import streams.task.*;
import streams.util.*;
import streams.exception.StreamsException;

public class CommandTest {
    @Test
    void testIsExit() {
        Command command = new Command() {
            @Override
            public void execute(TaskList tasks, Ui ui, Storage storage) throws StreamsException {
                // Do nothing
            }
        };
        assertFalse(command.isExit());
    }
}