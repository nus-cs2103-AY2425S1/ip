package Gutti;

/**
 * Represents a command to add a Todo task.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a TodoCommand object.
     *
     * @param input The input string containing the task description.
     */
    public TodoCommand(String input) {
        this.description = input.substring(0).trim();
    }

    /**
     * Executes the TodoCommand by adding a Todo task to the task list.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The UI object to handle user interactions.
     * @param storage The Storage object to handle saving tasks to a file.
     * @throws GuttiException If there is an error during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GuttiException {
        Task todoTask = new Todo(description, false);
        tasks.addTask(todoTask);  // Corrected method name
        storage.saveTasksToFile(tasks.getTasks());  // Pass tasks to save
        ui.showTaskList(tasks);  // Show updated task list
    }

    /**
     * Returns boolean on whether this command should terminate the application.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}