package Commands;

import Exceptions.BrockException;
import Tasks.TaskList;
import Storage.Storage;
import Ui.Ui;


/**
 * Abstract class that defines a template for user commands.
 */
public abstract class Command {
    private final String command;

    /**
     * Stores the command string associated with user commands.
     *
     * @param command Command string.
     */
    protected Command(String command) {
        this.command = command;
    }

    /**
     * Fetches the stored command string.
     * To be examined within the user command subclass.
     *
     * @return Command string.
     */
    protected String getCommand() {
        return this.command;
    }


    /**
     * Runs the user command.
     *
     * @param ui {@code UI} object that displays response to command.
     * @param storage {@code Storage} object that creates and interfaces with save file.
     * @param tasks {@code TaskList} object that stores the current tasks in an {@code ArrayList}.
     * @throws BrockException If there are any issues with running the command.
     */
    abstract public void execute(Ui ui, Storage storage, TaskList tasks) throws BrockException;


    /**
     * Checks the exit status associated with the user command.
     *
     * @return Exit status, either true or false.
     */
    abstract public boolean isExit();
}
