package talkie.command;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.exception.TalkieInvalidArgumentException;
import talkie.exception.TalkieMissingArgumentException;
import talkie.task.TaskList;

/**
 * Represents a command to find tasks containing a specific keyword.
 * <p>
 * The {@code FindCommand} class processes a command that searches for tasks with descriptions matching a given keyword.
 * It requires the keyword as an argument and handles any errors related to missing or invalid arguments.
 * </p>
 */
public class FindCommand extends Command {

    private String fullCommand;

    /**
     * Constructs a {@code FindCommand} with the specified full command string.
     * <p>
     * Initializes the command with the full string provided by the user,
     * which is used to parse and execute the find operation.
     * </p>
     *
     * @param fullCommand The full command string input by the user.
     */
    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the {@code FindCommand} to search for tasks containing the specified keyword.
     * <p>
     * This method splits the command string to extract the keyword. It checks if the keyword is provided
     * and valid. If so, it uses the {@code Ui} component to display tasks that match the keyword.
     * If the keyword is missing or invalid, the method throws appropriate exceptions.
     * </p>
     *
     * @param tasks   The {@code TaskList} containing all the tasks to search within.
     * @param ui      The {@code Ui} component used to display tasks matching the keyword.
     * @param storage The {@code Storage} component (not used in this method but included for method
     *                signature consistency).
     * @return A string containing the search results, showing tasks that contain the specified keyword.
     * @throws TalkieMissingArgumentException If the keyword is not provided in the command.
     * @throws TalkieInvalidArgumentException If the argument is not a valid string.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws TalkieMissingArgumentException, TalkieInvalidArgumentException {
        String[] temp = this.fullCommand.split(" ");

        // Check if user included an argument
        if (temp.length == 1) {
            throw new TalkieMissingArgumentException(temp[0], "The 'find' command requires a string as argument");
        }

        // Checks if the argument is a string
        if (temp[1] != null) {
            String keyword = temp[1];
            return ui.findTasks(tasks, keyword);
        } else {
            throw new TalkieInvalidArgumentException(temp[0], "The 'find' command requires a string as argument");
        }
    }

    /**
     * Indicates that this command does not terminate the application.
     * <p>
     * The {@code FindCommand} does not end the program, so this method always returns {@code false}.
     * </p>
     *
     * @return {@code false}, as this command does not end the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
