package Naega.Command;

import Naega.Storage.Storage;
import Naega.Task.TaskList;
import Naega.Ui.Ui;

import java.io.IOException;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command by saving the current task list to storage and displaying a farewell message to the user.
     *
     * @param tasks   the task list to be saved
     * @param ui      the UI component to display information to the user
     * @param storage the storage component to save the task list
     * @return a string message showing a farewell message and separator lines
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)  {
        storage.save(tasks.getTasks());  // Save tasks to storage

        return ui.showLine() + "\nBye. Hope to see you again soon!\n" + ui.showLine();  // Return the message with lines
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