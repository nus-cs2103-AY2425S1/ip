package stobberi.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stobberi.command.Command;
import stobberi.command.DateCommand;
import stobberi.command.DeadlineCommand;
import stobberi.command.DeleteCommand;
import stobberi.command.EventCommand;
import stobberi.command.ExitCommand;
import stobberi.command.FindCommand;
import stobberi.command.HelpCommand;
import stobberi.command.ListCommand;
import stobberi.command.MarkCommand;
import stobberi.command.TodoCommand;
import stobberi.command.UnmarkCommand;
import stobberi.stobberiexception.InvalidNumberStobberiException;
import stobberi.stobberiexception.NoSuchTaskStobberiException;
import stobberi.stobberiexception.StobberiException;

public class ParserTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(); // Initialize an empty TaskList
    }

    @Test
    public void testParseExitCommand() throws StobberiException {
        Command command = Parser.parse("bye", taskList);
        assertTrue(command instanceof ExitCommand, "Command should be an instance of ExitCommand.");
    }

    @Test
    public void testParseListCommand() throws StobberiException {
        Command command = Parser.parse("list", taskList);
        assertTrue(command instanceof ListCommand, "Command should be an instance of ListCommand.");
    }

    @Test
    public void testParseMarkCommand() throws StobberiException {
        Command command = Parser.parse("mark 1", taskList);
        assertTrue(command instanceof MarkCommand, "Command should be an instance of MarkCommand.");
    }

    @Test
    public void testParseUnmarkCommand() throws StobberiException {
        Command command = Parser.parse("unmark 1", taskList);
        assertTrue(command instanceof UnmarkCommand, "Command should be an instance of UnmarkCommand.");
    }

    @Test
    public void testParseDeleteCommand() throws StobberiException {
        Command command = Parser.parse("delete 1", taskList);
        assertTrue(command instanceof DeleteCommand, "Command should be an instance of DeleteCommand.");
    }

    @Test
    public void testParseDateCommand() throws StobberiException {
        Command command = Parser.parse("date 18-09-2024 2359", taskList);
        assertTrue(command instanceof DateCommand, "Command should be an instance of DateCommand.");
    }

    @Test
    public void testParseTodoCommand() throws StobberiException {
        Command command = Parser.parse("todo Read book | 0", taskList);
        assertTrue(command instanceof TodoCommand, "Command should be an instance of TodoCommand.");
    }

    @Test
    public void testParseDeadlineCommand() throws StobberiException {
        Command command = Parser.parse("deadline Finish project by 20-09-2024 2359 | 0", taskList);
        assertTrue(command instanceof DeadlineCommand, "Command should be an instance of DeadlineCommand.");
    }

    @Test
    public void testParseEventCommand() throws StobberiException {
        Command command = Parser.parse("event Attend conference on 25-09-2024 0900 | 1", taskList);
        assertTrue(command instanceof EventCommand, "Command should be an instance of EventCommand.");
    }

    @Test
    public void testParseFindCommand() throws StobberiException {
        Command command = Parser.parse("find book", taskList);
        assertTrue(command instanceof FindCommand, "Command should be an instance of FindCommand.");
    }

    @Test
    public void testParseHelpCommand() throws StobberiException {
        Command command = Parser.parse("?", taskList);
        assertTrue(command instanceof HelpCommand, "Command should be an instance of HelpCommand.");
    }

    @Test
    public void testParseInvalidCommand() {
        Exception exception = assertThrows(NoSuchTaskStobberiException.class, () -> {
            Parser.parse("invalidCommand", taskList);
        });
        assertEquals("UMmmmmm, I'm weallly sowwyyy!!!!\n\n"
                + "I just don't know how to do that yettt.", exception.getMessage());
    }

    @Test
    public void testParseWithInvalidSpacing() {
        Exception exception = assertThrows(InvalidNumberStobberiException.class, () -> {
            Parser.parse("todo   Read book", taskList); // Extra spaces in the command
        });
        assertEquals("UMmmmmm, I'm weallly sowwyyy!!!!\n\n"
                + "Please ensure your spacing are all correct.\nThankieww.", exception.getMessage());
    }
}

