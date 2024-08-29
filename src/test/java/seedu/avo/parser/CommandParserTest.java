package seedu.avo.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import seedu.avo.commands.CommandManager;
import seedu.avo.commands.ExitCommand;
import seedu.avo.commands.UnknownCommand;
import seedu.avo.mocks.MockStorage;
import seedu.avo.storage.Storage;
import seedu.avo.tasks.Task;
import seedu.avo.tasks.TaskManager;
import seedu.avo.ui.AppUI;

public class CommandParserTest {
    private final CommandParser parser;
    public CommandParserTest() {
        Storage<Task, String> storage = new MockStorage<>();
        TaskManager taskManager = new TaskManager(storage, new AppUI());
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
