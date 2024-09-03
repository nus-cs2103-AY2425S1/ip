package qwerty.command;

import java.util.HashMap;

import qwerty.Storage;
import qwerty.TaskList;
import qwerty.task.Task;
import qwerty.ui.Ui;

/**
 * This class encapsulates an 'unmark' command.
 */
public class UnmarkCommand extends Command {

    public UnmarkCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Marks the task at the given index in the TaskList as not done.
     * If the task is already marked as done, unmark it.
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

            // Mark the task as not done and print a message
            tasks.markTaskAsNotDone(index);
            ui.showQwertyMessage("\nMarked task as not done:\n" + task
                    + "\nMore work for you, boohoo.");
        } catch (NumberFormatException e) {
            ui.showError("You did not give a number as the index.");
        } catch (IndexOutOfBoundsException e) {
            ui.showError("That index is out of bounds.");
        }
    }
}
