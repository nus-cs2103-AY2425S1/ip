package duck.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duck.command.Command;
import duck.command.DeleteCommand;
import duck.command.FindCommand;
import duck.command.InvalidCommandException;
import duck.command.ListCommand;
import duck.command.MarkCommand;
import duck.command.RemindCommand;
import duck.command.TaskCommand;
import duck.command.UnmarkCommand;

class ParserTest {

    @Test
    void testParseTaskCommand() throws InvalidCommandException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof TaskCommand);

        command = Parser.parse("deadline submit assignment /by 2024-09-30");
        assertTrue(command instanceof TaskCommand);

        command = Parser.parse("event team meeting /at 2024-10-01");
        assertTrue(command instanceof TaskCommand);
    }

    @Test
    void testParseMarkCommand() throws InvalidCommandException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    void testParseUnmarkCommand() throws InvalidCommandException {
        Command command = Parser.parse("unmark 2");
        assertTrue(command instanceof UnmarkCommand);
    }

    @Test
    void testParseDeleteCommand() throws InvalidCommandException {
        Command command = Parser.parse("delete 3");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    void testParseListCommand() throws InvalidCommandException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    void testParseFindCommand() throws InvalidCommandException {
        Command command = Parser.parse("find homework");
        assertTrue(command instanceof FindCommand);
    }

    @Test
    void testParseRemindCommand() throws InvalidCommandException {
        Command command = Parser.parse("remind");
        assertTrue(command instanceof RemindCommand);
    }

    @Test
    void testParseInvalidCommand() {
        Exception exception = assertThrows(InvalidCommandException.class, () -> {
            Parser.parse("invalid command");
        });

        String expectedMessage = "Invalid command: invalid";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
