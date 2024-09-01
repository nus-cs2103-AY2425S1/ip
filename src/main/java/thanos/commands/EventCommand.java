package thanos.commands;

import static thanos.utility.ResponseFormatter.generateTaskAddedResponse;

import java.time.LocalDateTime;

import thanos.exceptions.InvalidCommandException;
import thanos.exceptions.InvalidDateException;
import thanos.tasks.Event;
import thanos.tasks.TaskList;
import thanos.utility.DateTimeUtility;

/**
 * Represents a command to add an event task to the {@code TaskList}.
 */
public class EventCommand extends Command {
    /**
     * Constructs an {@code EventCommand} with the given argument.
     *
     * @param argument the argument associated with this command. The argument should be a string in the format:
     *                 'event [task] /from [start time] /to [end time]'.
     */
    public EventCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the command to create an event and add it to the {@code TaskList}.
     * <p>
     * This method parses the command argument to obtain the event description, start time, and end time.
     * It verifies the format of the argument and converts the times to {@code LocalDateTime} objects. An {@code Event}
     * is created with these details and added to the {@code TaskList}. A confirmation message indicating the addition
     * of the event and displaying the updated task count is returned. If the argument is incorrectly formatted or if
     * the date parsing fails, an {@code InvalidCommandException} is thrown.
     * </p>
     *
     * @param taskList the list of tasks to which the event will be added.
     * @return a confirmation message indicating that the event has been added and displaying the updated task count.
     * @throws InvalidCommandException if the argument is incorrectly formatted or if the start or end time
     *                                  cannot be parsed into {@code LocalDateTime} objects.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidCommandException {
        String[] detailsArr = this.getArgument().split(" /from ");
        if (detailsArr.length != 2) {
            throw new InvalidCommandException("Invalid input format."
                    + "Please use the correct format: 'event [task] /from [start time] /to [end time]'");
        }

        String description = detailsArr[0];
        String[] fromToArr = detailsArr[1].split(" /to ");
        if (fromToArr.length != 2) {
            throw new InvalidCommandException(
                    "Invalid input format. Please ensure both start and end times are provided."
            );
        }

        try {
            LocalDateTime startDate = DateTimeUtility.parse(fromToArr[0]);
            LocalDateTime endDate = DateTimeUtility.parse(fromToArr[1]);
            Event event = new Event(description, startDate, endDate);
            taskList.add(event);
            return generateTaskAddedResponse(event, taskList.size());
        } catch (InvalidDateException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}
