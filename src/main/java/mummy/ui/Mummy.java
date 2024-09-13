package mummy.ui;

import java.io.IOException;

import mummy.command.Command;
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

    private final Storage storage;

    private final TaskList taskList;

    private Command currentCommand;

    /**
     * Constructs a new instance of the Mummy class.
     * Initializes the storage with the given file path.
     * Loads the task list from the storage file if it exists,
     * otherwise creates a new empty task list.
     */
    public Mummy() {
        this.storage = new Storage(IO_PATH);
        TaskList taskList;
        try {
            taskList = new TaskList(this.storage.load());
        } catch (IOException e) {
            taskList = new TaskList();
        }

        this.taskList = taskList;
    }

    public String getResponse(String input) {
        try {
            Command command = Command.of(input);

            if (command.getCommandType().equals(Command.CommandType.UNDO)) {
                return undoCommand(command);
            }

            this.currentCommand = command;
            return this.currentCommand.execute(this.taskList, this.storage);
        } catch (MummyException exception) {
            return exception.getMessage();
        }
    }

    private String undoCommand(Command undoCommand) throws MummyException {
        assert (undoCommand.getCommandType().equals(Command.CommandType.UNDO));

        if (currentCommand == null) {
            throw new MummyException("No command to undo");
        }

        String message = String.format(
                "%s\n%s",
                undoCommand.execute(this.taskList, this.storage),
                this.currentCommand.undo(this.taskList, this.storage)
        );

        this.currentCommand = undoCommand;
        return message;
    }

    public Command.CommandType getCommandType() {
        if (this.currentCommand == null) {
            return Command.CommandType.UNKNOWN;
        }

        return this.currentCommand.getCommandType();
    }

    public String generateWelcomeMessage() {
        return String.format("Hello from %s\nWhat can I do for you?", LOGO);
    }

    public boolean hasExitCommand() {
        return this.currentCommand != null && this.currentCommand.isExit();
    }
}
