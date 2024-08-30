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

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() throws TheBotFatherException {
        ui = new Ui();
        storage = new Storage("./data/TheBotFatherTest.txt");
        taskList = storage.load();
    }

    @AfterEach
    void finish() throws TheBotFatherException{
        storage.toFile(taskList);
    }

    @Test
    void testParseByeCommand() throws TheBotFatherException {
        Command command = Parser.parse("bye", ui);
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    void testParseListCommand() throws TheBotFatherException {
        Command command = Parser.parse("list", ui);
        assertInstanceOf(PrintTaskListCommand.class, command);
    }

    @Test
    void testParseMarkCommand() throws TheBotFatherException {
        Command command = Parser.parse("mark 1", ui);
        assertInstanceOf(MarkCommand.class, command);
        MarkCommand markCommand = (MarkCommand) command;
        markCommand.execute(taskList, ui, storage);
        assertEquals("[T][X] read book", taskList.getTaskDescAtIndex(0));
    }

    @Test
    void testParseUnmarkCommand() throws TheBotFatherException {
        Command command = Parser.parse("unmark 2", ui);
        assertInstanceOf(MarkCommand.class, command);
        MarkCommand markCommand = (MarkCommand) command;
        markCommand.execute(taskList, ui, storage);
        assertEquals("[E][ ] read book (from: 30 Aug 2024, 16:00 to: 31 Aug 2024, 23:59)",
                taskList.getTaskDescAtIndex(1));
    }

    @Test
    void testParseDeleteCommand() throws TheBotFatherException {
        Command command = Parser.parse("delete 3", ui);
        assertInstanceOf(DeleteCommand.class, command);
        DeleteCommand deleteCommand = (DeleteCommand) command;
        deleteCommand.execute(taskList, ui, storage);
        assertEquals("[T][X] this should be the new index 2", taskList.getTaskDescAtIndex(2));
    }

    @Test
    void testParseTodoCommand() throws TheBotFatherException {
        Command command = Parser.parse("todo read book", ui);
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        addCommand.execute(taskList, ui, storage);
        assertEquals("[T][ ] read book", taskList.getTaskDescAtIndex(taskList.numberOfElements() - 1));
    }

    @Test
    void testParseEventCommand() throws TheBotFatherException {
        Command command = Parser.parse("event project meeting /from 01-09-2024 12:00 /to 02-09-2024 23:59", ui);
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        addCommand.execute(taskList, ui, storage);
        assertEquals("[E][ ] project meeting (from: 01 Sep 2024, 12:00 to: 02 Sep 2024, 23:59)", taskList.getTaskDescAtIndex(taskList.numberOfElements() - 1));
    }

    @Test
    void testParseDeadlineCommand() throws TheBotFatherException {
        Command command = Parser.parse("deadline submit report /by 01-09-2024 12:00", ui);
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        addCommand.execute(taskList, ui, storage);
        assertEquals("[D][ ] submit report (by: 01 Sep 2024, 12:00)", taskList.getTaskDescAtIndex(taskList.numberOfElements() - 1));
    }

    @Test
    void testParseInvalidCommand() {
        TheBotFatherException exception = assertThrows(TheBotFatherException.class, () ->
                Parser.parse("invalidCommand", ui));
        assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(.\n" +
                "\tUse \"bye\" if you want to exit the program", exception.getMessage());
    }

    @Test
    void testParseMarkCommandWithoutIndex() {
        TheBotFatherException exception = assertThrows(TheBotFatherException.class, () ->
                Parser.parse("mark", ui));
        assertEquals("Skill issue: Atleast enter a number.\n" +
                "\tTo mark a task as done enter \"mark <index>\"", exception.getMessage());
    }

    @Test
    void testParseUnmarkCommandWithoutIndex() {
        TheBotFatherException exception = assertThrows(TheBotFatherException.class, () ->
                Parser.parse("unmark", ui));
        assertEquals("Skill issue: Atleast enter a number.\n" +
                "\tTo unmark a task enter \"unmark <index>\"", exception.getMessage());
    }

    @Test
    void testParseDeleteCommandWithoutIndex() {
        TheBotFatherException exception = assertThrows(TheBotFatherException.class, () ->
                Parser.parse("delete", ui));
        assertEquals("Skill issue: Atleast enter a number.\n" +
                "\tTo unmark a task enter \"unmark <index>\"", exception.getMessage());
    }
}
