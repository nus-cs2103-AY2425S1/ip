package assistinator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import assistinator.commands.ByeCommand;
import assistinator.commands.Command;
import assistinator.commands.DeadlineCommand;
import assistinator.commands.DeleteCommand;
import assistinator.commands.EventCommand;
import assistinator.commands.FindCommand;
import assistinator.commands.InvalidCommand;
import assistinator.commands.ListCommand;
import assistinator.commands.MarkCommand;
import assistinator.commands.TodoCommand;
import assistinator.commands.UnmarkCommand;

/**
 * Test class for the Parser's parseCommand method.
 * This class contains unit tests to ensure that the parseCommand method
 * correctly identifies and creates the appropriate Command objects based on input.
 */
class ParserTest {

    private Parser parser;

    /**
     * Sets up a new Parser instance before each test.
     */
    @BeforeEach
    void setUp() {
        parser = new Parser();
    }

    /**
     * Tests that the "list" command is correctly parsed into a ListCommand object.
     */
    @Test
    void testParseListCommand() {
        Command command = parser.parseCommand("list");
        assertTrue(command instanceof ListCommand);
    }

    /**
     * Tests that a "todo" command with a task description is correctly parsed into a TodoCommand object.
     */
    @Test
    void testParseTodoCommand() {
        Command command = parser.parseCommand("todo Buy groceries");
        assertTrue(command instanceof TodoCommand);
    }

    /**
     * Tests that a "deadline" command with a task description and
     * due date is correctly parsed into a DeadlineCommand object.
     */
    @Test
    void testParseDeadlineCommand() {
        Command command = parser.parseCommand("deadline Submit report /by 2023-09-15");
        assertTrue(command instanceof DeadlineCommand);
    }

    /**
     * Tests that an "event" command with a description, start time, and end time
     * is correctly parsed into an EventCommand object.
     */
    @Test
    void testParseEventCommand() {
        Command command = parser.parseCommand("event Team meeting /from 2023-09-15 14:00 /to 2023-09-15 15:00");
        assertTrue(command instanceof EventCommand);
    }

    /**
     * Tests that a "mark" command with a task number is correctly parsed into a MarkCommand object.
     */
    @Test
    void testParseMarkCommand() {
        Command command = parser.parseCommand("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    /**
     * Tests that an "unmark" command with a task number is correctly parsed into an UnmarkCommand object.
     */
    @Test
    void testParseUnmarkCommand() {
        Command command = parser.parseCommand("unmark 1");
        assertTrue(command instanceof UnmarkCommand);
    }

    /**
     * Tests that a "delete" command with a task number is correctly parsed into a DeleteCommand object.
     */
    @Test
    void testParseDeleteCommand() {
        Command command = parser.parseCommand("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    /**
     * Tests that a "find" command with a search keyword is correctly parsed into a FindCommand object.
     */
    @Test
    void testParseFindCommand() {
        Command command = parser.parseCommand("find meeting");
        assertTrue(command instanceof FindCommand);
    }

    /**
     * Tests that a "bye" command is correctly parsed into a ByeCommand object.
     */
    @Test
    void testParseByeCommand() {
        Command command = parser.parseCommand("bye");
        assertTrue(command instanceof ByeCommand);
    }

    /**
     * Tests that an invalid command is correctly parsed into an InvalidCommand object.
     */
    @Test
    void testParseInvalidCommand() {
        Command command = parser.parseCommand("invalidcommand");
        assertTrue(command instanceof InvalidCommand);
    }

    /**
     * Tests that the command parsing is case-insensitive.
     */
    @Test
    void testParseCaseInsensitivity() {
        Command command = parser.parseCommand("LIST");
        assertTrue(command instanceof ListCommand);
    }
}
