package dipsy.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dipsy.exception.InvalidCommandException;
import dipsy.task.Task;
import dipsy.tasklist.TaskList;
import dipsy.ui.Ui;

/**
 * Represents a command to mark a task as done or undone in the task list.
 * The command follows the format "mark &lt;task_number&gt;" or "unmark &lt;task_number&gt;".
 */
public class MarkCommand extends Command {

    /** Regular expression pattern to parse the mark or unmark command input. */
    private static final Pattern MARK_PATTERN = Pattern.compile("(mark|unmark) (\\d+)");

    /**
     * Constructs a MarkCommand with the specified user input, task list, and UI handler.
     *
     * @param userInput The user input that triggered this command.
     * @param tasks The task list associated with this command.
     * @param ui The UI handler for interacting with the user.
     */
    public MarkCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    /**
     * Executes the MarkCommand by parsing the user input and marking the specified task
     * as done or undone in the task list.
     * Returns a String message to be shown to the user.
     *
     * @throws InvalidCommandException If the command format is invalid or the task number is out of bounds.
     */
    @Override
    public String execute() throws InvalidCommandException {
        Matcher markMatcher = MARK_PATTERN.matcher(super.userInput);
        if (!markMatcher.matches()) {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_MARK_COMMAND);
        }
        String action = markMatcher.group(1);
        int userGivenIndex = Integer.parseInt(markMatcher.group(2));
        int index = userGivenIndex - 1; // Since tasks are 0-indexed.
        if (index >= 0 && index < tasks.getSize()) {
            if (action.equals("mark")) {
                return markTaskAsDone(index);
            } else {
                return markTaskAsUndone(index);
            }
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_MARK_INDEX);
        }
    }

    /**
     * Marks the task at the specified index as done, prints a confirmation message to the user,
     * and saves the task list to the local disk.
     *
     * @param index The index of the task to mark as done.
     */
    private String markTaskAsDone(int index) {
        Task task = tasks.getTask(index);
        task.markAsDone();
        saveTasksToLocalDisk();
        return ui.getMarkTaskDoneMessage(task);

    }

    /**
     * Marks the task at the specified index as undone, prints a confirmation message to the user,
     * and saves the task list to the local disk.
     *
     * @param index The index of the task to mark as undone.
     */
    private String markTaskAsUndone(int index) {
        Task task = tasks.getTask(index);
        task.markAsUndone();
        saveTasksToLocalDisk();
        return ui.getMarkTaskUndoneMessage(task);
    }
}
