package seedu.avo.parser;

import org.junit.jupiter.api.Test;
import seedu.avo.commands.CommandManager;
import seedu.avo.commands.ExitCommand;
import seedu.avo.commands.UnknownCommand;
import seedu.avo.mocks.MockStorage;
import seedu.avo.storage.Storage;
import seedu.avo.tasks.Task;
import seedu.avo.tasks.TaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandParserTest {
    private final CommandParser parser;
    public CommandParserTest() {
        Storage<Task, String> storage = new MockStorage<>();
        TaskManager taskManager = new TaskManager(storage);
        parser = new CommandParser(new CommandManager(taskManager));
    }
    @Test
    public void testEmptyString(){
        assertEquals(UnknownCommand.of(), parser.parse(""));
    }
    @Test
    public void testExitString(){
        assertEquals(ExitCommand.of(), parser.parse("exit"));
    }
}
