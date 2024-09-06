package commands;

import tasks.Task;
import tasks.TaskList;
import tasks.Todo;
import utils.Storage;
import utils.Ui;

/**
 * Command to add a To-Do task to the task list.
 */
public class AddTodoCommand implements Command {
    private final String description;

    /**
     * Constructs a new AddTodoCommand.
     *
     * @param description Description of the To-Do task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a To-Do task to the task list.
     *
     * @param tasks   TaskList to which the task is added.
     * @param ui      UI to handle user interaction.
     * @param storage Storage to save the task list.
     * @return Result message of task addition.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task todoTask = new Todo(description);
        tasks.add(todoTask);
        storage.save(tasks);
        return ui.showTaskAdded(todoTask, tasks.size());
    }
}
