package matcha.command;

import matcha.storage.Storage;

import matcha.tasklist.TaskList;

import matcha.ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command{

    /**
     * Executes the command to exit the program.
     * Says goodbye to user.
     *
     * @param tasks The task list containing all tasks.
     * @param ui Ui object to interact with user.
     * @param storage Storage object to save tasks to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        setExit(true);
        ui.bye();
    }
}
