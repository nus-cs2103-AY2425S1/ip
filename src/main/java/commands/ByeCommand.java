package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command to exit the application.
 * This command prints a farewell message to the user when executed.
 */
public class ByeCommand extends Command {

    /**
     * Returns a boolean indicating whether this command signifies an exit command.
     *
     * @return true, as this command indicates the application should terminate.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the exit command, printing a farewell message to the console.
     * This method does not modify the task list or interact with storage or UI directly.
     *
     * @param tasks The current task list. This parameter is not used in this method.
     * @param ui The user interface component. This parameter is not used in this method.
     * @param storage The storage component. This parameter is not used in this method.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String byeMessage = "\nEl Primo:\n" +
                "Bye. Hope to see you again soon!";
        System.out.println(byeMessage);
    }
}
