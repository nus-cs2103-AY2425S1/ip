package seedu.avo.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.avo.commands.CommandManager;
import seedu.avo.commands.DeadlineCommand;
import seedu.avo.commands.DeleteCommand;
import seedu.avo.commands.EventCommand;
import seedu.avo.commands.ExitCommand;
import seedu.avo.commands.HelpCommand;
import seedu.avo.commands.ListCommand;
import seedu.avo.commands.MarkCommand;
import seedu.avo.commands.SearchDateCommand;
import seedu.avo.commands.SearchNameCommand;
import seedu.avo.commands.TodoCommand;
import seedu.avo.commands.UnknownCommand;
import seedu.avo.commands.UnmarkCommand;
import seedu.avo.mocks.MockStorage;
import seedu.avo.storage.Storage;
import seedu.avo.tasks.Task;
import seedu.avo.tasks.TaskManager;
import seedu.avo.ui.ResponseFormatter;

public class CommandParserTest {
    private final CommandParser parser;
    private final TaskManager taskManager;
    public CommandParserTest() {
        Storage<Task, String> storage = new MockStorage<>();
        taskManager = new TaskManager(storage, new ResponseFormatter());
        parser = new CommandParser(new CommandManager(taskManager));
    }
    @Test
    public void testEmptyString() {
        assertEquals(UnknownCommand.of(), parser.parse(""));
    }
    @Test
    public void testHelpCommand() {
        assertEquals(HelpCommand.of(), parser.parse("help"));
    }
    @Test
    public void testExitCommand() {
        assertEquals(ExitCommand.of(), parser.parse("exit"));
    }
    @Test
    public void testListCommand() {
        assertEquals(ListCommand.of(taskManager), parser.parse("list"));
    }
    @Test
    public void testTodoCommand() {
        assertEquals(TodoCommand.of(taskManager), parser.parse("todo name"));
    }
    @Test
    public void testDeadlineCommand() {
        assertEquals(DeadlineCommand.of(taskManager),
                parser.parse("deadline name /by 2024-01-01 00:00"));
    }
    @Test
    public void testEventCommand() {
        assertEquals(EventCommand.of(taskManager),
                parser.parse("event name /from 2024-01-01 00:00 /to 2024-01-01 00:00"));
    }
    @Test
    public void testDeleteCommand() {
        assertEquals(DeleteCommand.of(taskManager), parser.parse("delete 1"));
    }
    @Test
    public void testMarkCommand() {
        assertEquals(MarkCommand.of(taskManager), parser.parse("mark 1"));
    }
    @Test
    public void testUnmarkCommand() {
        assertEquals(UnmarkCommand.of(taskManager), parser.parse("unmark 1"));
    }
    @Test
    public void testSearchDateCommand() {
        assertEquals(SearchDateCommand.of(taskManager), parser.parse("on 2024-01-01"));
    }
    @Test
    public void testSearchNameCommand() {
        assertEquals(SearchNameCommand.of(taskManager), parser.parse("find name"));
    }
}
