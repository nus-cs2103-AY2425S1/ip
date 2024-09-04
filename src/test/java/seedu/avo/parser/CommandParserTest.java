package seedu.avo.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.avo.commands.CommandManager;
import seedu.avo.commands.UnknownCommand;
import seedu.avo.mocks.MockStorage;
import seedu.avo.storage.Storage;
import seedu.avo.tasks.Task;
import seedu.avo.tasks.TaskManager;
import seedu.avo.ui.ResponseFormatter;

public class CommandParserTest {
    private final CommandParser parser;
    public CommandParserTest() {
        Storage<Task, String> storage = new MockStorage<>();
        TaskManager taskManager = new TaskManager(storage, new ResponseFormatter());
        parser = new CommandParser(new CommandManager(taskManager));
    }
    @Test
    public void testEmptyString() {
        assertEquals(UnknownCommand.of(), parser.parse(""));
    }
}
