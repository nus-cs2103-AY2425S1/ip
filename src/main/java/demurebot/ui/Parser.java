package demurebot.ui;

import demurebot.DemureBotException;
import demurebot.command.*;
import demurebot.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Parser {
    /**
     * Checks if user command is valid and executes the command.
     *
     * @param command User command.
     * @throws DemureBotException If the user command is invalid.
     */
    public static Command parse(String command) throws DemureBotException {
        if (command.trim().equals("bye")) {
            return new EndCommand();
        } else if (command.trim().equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("mark")) {
            String remainder = command.substring(4).trim();
            return new MarkCommand(remainder);
        } else if (command.startsWith("unmark")) {
            String remainder = command.substring(6).trim();
            return new UnmarkCommand(remainder);
        } else if (command.startsWith("delete")) {
            String remainder = command.substring(6).trim();
            return new DeleteCommand(remainder);
        } else if (command.startsWith("todo")) {
            String description = command.substring(4).trim();
            // check that there is a task description
            if (description.isEmpty()) {
                throw new DemureBotException("The description of a todo cannot be empty.\nAdd description after todo.\n");
            }
            return new TodoCommand(description);
        } else if (command.startsWith("deadline")) {
            Deadline deadline = getDeadline(command);
            return new DeadlineCommand(deadline);
        } else if (command.startsWith("event")) {
            Event event = getEvent(command);
            return new EventCommand(event);
        } else {
            return new InvalidCommand();
        }
    }

    /**
     * Returns a Deadline created from the user command.
     *
     * @param command User command.
     * @return Deadline created from the user command.
     * @throws DemureBotException If the user command is invalid.
     */
    private static Deadline getDeadline(String command) throws DemureBotException {
        String remainder = command.substring(8).trim();
        // check that there is a task description
        if (remainder.isEmpty() || remainder.startsWith("/by")) {
            throw new DemureBotException("The description of a deadline cannot be empty.\nAdd description after deadline.\n");
        }
        String[] splitBy = remainder.split("/by");
        // check that there is a deadline
        if (splitBy.length == 1) {
            throw new DemureBotException("The deadline of a deadline cannot be empty.\nAdd deadline after /by.\n");
        }
        String description = splitBy[0].trim();
        String by = formatDateTime(splitBy[1].trim());
        return new Deadline(description, by, false);
    }

    /**
     * Returns a demure.Event created from the user command.
     *
     * @param command User command.
     * @return demure.Event created from the user command.
     * @throws DemureBotException If the user command is invalid.
     */
    private static Event getEvent(String command) throws DemureBotException {
        String remainder = command.substring(5).trim();
        // check that there is a task description
        if (remainder.isEmpty() || remainder.startsWith("/from")) {
            throw new DemureBotException("The description of an event cannot be empty.\nAdd description after event.\n");
        }
        String[] splitFrom = remainder.split("/from");
        // check that there is a start time
        if (splitFrom.length == 1) {
            throw new DemureBotException("The start time of an event cannot be empty.\nAdd start time after /from.\n");
        }
        String description = splitFrom[0].trim();
        String[] splitTo = splitFrom[1].split("/to");
        // check that there is an end time
        if (splitTo.length == 1) {
            throw new DemureBotException("The end time of an event cannot be empty.\nAdd end time after /to.\n");
        }
        String from = formatDateTime(splitTo[0].trim());
        String to = formatDateTime(splitTo[1].trim());
        return new Event(description, from, to, false);
    }

    /**
     * Returns a formatted date/time string.
     *
     * @param dateTime Date/time string to be formatted.
     * @return Formatted date/time string.
     * @throws DemureBotException If the date/time string is invalid.
     */
    private static String formatDateTime(String dateTime) throws DemureBotException {
        try {
            LocalDateTime dateTimeParsed = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            return dateTimeParsed.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm"));
        } catch (DateTimeParseException e) {
            throw new DemureBotException("Invalid date/time format. Please enter date and time in yyyy-MM-dd HHmm format.\n");
        }
    }
}
