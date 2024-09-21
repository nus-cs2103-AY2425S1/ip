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
    public void getArguments_shouldReturnArguments() throws MummyException {
        Command command = Command.of("bye");
        assertEquals("bye", command.getArgument("command"));
    }

    @Test
    public void saveTaskListToStorage_shouldSaveToFile() throws MummyException, IOException {
        Command command = new StubCommand();
        List<String> fileRecords = taskList.toFileRecords();
        command.saveTaskListToStorage(taskList, storage);
        assertEquals(fileRecords, storage.load());
    }

    @Test
    public void of_withValidCommandType_shouldReturnCorrectCommand() throws MummyException {
        Command command = Command.of("bye");
        assertInstanceOf(ByeCommand.class, command);
    }

    @Test
    public void of_withUnknownCommandType_shouldThrowMummyException() {
        try {
            Command.of("skdhdjshfjgh");
        } catch (MummyException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void isExit_withExitCommand_shouldReturnTrue() throws MummyException {
        Command command = Command.of("bye");
        assertTrue(command.isExit());
    }

    @Test
    public void isExit_withNonExitCommand_shouldReturnFalse() {
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

        @Override
        public CommandType getCommandType() {
            return CommandType.UNKNOWN;
        }

        @Override
        public String undo(TaskList taskList, Storage storage) {
            return "Undid stub command";
        }
    }
}
