package opus;

import opus.commands.AddCommand;
import opus.commands.Command;
import opus.commands.ByeCommand;
import opus.commands.ListCommand;
import opus.commands.MarkCommand;
import opus.commands.DeleteCommand;
import opus.commands.HelpCommand;
import opus.exceptions.OpusException;
import opus.exceptions.OpusEmptyDescriptionException;
import opus.exceptions.OpusMissingArgumentException;
import opus.exceptions.OpusInvalidDateFormatException;
import opus.exceptions.OpusUnknownCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Parser class is responsible for parsing user commands and returning the corresponding Command objects.
 * It handles validation and throws specific exceptions for invalid inputs.
 */
public class Parser {

    /**
     * Parses the full command entered by the user and returns the corresponding Command object.
     *
     * @param fullCommand The complete command entered by the user.
     * @return The Command object corresponding to the command.
     * @throws OpusException If the command is invalid or cannot be parsed.
     */
    public static Command parse(String fullCommand) throws OpusException {
        String[] words = fullCommand.trim().split(" ", 2);
        String action = words[0];
        String details = words.length > 1 ? words[1].trim() : "";

        switch (action) {
        case "bye":
            return new ByeCommand();

        case "list":
            return new ListCommand();

        case "mark":
            return new MarkCommand(details);

        case "delete":
            return new DeleteCommand(details);

        case "todo":
            if (details.isEmpty()) {
                throw new OpusEmptyDescriptionException("The description of a todo cannot be empty.");
            }
            return new AddCommand(action, details);

        case "deadline":
            if (details.isEmpty()) {
                throw new OpusMissingArgumentException("The description and date of a deadline cannot be empty.");
            }
            String[] deadlineParts = details.split(" /by ", 2);
            if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                throw new OpusMissingArgumentException("Please provide details for the deadline.");
            }
            if (!isValidDate(deadlineParts[1].trim())) {
                throw new OpusInvalidDateFormatException("Invalid date format for deadline. Please use yyyy-MM-dd.");
            }
            return new AddCommand(action, details);

        case "event":
            if (details.isEmpty()) {
                throw new OpusMissingArgumentException("The description and dates of an event cannot be empty.");
            }
            String[] eventParts = details.split(" /from ", 2);
            if (eventParts.length < 2 || eventParts[0].trim().isEmpty()) {
                throw new OpusMissingArgumentException("Please provide the description and /from date for the event.");
            }
            String[] timeParts = eventParts[1].split(" /to ", 2);
            if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                throw new OpusMissingArgumentException("Please provide both the dates for the event.");
            }
            if (!isValidDate(timeParts[0].trim()) || !isValidDate(timeParts[1].trim())) {
                throw new OpusInvalidDateFormatException("Invalid date format. Please use yyyy-MM-dd.");
            }
            return new AddCommand(action, details);

        case "help":
            return new HelpCommand();

        default:
            throw new OpusUnknownCommandException("I'm sorry, but I don't know what that means.");
        }
    }

    /**
     * Parses a string containing a task index and returns the index as an integer.
     *
     * @param details The string containing the task index.
     * @return The task index as an integer.
     * @throws OpusException If the index is missing or invalid.
     */
    private static int parseIndex(String details) throws OpusException {
        if (details.trim().isEmpty()) {
            throw new OpusMissingArgumentException("Please provide a task number.");
        }
        try {
            int index = Integer.parseInt(details.trim()) - 1;
            if (index < 0) {
                throw new OpusException("Task number must be a positive integer.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new OpusException("Please provide a valid task number.");
        }
    }

    /**
     * Validates if the provided date string matches the expected format yyyy-MM-dd.
     *
     * @param date The date string to validate.
     * @return True if the date is valid, false otherwise.
     */
    private static boolean isValidDate(String date) {
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(date, inputFormat);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Parses the details for a deadline task, extracting the task description and deadline.
     *
     * @param details The details part of the command for a deadline task.
     * @return A string array containing the task description and the deadline.
     * @throws OpusException If the details are invalid.
     */
    public static String[] parseDeadlineDetails(String details) throws OpusException {
        String[] deadlineParts = details.split(" /by ", 2);
        if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
            throw new OpusMissingArgumentException("Please provide both details for the deadline.");
        }
        return deadlineParts;
    }

    /**
     * Parses the details for an event task, extracting the task description, start time, and end time.
     *
     * @param details The details part of the command for an event task.
     * @return A string array containing the task description, start time, and end time.
     * @throws OpusException If the details are invalid.
     */
    public static String[] parseEventDetails(String details) throws OpusException {
        String[] eventParts = details.split(" /from ", 2);
        if (eventParts.length < 2 || eventParts[0].trim().isEmpty()) {
            throw new OpusMissingArgumentException("Please provide the description and /from date for the event.");
        }
        String[] timeParts = eventParts[1].split(" /to ", 2);
        if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new OpusMissingArgumentException("Please provide both the /from date and /to date for the event.");
        }
        return new String[] { eventParts[0].trim(), timeParts[0].trim(), timeParts[1].trim() };
    }
}
