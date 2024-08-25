package megamind.parser;

import megamind.exception.InvalidCommandException;
import megamind.exception.MissingParameterException;

public class Parser {
    public String parseCommand(String command) {
        return command.split(" ")[0].toLowerCase();
    }

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

    public String parseDescription(String command, String keyword) throws InvalidCommandException {
        if (command.length() <= keyword.length() + 1) {
            throw new InvalidCommandException("The description cannot be empty.");
        }
        return command.substring(keyword.length() + 1);
    }

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
