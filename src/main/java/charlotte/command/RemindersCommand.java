package charlotte.command;

import java.time.LocalDate;

import charlotte.exception.CharlotteException;
import charlotte.storage.Storage;
import charlotte.task.Deadline;
import charlotte.task.Event;
import charlotte.task.Task;
import charlotte.task.TaskList;
import charlotte.ui.Ui;

/**
 * Represents a command to display reminders for tasks with deadlines or events starting within one week.
 */
public class RemindersCommand extends Command {

    /**
     * Executes the command to display tasks with deadlines or events starting within one week.
     *
     * @param tasks The TaskList object containing all the tasks.
     * @param ui The Ui object used to display messages to the user.
     * @param storage The Storage object, which is not used in this command.
     * @return A string representing tasks with upcoming deadlines or events starting within one week.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CharlotteException {
        StringBuilder result = new StringBuilder();
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusWeeks(1);

        boolean hasReminders = tasks.getTasks().stream()
                .filter(task -> isWithinOneWeek(task, today, nextWeek))
                .peek(task -> {
                    if (result.length() == 0) {
                        result.append("\nHere are your tasks with upcoming deadlines "
                                + "or events starting within one week:\n");
                    }
                    result.append(task).append("\n");
                })
                .count() > 0;

        if (!hasReminders) {
            result.append("\nYou have no upcoming deadlines or events within one week! \n");
        }

        return result.toString();
    }

    /**
     * Checks if the task has a deadline or event start date within one week from today.
     *
     * @param task The task to check.
     * @param today The current date.
     * @param nextWeek The date one week from today.
     * @return True if the task is within one week, false otherwise.
     */
    private boolean isWithinOneWeek(Task task, LocalDate today, LocalDate nextWeek) {
        if (task instanceof Deadline) { //check if task is a Deadline and within one week
            Deadline deadline = (Deadline) task;
            LocalDate dueDate = deadline.getBy();
            return isDateWithinRange(dueDate, today, nextWeek);
        } else if (task instanceof Event) { //check if task is an Event and starts within one week
            Event event = (Event) task;
            LocalDate startDate = event.getFrom();
            return isDateWithinRange(startDate, today, nextWeek);
        }
        return false;
    }

    /**
     * Helper method to check if a given date is within a specified date range.
     *
     * @param date The date to check.
     * @param start The start date of the range.
     * @param end The end date of the range.
     * @return True if the date is within the range, false otherwise.
     */
    private boolean isDateWithinRange(LocalDate date, LocalDate start, LocalDate end) {
        return !date.isBefore(start) && !date.isAfter(end);
    }
}
