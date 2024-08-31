package command;

import tasklist.TaskList;

/**
 * Represents an abstract command in the application.
 * Concrete command classes should extend this class and implement the abstract methods.
 */
public abstract class Command {
    /**
     * Executes the command with the provided task list.
     *
     * @param tasklist The TaskList that the command operates on.
     * @return The response from executing the command.
     */
    public abstract String execute(TaskList tasklist);

    /**
     * Determines whether the command causes the application to exit.
     * Subclasses should implement this method to specify if their command signals an exit.
     *
     * @return true if the command causes the application to exit; false otherwise.
     */
    public abstract boolean isExit();
}
