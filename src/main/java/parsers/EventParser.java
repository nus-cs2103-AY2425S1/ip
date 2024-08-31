package parsers;

import tasks.Event;
import tasks.Task;

import exceptions.TarsException;

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

        String[] split = taskInfo[1].split("/", 3);

        String name = split[0].trim();

        String[] startCommand = split.length > 1
                ? split[1].split(" ", 2)
                : null;

        String[] endCommand = split.length > 2
                ? split[2].split(" ", 2)
                : null;

        if (name.isEmpty()) {
            throw new TarsException("Try again. Next time tell me what your event is all about");
        }

        LocalDate[] dates = validateCommand(startCommand, endCommand);

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
        if (startCommand == null) {
            throw new TarsException("Add a /from command and a start date");
        } else {
            switch (startCommand.length) {
                case 1:
                    if (startCommand[0].equals("from")) {
                        throw new TarsException("Add a event start date");
                    } else {
                        throw new TarsException("Add the /from command");
                    }

                case 2:
                    if (startCommand[0].equals("from")) {
                        if (startCommand[1].isEmpty()) {
                            throw new TarsException("Add an event start date");
                        }
                    } else {
                        throw new TarsException("Add the /from command");
                    }
            }
        }

        if (endCommand == null) {
            throw new TarsException("Add a /to command and a end date");
        } else {
            switch (endCommand.length) {
                case 1:
                    if (endCommand[0].equals("to")) {
                        throw new TarsException("Add an event end date");
                    } else {
                        throw new TarsException("Add the /to command");
                    }

                case 2:
                    if (endCommand[0].equals("to")) {
                        if (endCommand[1].isEmpty()) {
                            throw new TarsException("Add an event end date");
                        }
                    } else {
                        throw new TarsException("Add the /to command");
                    }
            }
        }

        LocalDate startDate, endDate;


        try {
            startDate = LocalDate.parse(startCommand[1].trim(), Parser.FORMATTER);

        } catch (DateTimeParseException e) {

            throw new TarsException("Start date in wrong format. It should be in dd-mm-yy format");

        }

        try {
            endDate = LocalDate.parse(endCommand[1].trim(), Parser.FORMATTER);

        } catch (DateTimeParseException e) {
            throw new TarsException("End date in wrong format. It should be in dd-mm-yy format");

        }

        return new LocalDate[]{startDate, endDate};

    }
}
