package susan.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;
import susan.command.*;

public class ParserTest {
    @Test
    public void testHelloCommand() throws SusanException {
        Command command = Parser.parse("hello");
        assertInstanceOf(HelloCommand.class, command);
    }

    @Test
    public void testByeCommand() throws SusanException {
        Command command = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    public void testListCommand() throws SusanException {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void testRemindCommand() throws SusanException {
        Command command = Parser.parse("remindme");
        assertInstanceOf(RemindCommand.class, command);
    }

    @Test
    public void testAddCommand() throws SusanException {
        Command command = Parser.parse("todo");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    public void testDeleteCommand() throws SusanException {
        Command command = Parser.parse("delete");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    public void testMarkCommand() throws SusanException {
        Command command = Parser.parse("mark");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void testUnmarkCommand() throws SusanException {
        Command command = Parser.parse("unmark");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void testFindCommand() throws SusanException {
        Command command = Parser.parse("find");
        assertInstanceOf(FindCommand.class, command);
    }

    @Test
    public void testEmptyCommand() throws SusanException {
        try {
            Command command = Parser.parse("");
        } catch (SusanException e) {
            assertEquals(e.getMessage(),
                "You cry a lot and you are not productive! Please give a valid command.");
        }
    }

    @Test
    public void testInvalidCommand() throws SusanException {
        try {
            Command command = Parser.parse("nothing");
        } catch (SusanException e) {
            assertEquals(e.getMessage(),
                "You cry a lot and you are not productive! Please give a valid command.");
        }
    }
}
