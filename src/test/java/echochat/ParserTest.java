package echochat;

import Exceptions.EmptyDescriptionError;
import Exceptions.InvalidCommandError;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    void testParseExitCommand() throws EmptyDescriptionError, InvalidCommandError {
        Command command = parser.parse("bye");
        assertEquals(CommandType.EXIT, command.getType());
        assertNull(command.getTask());
    }

    @Test
    void testParseListCommand() throws EmptyDescriptionError, InvalidCommandError {
        Command command = parser.parse("list");
        assertEquals(CommandType.LIST, command.getType());
        assertNull(command.getTask());
    }

    @Test
    void testParseMarkCommand() throws EmptyDescriptionError, InvalidCommandError {
        Command command = parser.parse("mark 2");
        assertEquals(CommandType.MARK, command.getType());
        assertEquals(2, command.getIndex());
    }

    @Test
    void testParseUnmarkCommand() throws EmptyDescriptionError, InvalidCommandError {
        Command command = parser.parse("unmark 3");
        assertEquals(CommandType.UNMARK, command.getType());
        assertEquals(3, command.getIndex());
    }

    @Test
    void testParseDeleteCommand() throws EmptyDescriptionError, InvalidCommandError {
        Command command = parser.parse("delete 1");
        assertEquals(CommandType.DELETE, command.getType());
        assertEquals(1, command.getIndex());
    }

    @Test
    void testParseTodoCommand() throws EmptyDescriptionError, InvalidCommandError {
        Command command = parser.parse("todo read book");
        assertEquals(CommandType.TODO, command.getType());
        assertNotNull(command.getTask());
        assertEquals("[T][ ] read book", command.getTask().getDesc());
    }

    @Test
    void testParseDeadlineCommand() throws EmptyDescriptionError, InvalidCommandError {
        Command command = parser.parse("deadline submit assignment /by tomorrow");
        assertEquals(CommandType.DEADLINE, command.getType());
        assertNotNull(command.getTask());
        assertTrue(command.getTask() instanceof Deadline);
        assertEquals("[D][ ] submit assignment (by: tomorrow)", command.getTask().getDesc());
        assertEquals("tomorrow", ((Deadline) command.getTask()).getBy());
    }

    @Test
    void testParseEventCommand() throws EmptyDescriptionError, InvalidCommandError {
        Command command = parser.parse("event meeting /from Monday /to Wednesday");
        assertEquals(CommandType.EVENT, command.getType());
        assertNotNull(command.getTask());
        assertTrue(command.getTask() instanceof Event);
        assertEquals("[E][ ] meeting (from: Monday to: Wednesday)", command.getTask().getDesc());
        assertEquals("Monday", ((Event) command.getTask()).getFrom());
        assertEquals("Wednesday", ((Event) command.getTask()).getTo());
    }

    @Test
    void testParseEmptyDescriptionError() {
        assertThrows(EmptyDescriptionError.class, () -> parser.parse("todo "));
        assertThrows(EmptyDescriptionError.class, () -> parser.parse("deadline /by tomorrow"));
        assertThrows(EmptyDescriptionError.class, () -> parser.parse("event /from Monday /to Wednesday"));
    }

    @Test
    void testParseInvalidCommandError() {
        assertThrows(InvalidCommandError.class, () -> parser.parse("invalidcommand"));
        assertThrows(InvalidCommandError.class, () -> parser.parse("mark"));
        assertThrows(InvalidCommandError.class, () -> parser.parse("delete"));
    }

}
