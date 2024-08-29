package util;

import command.Command;
import command.AddCommand;
import exception.ScheduloException;
import org.junit.jupiter.api.Test;
import task.TaskList;
import task.Todo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void testParseValidTodoCommand() throws ScheduloException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddCommand); // Check if it's an AddCommand
    }

    @Test
    public void testParseInvalidCommand() {
        assertThrows(ScheduloException.class, () -> {
            Parser.parse("invalid command");
        });
    }
}
