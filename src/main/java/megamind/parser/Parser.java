package megamind.parser;

import megamind.exception.InvalidCommandException;
import megamind.exception.MissingParameterException;

/**
 * The `Parser` class is responsible for parsing user commands.
 * It provides methods to extract the action, task index, description, deadline, and event details from the commands.
 */
public class Parser {

    /**
     * Parses the command and returns the action
     *
     * @param command the command to be parsed
     * @return the action of the command
     */
    public String parseCommand(String command) {
        return command.split(" ")[0].toLowerCase();
    }

    /**
     * Parses the task index from the command
     *
     * @param command the command to be parsed
     * @return the index of the task
     * @throws InvalidCommandException if the task number is missing or invalid
     */
    public int parseTaskIndex(String command) throws InvalidCommandException {
        String[] words = command.split(" ");
        if (words.length < 2) {
            throw new InvalidCommandException("Task number is missing, please include it.");
        }

        int index;
        try {
            index = Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task number format.");
        }
        return index;
    }

    /**
     * Parses the description of the task from the command
     *
     * @param command The command to be parsed
     * @param keyword The keyword to be removed from the command
     * @return The description of the task
     * @throws InvalidCommandException If the description is missing
     */
    public String parseDescription(String command, String keyword) throws InvalidCommandException {
        if (command.length() <= keyword.length() + 1) {
            throw new InvalidCommandException("The description cannot be empty.");
        }
        return command.substring(keyword.length() + 1);
    }

    /**
     * Parses the date and time of the task from the command
     *
     * @param command The command to be parsed
     * @return the description and deadline of the task
     * @throws MissingParameterException if command does not contain keyword "/by"
     * @throws InvalidCommandException if the description or deadline is empty
     */
    public String[] parseDeadline(String command) throws MissingParameterException, InvalidCommandException {
        if (!command.contains(" /by ")) {
            throw new MissingParameterException("Please specify the deadline of the task.");
        }

        // Trims the start of the command "deadline "
        command = command.substring(9);

        String[] deadline = command.split(" /by ");

        if (deadline[0].isEmpty()) {
            throw new InvalidCommandException("The description of a task cannot be empty.");
        }

        if (deadline[1].isEmpty()) {
            throw new InvalidCommandException("The deadline of a task cannot be empty.");
        }

        return deadline;
    }

    /**
     * Parses the description, date, and time of the event from the command.
     *
     * @param command The command to be parsed
     * @return The description, date, and time of the event
     * @throws MissingParameterException If the keywords "/from" or "/to" are missing
     * @throws InvalidCommandException If the description, date, and time is missing.
     */
    public String[] parseEvent(String command) throws MissingParameterException, InvalidCommandException {
        if (!command.contains(" /from ") || !command.contains(" /to ")) {
            throw new MissingParameterException("Please specify the start and end time of the event.");
        }

        // Trims the start of the command "event "
        command = command.substring(6);
        String[] event = command.split(" /from | /to ");

        if (event[0].isEmpty()) {
            throw new InvalidCommandException("The description of a task cannot be empty.");
        }

        if (event[1].isEmpty()) {
            throw new InvalidCommandException("The start time of an event cannot be empty.");
        }

        if (event[2].isEmpty()) {
            throw new InvalidCommandException("The end time of an event cannot be empty.");
        }

        return event;
    }
}
