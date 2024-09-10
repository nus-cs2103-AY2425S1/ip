package commands;

import exceptions.BrockException;
import storage.Storage;
import task.TaskList;

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
     * @param storage {@code Storage} object that creates and interfaces with save file.
     * @param tasks {@code TaskList} object that stores the current tasks in an {@code ArrayList}.
     * @return Response string after executing the command.
     * @throws BrockException If there are any issues with running the command.
     */
    public abstract String execute(Storage storage, TaskList tasks) throws BrockException;

}
