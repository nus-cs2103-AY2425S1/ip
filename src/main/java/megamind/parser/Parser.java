package megamind.parser;

import megamind.exception.InvalidCommandException;
import megamind.exception.MissingParameterException;

public class Parser {
    /**
     * Parses the command and returns the action.
     *
     * @param command the command to be parsed
     * @return the action of the command
     */
    public String parseCommand(String command) {
        return command.split(" ")[0].toLowerCase();
    }

    /**
     * Parses the task index from the command.
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
     * Parses the description from the command.
     *
     * @param command the command to be parsed
     * @param keyword the keyword to be removed
     * @return the description of the task
     * @throws InvalidCommandException if the description is empty
     */
    public String parseDescription(String command, String keyword) throws InvalidCommandException {
        if (command.length() <= keyword.length() + 1) {
            throw new InvalidCommandException("The description cannot be empty.");
        }
        return command.substring(keyword.length() + 1);
    }

    /**
     * Parses the deadline (description, by) from the command.
     *
     * @param command the command to be parsed
     * @return the description and deadline of the task
     * @throws MissingParameterException if the deadline is missing
     * @throws InvalidCommandException   if the description or deadline is empty
     */
    public String[] parseDeadline(String command) throws MissingParameterException, InvalidCommandException {
        if (!command.contains(" /by ")) {
            throw new MissingParameterException("Please specify the deadline of the task.");
        }

        String[] words = command.split(" /by ");
        words[0] = words[0].substring(8);

        if (words[0].isEmpty()) {
            throw new InvalidCommandException("The description of a task cannot be empty.");
        }

        if (words[1].isEmpty()) {
            throw new InvalidCommandException("The deadline of a task cannot be empty.");
        }

        words[0] = words[0].trim();
        return words;
    }

    /**
     * Parses the event (description, start, end) from the command.
     *
     * @param command the command to be parsed
     * @return the description, start time and end time of the event
     * @throws MissingParameterException if the start or end time is missing
     * @throws InvalidCommandException   if the description, start or end time is empty
     */
    public String[] parseEvent(String command) throws MissingParameterException, InvalidCommandException {
        if (!command.contains(" /from ") || !command.contains(" /to ")) {
            throw new MissingParameterException("Please specify the start and end time of the event.");
        }

        String[] last = new String[3];
        String[] words = command.split(" /from ");
        last[0] = words[0].substring(6);

        words = words[1].split(" /to ");
        last[1] = words[0];
        last[2] = words[1];

        if (last[0].isEmpty()) {
            throw new InvalidCommandException("The description of a task cannot be empty.");
        }

        if (last[1].isEmpty()) {
            throw new InvalidCommandException("The start time of an event cannot be empty.");
        }

        if (last[2].isEmpty()) {
            throw new InvalidCommandException("The end time of an event cannot be empty.");
        }

        words[0] = words[0].trim();
        return last;
    }
}
