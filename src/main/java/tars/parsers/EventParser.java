package tars.parsers;

import tars.exceptions.TarsException;

import tars.tasks.Task;
import tars.tasks.Event;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


/**
 * Parses input data to create an {@link Event} task.
 *
 * <p>The {@code EventParser} class extends the {@link Parser} class and provides
 * specific logic to parse user input and create {@link Event} tasks. It checks that
 * the input format is correct, including the event name, start date, and end date,
 * and throws custom exceptions if the input is invalid.</p>
 */
public class EventParser extends Parser {

    /**
     * Parses an array of strings containing task information and converts it into an {@link Event} task.
     *
     * @param taskInfo An array of strings representing the task details to be parsed. The first element is expected
     *                 to be the command name, and the second element should contain the task name, start date, and end date.
     * @return An {@link Event} task created from the provided task information.
     * @throws TarsException if the input is invalid, incomplete, or in the wrong format.
     */
    @Override
    public Task parse(String[] taskInfo) {
        if (taskInfo.length <= 1) {
            throw new TarsException("event? What is that even supposed to mean?\nAdd a name, start time and end time");
        }

        String[] taskDetails = taskInfo[1].split("/", 3);

        String name = taskDetails[0].trim();

        String[] startCommand = taskDetails.length > 1
                ? taskDetails[1].split(" ", 2)
                : null;

        String[] endCommand = taskDetails.length > 2
                ? taskDetails[2].split(" ", 2)
                : null;

        if (name.isEmpty()) {
            throw new TarsException("Try again. Next time tell me what your event is all about");
        }

        if (startCommand == null) {
            throw new TarsException("Add a /from command and a start date");
        }

        if (endCommand == null) {
            throw new TarsException("Add a /to command and a end date");
        }

        LocalDate[] dates = validateCommand(startCommand, endCommand);

        assert dates.length == 2;
        return new Event(name, dates[0], dates[1]);
    }

    /**
     * Validates the start and end commands to ensure they contain the correct commands
     * ("/from" and "/to") and properly formatted dates.
     *
     * @param startCommand An array of strings representing the start command. The first element is expected
     *                     to be the "/from" keyword, and the second element should be the start date in "dd-MM-yy" format.
     * @param endCommand   An array of strings representing the end command. The first element is expected
     *                     to be the "/to" keyword, and the second element should be the end date in "dd-MM-yy" format.
     * @return An array of {@link LocalDate} objects representing the validated start and end dates.
     * @throws TarsException if the commands are missing, improperly formatted, or the dates are in the wrong format.
     */
    public LocalDate[] validateCommand(String[] startCommand, String[] endCommand) {

        CommandValidator.validate(startCommand, CommandValidator.CommandType.FROM);

        CommandValidator.validate(endCommand, CommandValidator.CommandType.TO);

        LocalDate startDate;
        LocalDate endDate;

        try {
            startDate = LocalDate.parse(startCommand[1].trim(), FORMATTER);
        } catch (DateTimeParseException e) {
            throw new TarsException("Start date in wrong format. It should be in dd-MM-yy format");
        }

        try {
            endDate = LocalDate.parse(endCommand[1].trim(), FORMATTER);
        } catch (DateTimeParseException e) {
            throw new TarsException("End date in wrong format. It should be in dd-MM-yy format");
        }

        if (startDate.isAfter(endDate)) {
            throw new TarsException("Start date cannot be after End Date");
        }

        return new LocalDate[]{startDate, endDate};

    }
}
