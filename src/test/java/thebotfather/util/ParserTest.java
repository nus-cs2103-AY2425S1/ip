package thebotfather.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import thebotfather.command.Command;
import thebotfather.command.ExitCommand;
import thebotfather.command.MarkCommand;
import thebotfather.command.PrintTaskListCommand;
import thebotfather.parser.Parser;


/**
 * A test suite for the {@link Parser} class.
 * Tests various parsing scenarios and ensures that the correct commands are returned.
 */
public class ParserTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Sets up the necessary components before each test.
     *
     * @throws TheBotFatherException If there is an issue with loading the task list.
     */
    @BeforeEach
    void setUp() throws TheBotFatherException {
        ui = new Ui();
        storage = new Storage("./data/TheBotFatherTest.txt");
        taskList = storage.load();
    }

    /**
     * Saves the task list to the file after each test.
     *
     * @throws TheBotFatherException If there is an issue with saving the task list.
     */
    @AfterEach
    void finish() throws TheBotFatherException {
        storage.toFile(taskList);
    }

    /**
     * Tests the parsing of the "bye" command.
     *
     * @throws TheBotFatherException If the parsing fails.
     */
    @Test
    void testParseByeCommand() throws TheBotFatherException {
        Command command = Parser.parse("bye", ui);
        assertInstanceOf(ExitCommand.class, command);
    }

    /**
     * Tests the parsing of the "list" command.
     *
     * @throws TheBotFatherException If the parsing fails.
     */
    @Test
    void testParseListCommand() throws TheBotFatherException {
        Command command = Parser.parse("list", ui);
        assertInstanceOf(PrintTaskListCommand.class, command);
    }

    /**
     * Tests the parsing and execution of the "mark" command.
     *
     * @throws TheBotFatherException If the parsing or execution fails.
     */
    @Test
    void testParseMarkCommand() throws TheBotFatherException {
        Command command = Parser.parse("mark 1", ui);
        assertInstanceOf(MarkCommand.class, command);
        MarkCommand markCommand = (MarkCommand) command;
        markCommand.execute(taskList, ui, storage);
        assertEquals("[T][X] read book", taskList.getTaskDescAtIndex(0));
    }

    /**
     * Tests the parsing and execution of the "unmark" command.
     *
     * @throws TheBotFatherException If the parsing or execution fails.
     */
    @Test
    void testParseUnmarkCommand() throws TheBotFatherException {
        Command command = Parser.parse("unmark 2", ui);
        assertInstanceOf(MarkCommand.class, command);
        MarkCommand markCommand = (MarkCommand) command;
        markCommand.execute(taskList, ui, storage);
        assertEquals("[E][ ] read book (from: 30 Aug 2024, 16:00 to: 31 Aug 2024, 23:59)",
                taskList.getTaskDescAtIndex(1));
    }
}
