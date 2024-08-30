package Gutti;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand object.
     *
     * @param commandString The input string containing the index of the task to be unmarked.
     * @throws GuttiException If the input format is invalid or the index is not a number.
     */
    public UnmarkCommand(String commandString) throws GuttiException {
        try {
            this.taskIndex = Integer.parseInt(commandString) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new GuttiException("Invalid index format for unmark command.");
        }
    }

    /**
     * Executes the UnmarkCommand by unmarking a task as not done in the task list.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The UI object to handle user interactions.
     * @param storage The Storage object to handle saving tasks to a file.
     * @throws GuttiException If there is an error during command execution, such as an invalid task index.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GuttiException {
        try {
            Task task = tasks.getTasks().get(taskIndex);
            task.unmark();
            storage.saveTasksToFile(tasks.getTasks());
            ui.showTaskList(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new GuttiException("Task index out of range.");
        }
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