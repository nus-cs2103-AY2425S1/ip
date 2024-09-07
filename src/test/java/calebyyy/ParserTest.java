package calebyyy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calebyyy.commands.Command;
import calebyyy.exceptions.InvalidCommandException;
import calebyyy.commands.ListCommand;

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
}