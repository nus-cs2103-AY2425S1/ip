package Alex.Command;

import java.util.ArrayList;

import Alex.Exceptions.AlexException;
import Alex.Storage.Storage;
import Alex.Task.Task;
import Alex.Task.TaskList;
import Alex.Ui.Ui;

/**
 * Represents a command to find tasks based on a keyword.
 */
public class FindCommand extends CommandBase {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, searching for tasks that match the keyword.
     *
     * @param tasks   The TaskList containing tasks.
     * @param ui      The Ui object for user interaction.
     * @param storage The Storage object for saving and loading tasks.
     * @throws AlexException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        if (matchingTasks.isEmpty()) {
            ui.showTaskList(matchingTasks); // You might want to change this to show a no-match message
        } else {
            ui.showTaskList(matchingTasks);
        }
    }
}

