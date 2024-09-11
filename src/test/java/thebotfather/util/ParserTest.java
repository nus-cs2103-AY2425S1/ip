package thebotfather.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import thebotfather.command.Command;
import thebotfather.command.ExitCommand;
import thebotfather.command.MarkCommand;
import thebotfather.command.DeleteCommand;
import thebotfather.command.AddCommand;
import thebotfather.command.PrintTaskListCommand;
import thebotfather.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

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

    /**
     * Tests the parsing and execution of the "delete" command.
     *
     * @throws TheBotFatherException If the parsing or execution fails.
     */
    @Test
    void testParseDeleteCommand() throws TheBotFatherException {
        Command command = Parser.parse("delete 3", ui);
        assertInstanceOf(DeleteCommand.class, command);
        DeleteCommand deleteCommand = (DeleteCommand) command;
        deleteCommand.execute(taskList, ui, storage);
        assertEquals("[T][X] this should be the new index 2", taskList.getTaskDescAtIndex(2));
    }

    /**
     * Tests the parsing and execution of the "todo" command.
     *
     * @throws TheBotFatherException If the parsing or execution fails.
     */
    @Test
    void testParseTodoCommand() throws TheBotFatherException {
        Command command = Parser.parse("todo read book", ui);
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        addCommand.execute(taskList, ui, storage);
        assertEquals("[T][ ] read book", taskList.getTaskDescAtIndex(taskList.numberOfElements() - 1));
    }

    /**
     * Tests the parsing and execution of the "event" command.
     *
     * @throws TheBotFatherException If the parsing or execution fails.
     */
    @Test
    void testParseEventCommand() throws TheBotFatherException {
        Command command = Parser.parse("event project meeting /from 01-09-2024 12:00 /to 02-09-2024 23:59",
                ui);
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        addCommand.execute(taskList, ui, storage);
        assertEquals("[E][ ] project meeting (from: 01 Sep 2024, 12:00 to: 02 Sep 2024, 23:59)",
                taskList.getTaskDescAtIndex(taskList.numberOfElements() - 1));
    }

    /**
     * Tests the parsing and execution of the "deadline" command.
     *
     * @throws TheBotFatherException If the parsing or execution fails.
     */
    @Test
    void testParseDeadlineCommand() throws TheBotFatherException {
        Command command = Parser.parse("deadline submit report /by 01-09-2024 12:00", ui);
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        addCommand.execute(taskList, ui, storage);
        assertEquals("[D][ ] submit report (by: 01 Sep 2024, 12:00)", taskList.getTaskDescAtIndex(taskList.numberOfElements() - 1));
    }

    /**
     * Tests the parsing of an invalid command.
     */
    @Test
    void testParseInvalidCommand() {
        TheBotFatherException exception = assertThrows(TheBotFatherException.class, () ->
                Parser.parse("invalidCommand", ui));
        assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(.\n"
                + "\tUse \"bye\" if you want to exit the program", exception.getMessage());
    }

    /**
     * Tests the parsing of the "mark" command without an index.
     */
    @Test
    void testParseMarkCommandWithoutIndex() {
        TheBotFatherException exception = assertThrows(TheBotFatherException.class, () ->
                Parser.parse("mark", ui));
        assertEquals("Skill issue: Atleast enter a number.\n"
                + "\tTo mark a task as done enter \"mark <index>\"", exception.getMessage());
    }

    /**
     * Tests the parsing of the "unmark" command without an index.
     */
    @Test
    void testParseUnmarkCommandWithoutIndex() {
        TheBotFatherException exception = assertThrows(TheBotFatherException.class, () ->
                Parser.parse("unmark", ui));
        assertEquals("Skill issue: Atleast enter a number.\n"
                + "\tTo unmark a task enter \"unmark <index>\"", exception.getMessage());
    }

    /**
     * Tests the parsing of the "delete" command without an index.
     */
    @Test
    void testParseDeleteCommandWithoutIndex() {
        TheBotFatherException exception = assertThrows(TheBotFatherException.class, () ->
                Parser.parse("delete", ui));
        assertEquals("Skill issue: Atleast enter a number.\n"
                + "\tTo unmark a task enter \"unmark <index>\"", exception.getMessage());
    }
}
