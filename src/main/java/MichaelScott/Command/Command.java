package MichaelScott.Command;

import MichaelScott.Exception.MichaelScottException;
import MichaelScott.Task.TaskList;

/**
 * This interface represents a Command
 */
public interface Command {

    /**
     * Executes the command on the given task list.
     *
     * @param tasks The TaskList on which to execute the command.
     * @return A String representing the result of the command execution.
     * @throws MichaelScottException If an error occurs during command execution.
     */
    String execute(TaskList tasks) throws MichaelScottException;

    /**
     * Checks if this command should cause the program to exit.
     *
     * @return true if the program should exit after this command, false otherwise.
     *         By default, this method returns false.
     */
    default boolean isExit() {
        return false;
    }
}
