package command;

import exception.TaskListException;
import tasklist.TaskList;

/**
 * Handles command from users
 */
public interface Command {
    /**
     * Executes the command
     * @param tasks The tasklist to store user's tasks
     * @throws TaskListException If wrong format that leads to exception
     */
    public abstract String getResponse(TaskList tasks) throws TaskListException;
}
