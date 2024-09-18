package delta.command;

import delta.exception.DeltaException;
import delta.task.Task;
import delta.util.Storage;
import delta.util.TaskList;
import delta.util.Ui;

/**
 * Concrete subclass of Command abstract class.
 * Finds associated tasks from stored list using keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a FindCommand instance.
     *
     * @param keyword Keyword used to search for associated tasks in list.
     */
    public FindCommand(String keyword) {
        super(CommandType.Find);
        this.keyword = keyword;
    }

    /**
     * Returns that FindCommand is not the exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Finds associated tasks from list using a keyword.
     *
     * @param tasks List containing all current tasks.
     * @param ui User Interfacing object to print tasks found.
     * @param storage Storage object to save list.
     * @throws DeltaException If list is empty or no matching tasks found in list.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DeltaException {
        if (tasks.getSize() == 0) {
            throw new DeltaException("There are no tasks in your list.");
        }
        String output = "";
        int numTasks = 0;
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                numTasks++;
                output += String.format("\n%d. %s", numTasks, task);
            }
        }
        if (numTasks > 0) {
            String message = "Here are the matching tasks in your list:" + output;
            ui.showCommand(message);
            return message;
        } else {
            throw new DeltaException("OOPS!!! There are no matching tasks in your list!");
        }
    }
}
