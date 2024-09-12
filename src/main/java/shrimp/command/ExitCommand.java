package shrimp.command;

import shrimp.task.TaskList;
import shrimp.utility.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command by printing a farewell message to the user interface.
     *
     * @param taskList The list of tasks (not used in this command).
     * @param ui       The user interface to print the farewell message.
     */
    @Override
    public String run(TaskList taskList, Ui ui) {
        return ui.printExit();
    }
}
