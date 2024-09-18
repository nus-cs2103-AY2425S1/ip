package mummy.ui;

import java.io.IOException;

import mummy.command.Command;
import mummy.command.CommandManager;
import mummy.task.TaskList;
import mummy.utility.Storage;


/**
 * The Mummy class represents the service class of the Mummy application.
 * It handles the initialization of the storage, loading of the task list,
 * and execution of user commands.
 */
public class Mummy {
    private static final String LOGO = "Mummy";

    private static final String IO_PATH = "./data/mummy.txt";

    private final CommandManager commandManager;

    /**
     * Constructs a new instance of the Mummy class.
     * Initializes the storage with the given file path.
     * Loads the task list from the storage file if it exists,
     * otherwise creates a new empty task list.
     */
    public Mummy() {
        Storage storage = new Storage(IO_PATH);
        TaskList taskList;

        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            taskList = new TaskList();
        }

        this.commandManager = new CommandManager(taskList, storage);
    }

    public String getResponse(String input) {
        try {
            Command command = Command.of(input);
            return commandManager.execute(command);
        } catch (MummyException exception) {
            return exception.getMessage();
        }
    }

    public Command.CommandType getCommandType() {
        try {
            Command command = commandManager.getMostRecentCommand();
            return command.getCommandType();
        } catch (MummyException exception) {
            return Command.CommandType.UNKNOWN;
        }
    }

    public String generateWelcomeMessage() {
        return String.format("Hello from %s\nWhat can I do for you?", LOGO);
    }

    /**
     * Checks if the most recent command is an exit command.
     *
     * @return true if the most recent command is an exit command, false otherwise
     */
    public boolean hasExitCommand() {
        try {
            Command command = commandManager.getMostRecentCommand();
            return command.isExit();
        } catch (MummyException exception) {
            return false;
        }
    }
}
