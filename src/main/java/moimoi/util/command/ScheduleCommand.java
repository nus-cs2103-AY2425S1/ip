package moimoi.util.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import moimoi.util.Storage;
import moimoi.util.TaskList;
import moimoi.util.exception.InvalidDateTimeException;
import moimoi.util.task.Task;

/**
 * Represents a command to filter the task list, by a specific occurring date.
 */
public class ScheduleCommand extends Command {

    private String dateString;

    /**
     * Constructs a command to filter the task list, by the specified occurring date.
     *
     * @param dateString Date to be checked against tasks' scheduled dates.
     */
    public ScheduleCommand(String dateString) {
        super(false);
        this.dateString = dateString;
    }

    /**
     * Executes task filtering, by occurring date.
     *
     * @param storage MoiMoi's storage.
     * @param tasks List of existing tasks.
     * @return Completion message.
     * @throws InvalidDateTimeException If the specified date is invalid.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) throws InvalidDateTimeException {

        try {
            LocalDate date = LocalDate.parse(this.dateString);
            StringBuilder completionMessage = new StringBuilder("Here's your list of tasks, occurring on "
                    + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "!");
            boolean isEmpty = true;

            for (int i = 1; i <= tasks.getSize(); i = i + 1) {
                Task task = tasks.get(i);
                if (task.occursOn(date)) {
                    isEmpty = false;
                    completionMessage.append("\n").append(i).append(". ").append(task.stringUI());
                }
            }

            if (isEmpty) {
                completionMessage.append("\n(No tasks found!!!)");
            }

            return completionMessage.toString();
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("date");
        }

    }

}
