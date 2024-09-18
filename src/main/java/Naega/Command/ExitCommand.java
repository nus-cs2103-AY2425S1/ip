package Naega.Command;

import Naega.Storage.Storage;
import Naega.Task.TaskList;
import Naega.Ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command by saving the current task list to storage and displaying a farewell message to the user.
     *
     * @param tasks the task list to be saved
     * @param ui the UI component to display information to the user
     * @param storage the storage component to save the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTasks());
        ui.showLine();
        System.out.println(" Bye. Hope to see you again soon!");
        ui.showLine();
    }

    /**
     * Checks if the command signifies an exit action.
     *
     * @return true since this command is an exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}