package myapp.command;

import myapp.core.BingBongException;
import myapp.core.Storage;
import myapp.task.TaskList;

/**
 * Represents an abstract command that can be executed in the context of a task management application.
 * Subclasses of this class should implement the {@link #execute(TaskList, Storage)} method
 * to define specific behavior for different types of commands.
 */
public abstract class Command {

    /**
     * Constructs a Command object.
     * This constructor is typically called by subclasses.
     */
    public Command() {}

    /**
     * Executes the command, performing the associated action on the task list.
     *
     * @param tasks The task list on which the command will operate.
     * @param storage The storage system used to save the task list.
     * @return A string message indicating the result of the command execution.
     * @throws BingBongException If an error occurs during the execution of the command.
     * @throws IndexOutOfBoundsException If an invalid task index is accessed during execution.
     */
    public abstract String execute(TaskList tasks, Storage storage)
            throws BingBongException, IndexOutOfBoundsException;

    /**
     * Determines whether the command causes the application to exit.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();

    /**
     * Saves the current state of the task list to storage.
     *
     * @param tasks The task list to be saved.
     * @param storage The storage system to which the task list will be saved.
     */
    public void saveTasks(TaskList tasks, Storage storage) {
        try {
            storage.save(tasks);
        } catch (BingBongException e) {
            System.out.println("Unable to save tasks in hard disk");
        }
    }
}
