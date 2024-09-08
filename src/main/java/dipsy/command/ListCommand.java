package dipsy.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import dipsy.exception.InvalidCommandException;
import dipsy.exception.InvalidDateException;
import dipsy.task.Task;
import dipsy.tasklist.TaskList;
import dipsy.ui.Ui;

/**
 * Represents a command to list tasks in the task list. The command can be used
 * to display all tasks or tasks filtered by a specific date.
 * The valid formats for the command are:
 * - "list": to list all tasks.
 * - "list yyyy-MM-dd": to list tasks that are relevant to the specified date.
 */
public class ListCommand extends Command {

    /**
     * Constructs a new {@code ListCommand} object.
     *
     * @param userInput The raw input from the user.
     * @param tasks     The task list containing all tasks.
     * @param ui        The user interface used to interact with the user.
     */
    public ListCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    /**
     * Executes the {@code ListCommand} by listing all tasks or filtering tasks by a specific date.
     *
     * <p>If the user input is just "list", the method displays all tasks in the task list. If the input
     * is in the form "list yyyy-MM-dd", the method filters the tasks by the specified date and lists
     * only the tasks that are due or relevant to that date.</p>
     *
     * @return A message containing the list of all tasks, or the list of tasks filtered by the specified date.
     *         If no tasks are found, a message indicating that there are no tasks is returned.
     * @throws InvalidDateException If the provided date is in an incorrect format.
     */
    public String execute() throws InvalidDateException, InvalidCommandException {
        String[] parts = userInput.trim().split("\\s+");
        if (parts.length == 1) {
            // Case where input is 'list'
            return ui.getTasksMessage(tasks.getTasks());
        } else if (parts.length == 2) {
            // Case where input is 'list <date>'
            try {
                LocalDate date = LocalDate.parse(parts[1]);
                return ui.getTasksMessage(filterTasksByDate(date));
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_LIST_COMMAND);
        }
    }

    /**
     * Filters the tasks by a specific date. This method retrieves the tasks
     * that are relevant to the given {@code date}.
     *
     * @param date The date to filter tasks by.
     * @return A list of tasks that are relevant to the specified date.
     */
    private ArrayList<Task> filterTasksByDate(LocalDate date) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task t : tasks.getTasks()) {
            if (date.equals(t.getRelevantDate())) {
                res.add(t);
            }
        }
        return res;
    }
}
