package calebyyy;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calebyyy.commands.AddCommand;
import calebyyy.commands.ByeCommand;
import calebyyy.commands.Command;
import calebyyy.commands.DeleteCommand;
import calebyyy.commands.FindCommand;
import calebyyy.commands.ListCommand;
import calebyyy.commands.MarkCommand;
import calebyyy.commands.UnmarkCommand;
import calebyyy.exceptions.InvalidCommandException;

public class ParserTest {
    private Parser parser;
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Calebyyy calebyyy;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
        taskList = new TaskList(ui);
        storage = new Storage("data/tasks.txt");
        calebyyy = new Calebyyy();
        parser = new Parser(calebyyy, taskList, storage, ui);
    }

    @Test
    public void testGetCommand_validCommand() {
        Command command = parser.getCommand(Parser.CommandType.LIST);
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void testExecuteCommand_invalidCommand() {
        assertThrows(InvalidCommandException.class, () -> {
            parser.executeCommand("invalidCommand");
        });
    }

    @Test
    public void testGetCommand_addCommand() {
        Command command = parser.getCommand(Parser.CommandType.ADD);
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void testGetCommand_markCommand() {
        Command command = parser.getCommand(Parser.CommandType.MARK);
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void testGetCommand_unmarkCommand() {
        Command command = parser.getCommand(Parser.CommandType.UNMARK);
        assertTrue(command instanceof UnmarkCommand);
    }

    @Test
    public void testGetCommand_deleteCommand() {
        Command command = parser.getCommand(Parser.CommandType.DELETE);
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void testGetCommand_findCommand() {
        Command command = parser.getCommand(Parser.CommandType.FIND);
        assertTrue(command instanceof FindCommand);
    }

    @Test
    public void testGetCommand_byeCommand() {
        Command command = parser.getCommand(Parser.CommandType.BYE);
        assertTrue(command instanceof ByeCommand);
    }

    @Test
    public void testExecuteCommand_validListCommand() {
        assertDoesNotThrow(() -> {
            parser.executeCommand("list");
        });
    }

    @Test
    public void testExecuteCommand_validAddCommand() {
        assertDoesNotThrow(() -> {
            parser.executeCommand("todo read book");
        });
    }
}
