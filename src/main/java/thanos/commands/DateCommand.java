package thanos.commands;

import static thanos.utility.DateTimeUtility.format;
import static thanos.utility.ResponseFormatter.generateTaskListResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.Task;
import thanos.tasks.TaskList;
import thanos.utility.DateTimeUtility;

/**
 * Represents a command to search for tasks that match a specified date, by searching the {@code TaskList} for tasks
 * on that date.
 */
public class DateCommand extends Command {
    /**
     * Constructs a {@code DateCommand} with the given date argument.
     *
     * @param argument the date argument associated with this command. The argument should be a string representing
     *                 the date to search for tasks.
     */
    public DateCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the command to find and display tasks for the specified date.
     * <p>
     * This method parses the date from the command's argument, searches the {@code TaskList} for tasks occurring on
     * that date, and returns a formatted string with the results. If the argument is empty or the date cannot be
     * parsed, an {@code InvalidCommandException} is thrown.
     * </p>
     *
     * @param taskList the list of tasks from which the command searches for tasks that match the specified date.
     * @return a formatted string containing the tasks that occur on the specified date, or an empty string if no tasks
     *         are found or if the date cannot be parsed.
     * @throws InvalidCommandException if the argument is empty or if the date cannot be parsed.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidCommandException {
        if (this.getArgument().isEmpty()) {
            throw new InvalidCommandException(
                    "No date provided. Please use the correct format: 'date [date_to_search]'"
            );
        }

        LocalDateTime date = DateTimeUtility.parse(this.getArgument());
        if (date == null) {
            return "";
        }
        ArrayList<Task> result = taskList.findByDate(date);
        return generateTaskListResponse(result, "Here are the tasks on: " + format(date));
    }
}
