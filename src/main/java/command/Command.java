package command;

import exception.TaskListException;
import tasklist.TaskList;

/**
 * Handles command from users
 */
public class Command {
    /**
     * Executes the command
     * @param tasks The tasklist to store user's tasks
     * @param ui The UI to work with user
     * @throws TaskListException If wrong format that leads to exception
     */
    public String getResponse(TaskList tasks) throws TaskListException {
        return "";
    }
    /**
     * Checks if whether the interaction between bot and user end after the command
     * @return true if the conversation end after the command
     */
    public boolean isExit() {
        return false;
    }
}
