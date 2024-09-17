package bottleopener;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bottleopener.command.AddCommand;
import bottleopener.command.ByeCommand;
import bottleopener.command.Command;
import bottleopener.command.DeleteCommand;
import bottleopener.command.ErrorCommand;
import bottleopener.command.FindCommand;
import bottleopener.command.ListCommand;
import bottleopener.command.MarkCommand;
import bottleopener.command.RemindCommand;
import bottleopener.myapp.Parser;
import bottleopener.task.Tasklist;


class ParserTest {

    private Tasklist tasklist;

    @BeforeEach
    public void setUp() {
        tasklist = new Tasklist();
    }

    @Test
    public void testValidListCommand_returnsListCommand() {
        Parser parser = new Parser("list", tasklist);
        Command command = parser.runParser();
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void testByeCommand_returnsByeCommand() {
        Parser parser = new Parser("bye", tasklist);
        Command command = parser.runParser();
        assertTrue(command instanceof ByeCommand);
        assertTrue(parser.checkExit());
    }

    @Test
    public void testMarkCommand_returnsMarkCommand() {
        Parser parser = new Parser("mark 1", tasklist);
        Command command = parser.runParser();
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void testUnmarkCommand_returnsUnmarkCommand() {
        Parser parser = new Parser("unmark 2", tasklist);
        Command command = parser.runParser();
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void testDeleteCommand_returnsDeleteCommand() {
        Parser parser = new Parser("delete 3", tasklist);
        Command command = parser.runParser();
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void testRemindCommand_returnsRemindCommand() {
        Parser parser = new Parser("remind", tasklist);
        Command command = parser.runParser();
        assertTrue(command instanceof RemindCommand);
    }

    @Test
    public void testAddTodoCommand_returnsAddCommand() {
        Parser parser = new Parser("todo read book", tasklist);
        Command command = parser.runParser();
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void testAddDeadlineCommand_returnsAddCommand() {
        Parser parser = new Parser("deadline finish project", tasklist);
        Command command = parser.runParser();
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void testFindCommand_returnsFindCommand() {
        Parser parser = new Parser("find keyword", tasklist);
        Command command = parser.runParser();
        assertTrue(command instanceof FindCommand);
    }

    @Test
    public void testInvalidCommand_returnsErrorCommand() {
        Parser parser = new Parser("unknown", tasklist);
        Command command = parser.runParser();
        assertTrue(command instanceof ErrorCommand);
    }
}
