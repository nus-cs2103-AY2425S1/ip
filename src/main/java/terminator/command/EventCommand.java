package terminator.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import terminator.task.EventTask;
import terminator.task.Task;

/**
 * Concrete class representing a command to create a EventTask.
 */
public class EventCommand extends Command {

    private static final String ERR_MSG = """
            Invalid input format.\n
            Usage: event <description> /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm""";

    public EventCommand(String input) {
        super(input);
    }

    /**
     * Creates a new EventTask with the given input and adds it to the task list.
     *
     * @param todoList The task list.
     * @throws TerminatorException if the description of the Event task is empty.
     */
    @Override
    public String execute(ArrayList<Task> todoList) throws TerminatorException {
        if (input == null) {
            throw new TerminatorException(ERR_MSG);
        }

        // Extract description string and indices of '/from' and '/to'
        int fromDateIdx = input.indexOf("/from");
        String description = input.substring(0, fromDateIdx).trim();
        if (description.isEmpty()) {
            throw new TerminatorException(ERR_MSG);
        }
        int toDateIdx = input.indexOf("/to");

        // Extract the date time strings
        String fromDateString = input.substring(fromDateIdx + 6, toDateIdx).trim();
        String toDateString = input.substring(toDateIdx + 4).trim();

        // Create LocalDateTime objects
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        try {
            LocalDateTime fromDate = LocalDateTime.parse(fromDateString, dateTimeFormatter);
            LocalDateTime toDate = LocalDateTime.parse(toDateString, dateTimeFormatter);

            // If end date is before start date, return error response
            if (fromDate.compareTo(toDate) == 1) {
                return "Error: end date cannot be before start date.";
            }

            // Add to TaskList
            Task t = new EventTask(description, fromDate, toDate);
            todoList.add(t);

            return "Mission parameters updated. Added new objective:\n\n" + t;
        } catch (DateTimeParseException e) {
            throw new TerminatorException(
                    """
                    Error: invalid date time input.\n
                    Months should be between 1-12, and days should be between 1-31.
                    The hour should be between 00 to 23, and the minute should be between 00 and 59.""");
        }
    }
}
