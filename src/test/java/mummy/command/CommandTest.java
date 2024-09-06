package mummy.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mummy.task.TaskList;
import mummy.ui.MummyException;
import mummy.utility.Storage;


public class CommandTest {

    private TaskList taskList;
    private Storage storage;

    @BeforeEach
    public void setup() {
        this.taskList = new TaskList();
        this.storage = new Storage("data/test.txt");
    }

    @Test
    public void getArgumentsShouldReturnArguments() throws MummyException {
        Command command = Command.of("bye");
        assertEquals("bye", command.getArgument("command"));
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
        Command command = Command.of("bye");
        assertInstanceOf(ByeCommand.class, command);
    }

    @Test
    public void ofWithUnknownCommandTypeShouldThrowMummyException() {
        try {
            Command.of("skdhdjshfjgh");
        } catch (MummyException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void isExitWithExitCommandShouldReturnTrue() throws MummyException {
        Command command = Command.of("bye");
        assertTrue(command.isExit());
    }

    @Test
    public void isExitWithNonExitCommandShouldReturnFalse() {
        Command command = new StubCommand();
        assertFalse(command.isExit());
    }

    private static class StubCommand extends Command {

        public StubCommand(HashMap<String, String> arguments) {
            super(arguments);
        }

        public StubCommand() {
            super(new HashMap<>());
        }

        @Override
        public String execute(TaskList taskList, Storage storage) {
            return "This is a stub command";
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }
}
