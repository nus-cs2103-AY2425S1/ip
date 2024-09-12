package denim.commands;

import denim.TaskList;
import denim.storage.WriteTaskFile;

/**
 * Represents an abstract command that can be executed.
 * This class is the base for all specific command types.
 * Each command performs an action on a task list, potentially involving I/O operations.
 */
public abstract class Command {

    /**
     * Executes the command on the given task list and with the provided I/O operations.
     *
     * @param taskList The task list to perform the command on.
     * @param writeTaskFile The I/O operations for reading from and writing to storage.
     * @return The result of the command execution.
     */
    public abstract CommandResult execute(TaskList taskList, WriteTaskFile writeTaskFile);

    /**
     * Determines whether this command should exit the application.
     *
     * @return True if the command should exit the application, false otherwise.
     */
    public abstract boolean isExit();
}
