package lawrence.command;

import lawrence.database.TaskFileManager;
import lawrence.task.TaskList;
import lawrence.ui.UserInterface;

/**
 * Represents the user command to search for tasks through the task description.
 */
public class FindTasksCommand extends Command {
    private final String input;

    /**
     * Default constructor.
     *
     * @param input the user input associated with this command
     */
    public FindTasksCommand(CommandType type, String input) {
        super(type);
        this.input = input;
    }

    /**
     * Finds tasks that match the user query and displays them. Queries that
     * partially match task descriptions are also returned.
     * <p>
     * Leading and trailing spaces are removed from the query to sanitise inputs.
     * If the query is empty or only contains spaces, the user is notified and
     * the method returns.
     * </p>
     *
     * @param tasks a list of tasks the command may operate
     *              on
     * @param manager a {@link TaskFileManager} instance that
     *                the command may use when saving changes
     *                made
     * @param ui a {@link UserInterface} instance to display
     *           possible messages to the user
     */
    @Override
    public void execute(TaskList tasks, TaskFileManager manager, UserInterface ui) {
        if (input.isEmpty()) {
            this.response = "Match query cannot be empty!";
            return;
        }

        String[] inputComponents = input.split(" ", 2);
        if (inputComponents.length < 2) {
            this.response = "Please include a phrase for your query.";
            return;
        }

        String query = inputComponents[1].trim();
        if (query.isEmpty()) {
            this.response = "Please include a phrase for your query.";
            return;
        }
        TaskList results = tasks.findTasks(query);
        if (results.getSize() < 1) {
            this.response = "No matches were found for your query: " + query;
            return;
        }

        this.response = String.format("Here are the matching tasks in your list:%n%s", results);
    }
}
