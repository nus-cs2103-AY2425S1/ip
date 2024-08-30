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
     * Initializes the command with the full string provided by the user, which is used to parse and execute the find operation.
     * </p>
     *
     * @param fullCommand The full command string input by the user.
     */
    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the find command to search for tasks containing the specified keyword.
     * <p>
     * Splits the command string to extract the keyword and checks if it is provided correctly. If valid, it uses the
     * {@code Ui} component to display tasks matching the keyword. Throws exceptions for missing or invalid arguments.
     * </p>
     *
     * @param tasks The {@code TaskList} containing all the tasks to search within.
     * @param ui The {@code Ui} component used to display search results.
     * @param storage The {@code Storage} component (not used in this method but included for method signature consistency).
     * @throws TalkieMissingArgumentException If the keyword is not provided.
     * @throws TalkieInvalidArgumentException If the argument is not a valid string.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws TalkieMissingArgumentException, TalkieInvalidArgumentException {
        String[] temp = this.fullCommand.split(" ");

        // Check if user included an argument
        if (temp.length == 1) {
            throw new TalkieMissingArgumentException(temp[0], "The 'find' command requires a string as argument");
        }

        // Checks if the argument is a string
        if (temp[1] != null) {
            String keyword = temp[1];
            ui.findTasks(tasks, keyword);
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
