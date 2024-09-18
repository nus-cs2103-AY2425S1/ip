package skibidi.command;

import java.io.IOException;

import skibidi.exceptions.SkibidiException;
import skibidi.utils.Storage;
import skibidi.utils.TaskList;


/**
 * Represents a command.
 */
public abstract class Command {

    public Command(String command) {
    }

    /**
     * Executes the command.
     *
     * @param storage Storage object to save and load tasks
     * @param taskList TaskList object to store and manipulate tasks
     * @throws IOException If an error occurs when saving or loading tasks
     * @throws SkibidiException If an error occurs in Skibidi
     */
    public abstract String execute(Storage storage, TaskList taskList) throws IOException, SkibidiException;

    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}
