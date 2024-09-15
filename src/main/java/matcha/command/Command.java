package matcha.command;

import matcha.exception.MatchaException;
import matcha.storage.Storage;
import matcha.tasklist.TaskList;

/**
 * Represents a command to be executed by the user.
 */
public abstract class Command {
    private String response;
    /**
     * Sets the command's response
     *
     * @param response The response to be set
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * Returns the command's response
     *
     * @return The response of the command
     */
    public String getResponse() {
        return this.response;
    }
    /**
     * Executes the command given the task list, ui and storage objects.
     *
     * @param tasks Task list to execute command on.
     * @param storage Storage object to save tasks to file.
     * @return The response to the user.
     * @throws MatchaException If there is an error executing the command.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws MatchaException;
}
