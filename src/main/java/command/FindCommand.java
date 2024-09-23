package command;

import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * Represents a command to find and display tasks containing a specific keyword.
 */
public class FindCommand extends Command {

    private final String input;

    /**
     * Constructs a FindCommand with the specified input string.
     *
     * @param input The input string containing the keyword to search for.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the FindCommand by extracting the keyword from the input string,
     * finding tasks that contain the keyword, and displaying the results to the user.
     *
     * @param tasks The TaskList to search for tasks.
     * @param storage The Storage object, which is not used in this method.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String[] parts = this.input.split(" ", 2);
        if (parts.length < 2) {
            return "OOPS!!! Please provide a keyword for the search.";
        }
        String keyword = parts[1];
        return Ui.showKeywordTasks(tasks, keyword);
    }
}
