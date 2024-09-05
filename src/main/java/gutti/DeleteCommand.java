package gutti;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param input The input string containing the index of the task to be deleted.
     * @throws GuttiException If the input format is invalid or the index is not a number.
     */
    public DeleteCommand(String input) throws GuttiException {
        try {
            this.index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new GuttiException("Invalid format. Use: delete <task index>");
        }
    }

    /**
     * Executes the DeleteCommand by removing a task from the task list.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The UI object to handle user interactions.
     * @param storage The Storage object to handle saving tasks to a file.
     * @throws GuttiException If there is an error during command execution, such as an invalid task index.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GuttiException {
        try {
            // Remove the task and update storage
            Task removedTask = tasks.getTasks().remove(index);
            storage.saveTasksToFile(tasks.getTasks()); // Save updated task list

            // Prepare the response string
            StringBuilder response = new StringBuilder();
            response.append("Meow. I've removed this task:\n");
            response.append(removedTask).append("\n");
            response.append("Now you have ").append(tasks.getTasks().size()).append(" tasks in the list.");

            return response.toString(); // Return the response string

        } catch (IndexOutOfBoundsException e) {
            throw new GuttiException("No such task to delete!");
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
