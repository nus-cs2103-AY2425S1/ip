package ponderpika.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import ponderpika.Command;
import ponderpika.exception.PonderPikaException;
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

        switch (action) {
        case "list":
            return new Command(Command.Action.LIST, null);

        case "mark":
            return new Command(Command.Action.MARK, Integer.parseInt(commands[1]));

        case "unmark":
            return new Command(Command.Action.UNMARK, Integer.parseInt(commands[1]));

        case "todo":
            if (commands.length < 2) {
                throw new PonderPikaException("Missing Description for Todo task!");
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
            if (commands.length < 2) {
                throw new PonderPikaException("Missing keyword for find command!");
            }
            return new Command(Command.Action.FIND, commands[1].trim());

        case "bye":
            return new Command(Command.Action.BYE, null);

        default:
            throw new PonderPikaException("Invalid Command, Please Try Again!");
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
    private Command parseDeadlineCommand(String details) throws PonderPikaException {
        String[] args = details.split("/by ");
        if (args.length < 2) {
            throw new PonderPikaException("Missing Deadline time for Deadline task!");
        }
        if (args[0].trim().isEmpty()) {
            throw new PonderPikaException("Missing Description for Deadline task!");
        }
        return new Command(Command.Action.DEADLINE, new Deadline(args[0].trim(),
                LocalDateTime.parse(args[1].trim(), FORMATTER)));
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
    private Command parseEventCommand(String details) throws PonderPikaException {
        String[] desc = details.split("/from");
        if (desc[0].trim().isEmpty()) {
            throw new PonderPikaException("Missing Description for Event task!");
        }
        if (desc.length < 2) {
            throw new PonderPikaException("Missing \"From\" and \"To\" times for Event task!");
        }
        assert desc.length > 1;

        String[] time = desc[1].split("/to");
        if (time.length < 2) {
            throw new PonderPikaException("Missing \"To\" timeline for Event task!");
        }
        if (time[0].trim().isEmpty()) {
            throw new PonderPikaException("Missing \"From\" timeline for Event task!");
        }

        return new Command(Command.Action.EVENT, new Event(desc[0].trim(),
                LocalDateTime.parse(time[0].trim(), FORMATTER), LocalDateTime.parse(time[1].trim(), FORMATTER)));
    }
}
