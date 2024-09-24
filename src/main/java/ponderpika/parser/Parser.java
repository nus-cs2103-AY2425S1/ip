package ponderpika.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import ponderpika.Command;
import ponderpika.exception.InvalidDateRangeException;
import ponderpika.exception.InvalidDateTimeException;
import ponderpika.exception.InvalidDescriptionException;
import ponderpika.exception.MissingDeadlineTimeException;
import ponderpika.exception.MissingDescriptionException;
import ponderpika.exception.MissingEventFromTimeException;
import ponderpika.exception.MissingEventTimingException;
import ponderpika.exception.MissingEventToTimeException;
import ponderpika.exception.MissingFindKeywordException;
import ponderpika.exception.MissingPriorityLevelException;
import ponderpika.exception.MissingTaskIndexException;
import ponderpika.exception.PonderPikaException;
import ponderpika.exception.UnknownCommandException;
import ponderpika.task.Deadline;
import ponderpika.task.Event;

/**
 * Parses user input commands and converts them into Command objects.
 * <p>
 * This class is responsible for interpreting the user's command input, extracting the relevant
 * details, and creating Command objects that represent the action to be performed.
 * </p>
 */
public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");

    /**
     * Parses the user command and returns a Command object.
     *
     * @param command The user input command string.
     * @return A Command object representing the parsed command.
     * @throws PonderPikaException If there is an error in parsing the command.
     */
    public Command parseCommand(String command) throws PonderPikaException {
        String[] commands = command.split(" ", 2);
        String action = commands[0].toLowerCase();
        boolean isWithinSizeLimit = (commands.length < 2);

        switch (action) {
        case "list":
            return new Command(Command.Action.LIST, null);

        case "mark":
            if (commands[1].isEmpty()) {
                throw new MissingTaskIndexException();
            }
            return new Command(Command.Action.MARK, Integer.parseInt(commands[1]));

        case "unmark":
            if (commands[1].isEmpty()) {
                throw new MissingTaskIndexException();
            }
            return new Command(Command.Action.UNMARK, Integer.parseInt(commands[1]));

        case "todo":
            if (isWithinSizeLimit) {
                throw new MissingDescriptionException();
            }
            assert !Objects.equals(commands[1], "");
            return new Command(Command.Action.TODO, commands[1]);

        case "deadline":
            return parseDeadlineCommand(commands[1]);

        case "event":
            return parseEventCommand(commands[1]);

        case "delete":
            return new Command(Command.Action.DELETE, Integer.parseInt(commands[1]));

        case "find":
            if (isWithinSizeLimit) {
                throw new MissingFindKeywordException();
            }
            return new Command(Command.Action.FIND, commands[1].trim());

        case "priority":
            return parsePriorityCommand(commands[1]);

        case "bye":
            return new Command(Command.Action.BYE, null);
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses a string input into a LocalDateTime object.
     * <p>
     * This method attempts to convert the provided string into a LocalDateTime using
     * the specified formatter. If the input string is not in a valid format, an
     * InvalidDateTimeException is thrown
     * </p>
     *
     * @param input the string representation of the date and time to be parsed
     * @return a LocalDateTime object representing the parsed date and time
     * @throws InvalidDateTimeException if the input string cannot be parsed into a valid date and time
     */
    public LocalDateTime parseDateTime(String input) throws InvalidDateTimeException {
        try {
            return LocalDateTime.parse(input, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Parses a deadline command string and returns a Command object for a deadline task.
     * <p>
     * The method splits the details string into description and deadline time. It then constructs a
     * Command object with a Deadline task. If any part of the expected input is missing,
     * a PonderPikaException is thrown.
     * </p>
     *
     * @param details The details of the deadline command, including the description and deadline time.
     * @return A Command object representing the parsed deadline command.
     *
     * @throws PonderPikaException If the details are incomplete or incorrectly formatted.
     */
    private Command parseDeadlineCommand(String details) throws MissingDeadlineTimeException,
            MissingDescriptionException, InvalidDateTimeException, InvalidDescriptionException {
        String[] args = details.split("/by ");
        if (args.length < 2) {
            throw new MissingDeadlineTimeException();
        }
        if (args[0].trim().isEmpty()) {
            throw new MissingDescriptionException();
        }
        if (!args[0].matches("[a-zA-Z ]+")) {
            throw new InvalidDescriptionException();
        }
        LocalDateTime byTime = parseDateTime(args[1].trim());
        return new Command(Command.Action.DEADLINE, new Deadline(args[0].trim(), byTime));
    }

    /**
     * Parses an event command string and returns a Command object for an event task.
     * <p>
     * The method splits the details string into description and event times. It then constructs a
     * Command object with an Event task. If any part of the expected input is missing,
     * a PonderPikaException is thrown.
     * </p>
     *
     * @param details The details of the event command, including the description, start time, and end time.
     * @return A Command object representing the parsed event command.
     *
     * @throws PonderPikaException If the details are incomplete or incorrectly formatted.
     */
    private Command parseEventCommand(String details) throws MissingDescriptionException,
            MissingEventTimingException, MissingEventFromTimeException, MissingEventToTimeException,
            InvalidDateTimeException, InvalidDateRangeException, InvalidDescriptionException {
        String[] desc = details.split("/from");
        if (desc[0].trim().isEmpty()) {
            throw new MissingDescriptionException();
        }
        if (!desc[0].matches("[a-zA-Z ]+")) {
            throw new InvalidDescriptionException();
        }
        if (desc.length < 2) {
            throw new MissingEventTimingException();
        }
        assert desc.length > 1;

        String[] time = desc[1].split("/to");
        if (time.length < 2) {
            throw new MissingEventToTimeException();
        }
        if (time[0].trim().isEmpty()) {
            throw new MissingEventFromTimeException();
        }

        LocalDateTime fromTime = parseDateTime(time[0].trim());
        LocalDateTime toTime = parseDateTime(time[1].trim());

        if (toTime.isBefore(fromTime) || toTime.isEqual(fromTime)) {
            throw new InvalidDateRangeException();
        }

        return new Command(Command.Action.EVENT, new Event(desc[0].trim(), fromTime, toTime));
    }

    /**
     * parses through the priority setting command
     *
     * @param details task and index required for setting priority
     * @return Command instance with action as Priority and data as detail
     * @throws PonderPikaException for missing priority and missing index
     */
    public Command parsePriorityCommand(String details) throws MissingPriorityLevelException,
            MissingTaskIndexException {
        String[] taskDetail = details.split(" ");
        if (taskDetail[0].trim().isEmpty()) {
            throw new MissingPriorityLevelException();
        }
        if (taskDetail.length < 2) {
            throw new MissingTaskIndexException();
        }

        return new Command(Command.Action.PRIORITY, taskDetail);
    }
}
