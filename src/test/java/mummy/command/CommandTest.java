package mummy.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mummy.task.TaskList;
import mummy.ui.MummyException;
import mummy.ui.Ui;
import mummy.utility.Storage;


public class CommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setup() {
        this.taskList = new TaskList();
        this.ui = new Ui("");
        this.storage = new Storage("data/test.txt");
    }

    @Test
    public void getArgumentsShouldReturnArguments() {
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "bye");
        Command command = new StubCommand(arguments);
        assertEquals(arguments.get("command"), command.getArgument("command"));
    }

    @Test
    public void saveTaskListToStorageShouldSaveToFile() throws MummyException, IOException {
        Command command = new StubCommand();
        List<String> fileRecords = taskList.toFileRecords();
        command.saveTaskListToStorage(taskList, storage);
        assertEquals(fileRecords, storage.load());
    }

    @Test
    public void ofWithValidCommandTypeShouldReturnCorrectCommand() throws MummyException {
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "bye");
        Command command = Command.of(arguments);
        assertInstanceOf(ByeCommand.class, command);
    }

    @Test
    public void ofWithUnknownCommandTypeShouldThrowMummyException() {
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "skdhdjshfjgh");
        try {
            Command.of(arguments);
        } catch (MummyException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void isExitWithExitCommandShouldReturnTrue() {
        Command command = new ByeCommand();
        assertTrue(command.isExit());
    }

    @Test
    public void isExitWithNonExitCommandShouldReturnFalse() {
        Command command = new ListCommand();
        assertFalse(command.isExit());
    }

    private static class StubCommand extends Command {

        public StubCommand(HashMap<String, String> arguments) {
            super(arguments);
        }

        public StubCommand() {}

        @Override
        public void execute(TaskList taskList, Ui ui, Storage storage) {
            // Do nothing
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }
}
