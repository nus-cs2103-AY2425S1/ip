package thanos.commands;

import static thanos.utility.DateTimeUtility.format;
import static thanos.utility.ResponseFormatter.generateTaskListResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;

import thanos.exceptions.InvalidCommandException;
import thanos.exceptions.InvalidDateException;
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
     * @param argument the date argument associated with this command.
     */
    public DateCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the command to find and display tasks for the specified date.
     *
     * @param taskList the list of tasks from which the command searches for tasks that match the specified date.
     * @return a formatted string containing the tasks that occur on the specified date, or an empty string if no tasks
     *         are found or if the date cannot be parsed.
     * @throws InvalidCommandException if the argument is empty or if the date cannot be parsed.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidCommandException {
        if (this.getArgument().isEmpty()) {
            throw new InvalidCommandException("No date provided.");
        }

        try {
            LocalDateTime date = DateTimeUtility.parse(this.getArgument());
            ArrayList<Task> result = taskList.findByDate(date);
            return generateTaskListResponse("Here are the tasks on: " + format(date), result.toArray(new Task[0]));
        } catch (InvalidDateException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}
