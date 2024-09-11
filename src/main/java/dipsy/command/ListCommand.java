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
     * Executes the command to either display the full task list or filter the task list by a specific date.
     *
     * @return A message containing either the full list of tasks or a filtered list of tasks based on the
     *         provided date.
     * @throws InvalidDateException If the provided date is invalid or incorrectly formatted.
     * @throws InvalidCommandException If the command format is invalid.
     */
    public String execute() throws InvalidDateException, InvalidCommandException {
        String[] parts = userInput.trim().split("\\s+");

        // If the input is just 'list', return all tasks
        if (parts.length == 1) {
            return ui.getTasksMessage(tasks.getTasks());
        }

        // If the input is 'list <date>', handle the date and filter tasks by the provided date
        if (parts.length == 2) {
            return handleListByDate(parts[1]);
        }

        throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_LIST_COMMAND);
    }

    /**
     * Filters the task list by a specific date and returns a message with the filtered list.
     *
     * <p>This method parses the provided date string and filters tasks that match the date.
     * If the date format is invalid, an {@code InvalidDateException} is thrown.</p>
     *
     * @param datePart The string representation of the date to filter tasks by.
     * @return A message containing the tasks that match the specified date.
     * @throws InvalidDateException If the provided date is invalid or incorrectly formatted.
     */
    private String handleListByDate(String datePart) throws InvalidDateException {
        LocalDate date;
        try {
            date = LocalDate.parse(datePart);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }

        return ui.getTasksMessage(filterTasksByDate(date));
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
