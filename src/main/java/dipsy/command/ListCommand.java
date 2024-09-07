package dipsy.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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
     * Executes the {@code ListCommand}, either listing all tasks or filtering
     * tasks by a specific date. If the input is simply "list", it will display
     * all tasks. If the input includes a date (e.g., "list yyyy-MM-dd"), it will
     * list tasks that are due or relevant to that date.
     *
     * @throws InvalidDateException If the provided date is in an incorrect format.
     */
    public void execute() throws InvalidDateException {
        String[] parts = userInput.trim().split("\\s+");
        if (parts.length == 1) {
            // Case where input is 'list'
            ui.printListOfTasks(tasks.getTasks());
        } else if (parts.length == 2) {
            // Case where input is 'list <date>'
            try {
                LocalDate date = LocalDate.parse(parts[1]);
                ui.printListOfTasks(filterTasksByDate(date));
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
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
