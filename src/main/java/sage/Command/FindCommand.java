package sage.Command;

import sage.List.TaskList;
import sage.Storage;
import sage.Task.Task;
import sage.Ui;

/**
 * Represents a command to find a task in the task list.
 */
public class FindCommand extends Command {

    private final String itemToFind;

    /**
     * Creates a new FindCommand with the item to find.
     *
     * @param itemToFind The item to search for in the task list.
     */
    public FindCommand(String itemToFind) {
        this.itemToFind = itemToFind;
    }

    /**
     * Executes the find command.
     * Searches for tasks containing the item and displays matching tasks.
     *
     * @param tasks   The TaskList containing all task.
     * @param ui      The UI object to handle user interaction.
     * @param storage The Storage object for saving changes to the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList result = new TaskList();

        for (Task task : tasks.getTasks()) {
            String sentence = task.toString();
            if (sentence.contains(itemToFind)) {
                result.addTask(task);
                break;
            }
        }

        return result.listTasks("Here are the matching tasks in your list:");
    }
}
