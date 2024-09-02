package taskon.commands;

import taskon.storage.Storage;
import taskon.task.Task;
import taskon.task.TaskList;
import taskon.ui.Ui;

/**
 * Represents a command to find tasks based on a description keyword.
 * This command searches through the task list and filters tasks
 * that contain the given description.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private String description;

    /**
     * Constructs a FindCommand with the specified description to search for.
     *
     * @param description The description keyword to search for in the task list.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the FindCommand which searches the task list for tasks
     * containing the specified description and displays the results.
     *
     * @param taskList The list of tasks to search through.
     * @param ui       The UI instance to handle user interactions.
     * @param storage  The storage instance for managing task data (not used in this method).
     * @return A string message that shows the list of tasks with the specified description.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList list = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(this.description)) {
                list.addTask(task);
            }
        }
        if (list.size() == 0) {
            return ui.showEmptyFind();
        } else {
            return ui.listItems(list);
        }
    }
}
