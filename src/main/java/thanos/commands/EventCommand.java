package thanos.commands;

import java.time.LocalDateTime;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.Event;
import thanos.tasks.TaskList;
import thanos.ui.Ui;
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
     * It verifies the format of the argument and converts the times to {@code LocalDateTime} objects.
     * An {@code Event} is created with these details, added to the {@code TaskList}, and a confirmation message
     * is displayed using the {@code Ui} component. If the argument is not formatted correctly or the date
     * parsing fails, an {@code InvalidCommandException} is thrown.
     * </p>
     *
     * @param taskList the list of tasks to which the event will be added.
     * @param ui the user interface component used to display the task addition confirmation to the user.
     *
     * @throws InvalidCommandException if the argument is incorrectly formatted or if the start or end time
     *                                  cannot be parsed into {@code LocalDateTime} objects.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
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

        LocalDateTime startDate = DateTimeUtility.parse(fromToArr[0]);
        LocalDateTime endDate = DateTimeUtility.parse(fromToArr[1]);
        if (startDate == null || endDate == null) {
            return;
        }
        Event event = new Event(description, startDate, endDate);
        taskList.add(event);
        ui.displayTaskAdded(event, taskList.size());
    }
}
