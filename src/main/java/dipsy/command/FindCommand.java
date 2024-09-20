package dipsy.command;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dipsy.exception.InvalidCommandException;
import dipsy.task.Task;
import dipsy.tasklist.TaskList;
import dipsy.ui.Ui;

/**
 * The {@code FindCommand} class handles the execution of the 'find' command,
 * which searches for tasks whose descriptions contain a specified keyword.
 * The search is case-insensitive.
 */
public class FindCommand extends Command {

    /** The regex pattern used to match the 'find' command syntax and extract the keyword. */
    private static final Pattern FIND_PATTERN = Pattern.compile("^find (.+)");

    /**
     * Constructs a {@code FindCommand} to search for tasks with a specific keyword in their description.
     *
     * @param userInput The user's input containing the 'find' command and the keyword.
     * @param tasks     The task list where tasks will be searched.
     * @param ui        The user interface to display messages and results.
     */
    public FindCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    /**
     * Executes the {@code FindCommand}. It searches the task list for tasks whose descriptions
     * contain the specified keyword.
     *
     * @return A message listing the tasks that match the keyword, or a message indicating that no tasks matched.
     * @throws InvalidCommandException if the command does not follow the expected syntax
     *                                 or if the keyword is missing.
     */
    @Override
    public String execute() throws InvalidCommandException {
        Matcher matcher = FIND_PATTERN.matcher(userInput);
        if (!matcher.matches()) {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_FIND_COMMAND);
        }

        String keyword = matcher.group(1);
        ArrayList<Task> tasksMatchingKeyword = tasks.getTasksByKeyword(keyword);

        return ui.getTasksMatchingKeywordMessage(tasksMatchingKeyword);
    }
}
