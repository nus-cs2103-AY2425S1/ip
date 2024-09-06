package stan.commands;

import stan.Storage;
import stan.TaskList;
import stan.exceptions.StanMissingArgumentException;
import stan.tasks.Task;
import stan.tasks.Todo;
import stan.ui.Ui;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a TodoCommand.
     *
     * @param words The user input split into words.
     * @throws StanMissingArgumentException If the description is empty.
     */
    public TodoCommand(String[] words) throws StanMissingArgumentException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new StanMissingArgumentException("The description of a todo cannot be empty.");
        }
        this.description = words[1];
    }

    /**
     * Executes the command to add a todo task to the task list.
     *
     * @param tasks The task list where the task will be added.
     * @param ui The UI object to display feedback to the user.
     * @param storage The storage object to save the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(description);
        tasks.add(task);
        storage.saveTasks(tasks.getTasks());
        return ui.showTaskAdded(task, tasks.size());
    }
}
