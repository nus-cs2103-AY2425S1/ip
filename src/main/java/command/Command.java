package command;

import tasklist.TaskList;
import ui.CommandLineUi;

/**
 * Represents an abstract command in the application.
 * Concrete command classes should extend this class and implement the abstract methods.
 */
public abstract class Command {
    /**
     * Executes the command with the provided task list and command line interface.
     * Subclasses should implement this method to define the specific command behavior.
     *
     * @param tasklist The TaskList that the command operates on.
     * @param ui       The CommandLineUI used to interact with the user.
     */
    public abstract void execute(TaskList tasklist, CommandLineUi ui);

    /**
     * Determines whether the command causes the application to exit.
     * Subclasses should implement this method to specify if their command signals an exit.
     *
     * @return true if the command causes the application to exit; false otherwise.
     */
    public abstract boolean isExit();
}
