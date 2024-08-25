package diego;

/**
 * Represents a command to add a to-do task to the task list.
 */
public class AddTodoCommand implements Command {
    private String description;

    /**
     * Constructs a new AddTodoCommand.
     *
     * @param description The description of the to-do task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a to-do task to the task list.
     *
     * @param tasks   The task list to which the task is added.
     * @param ui      The UI component that handles user interaction.
     * @param storage The storage component to save the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(description);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks);
    }
}