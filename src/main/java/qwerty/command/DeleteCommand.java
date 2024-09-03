package qwerty.command;

import java.util.HashMap;

import qwerty.Storage;
import qwerty.TaskList;
import qwerty.task.Task;
import qwerty.ui.Ui;

/**
 * This class encapsulates a 'delete' command.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Deletes the task in the TaskList at the given index.
     * The index starts counting from 1.
     *
     * @param tasks The TaskList component handling storage of Task objects.
     * @param ui The Ui component handling user interaction.
     * @param storage The Storage component handling read/write to hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            // Get the task from the task list
            int index = Integer.parseInt(getArgs().get("main"));
            Task task = tasks.getTask(index);

            // Delete the task and print message
            tasks.deleteTask(index);
            if (task.getIsDone()) {
                ui.showQwertyMessage("\nRemoved this completed task:\n" + task
                        + "\nFinally, some progress.");
            } else {
                ui.showQwertyMessage("\nRemoved this incomplete task:\n" + task
                        + "\nYou aren't slacking off, are you?");
            }
        } catch (NumberFormatException e) {
            ui.showError("You did not give a number as the index.");
        } catch (IndexOutOfBoundsException e) {
            ui.showError("That index is out of bounds.");
        }
    }
}
