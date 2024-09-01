package asura.commands;

import asura.data.exception.AsuraException;
import asura.data.tasks.Task;
import asura.data.tasks.TaskList;
import asura.storage.Storage;
import asura.ui.Ui;

/**
 * Represets the user inputting a DeleteCommand.
 */
public class DeleteCommand extends Command {
    int selection;

    /**
     * Creates a DeleteCommand with the specified selection.
     * @param selection An integer representing the index of the task that the user wants to delete.
     */
    public DeleteCommand(int selection) {
        this.selection = selection;
    }

    /**
     * Deletes the task that the user specified and outputs feedback to the user.
     * @param tasklist The list of tasks of the user.
     * @param ui The UI object to give user feedback.
     * @param storage The storage object to save/load tasks.
     * @throws AsuraException If saving user tasks fails.
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws AsuraException {
        Task removedTask = tasklist.removeAt(selection);
        output.append("Noted! I've removed this task :").append("\n").append(removedTask.toString()).append("\n")
                .append("Now you have ").append(tasklist.size()).append(" tasks in your list.\n");
        storage.save(tasklist.getTaskList());
        ui.printString(output.toString());
    }

    /**
     * Indicates that the user does not want to terminate the program.
     * @return A boolean representing whether the program is to be terminated, in this case false.
     */
    public boolean isExit() {
        return false;
    }
}
