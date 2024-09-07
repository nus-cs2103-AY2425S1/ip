package nixy.parse;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import nixy.Command;
import nixy.exceptions.NixyException;
import nixy.task.DeadlineTask;
import nixy.task.EventTask;
import nixy.task.Task;
import nixy.task.TodoTask;

/**
 * Parser holds the static method that parses the user input.
 */
public class Parser {
    /**
     * Parses the user input and returns a Parsed object.
     * @param input The user input to be parsed.
     * @return The Parsed object containing the command and task number or task.
     */
    public static Parsed parse(String input) {
        Task task;
        input = input.trim();
        String[] tokens = input.split(" ", 2);
        tokens[0] = tokens[0].trim();
        switch (tokens[0]) {
        case "bye":
            return new Parsed(Command.BYE);
        case "list":
            return new Parsed(Command.LIST);
        case "mark":
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The task number to mark as done cannot be empty.");
            }
            try {
                int parsedInt = Integer.parseInt(tokens[1].trim());
                if (parsedInt < 1) {
                    throw new NixyException("BLAHH!!! The task number to mark as done must be a positive integer.");
                }
                return new Parsed(Command.MARK, parsedInt);
            } catch (NumberFormatException e) {
                throw new NixyException("BLAHH!!! The task number to mark as done must be an integer.");
            }
        case "unmark":
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The task number to mark as undone cannot be empty.");
            }
            try {
                int parsedInt = Integer.parseInt(tokens[1].trim());
                if (parsedInt < 1) {
                    throw new NixyException("BLAHH!!! The task number to mark as undone must be a positive integer.");
                }
                return new Parsed(Command.UNMARK, parsedInt);
            } catch (NumberFormatException e) {
                throw new NixyException("BLAHH!!! The task number to mark as undone must be an integer.");
            }
        case "delete":
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The task number to delete cannot be empty.");
            }
            try {
                int parsedInt = Integer.parseInt(tokens[1].trim());
                if (parsedInt < 1) {
                    throw new NixyException("BLAHH!!! The task number to delete must be a positive integer.");
                }
                return new Parsed(Command.DELETE, parsedInt);
            } catch (NumberFormatException e) {
                throw new NixyException("BLAHH!!! The task number to delete must be an integer.");
            }
        case "todo":
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The task description cannot be empty.");
            }
            task = new TodoTask(tokens[1].trim());
            return new Parsed(Command.TODO, task);
        case "deadline":
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The task description cannot be empty.");
            }
            // idx 0: task name, idx 1: deadline
            String[] deadlineTokens = tokens[1].split(" /by ", 2);
            if (deadlineTokens.length < 2) {
                throw new NixyException("BLAHH!!! The deadline of a deadline task must be specified.");
            }
            try {
                task = new DeadlineTask(deadlineTokens[0].trim(),
                    LocalDate.parse(deadlineTokens[1].trim()));
            } catch (DateTimeParseException e) {
                throw new NixyException("BLAHH!!! The deadline of a deadline task must be a valid date. (e.g. 2024-12-31)");
            }
            return new Parsed(Command.DEADLINE, task);
        case "event":
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The task description cannot be empty.");
            }
            // idx 0: task name, idx 1: event times (unparsed)
            String[] eventTokens = tokens[1].split(" /from ", 2);
            if (eventTokens.length < 2) {
                throw new NixyException("BLAHH!!! The start time of an event task must be specified.");
            }
            // idx 0: start time, idx 1: end time
            String[] eventTimeTokens = eventTokens[1].split(" /to ", 2);
            if (eventTimeTokens.length < 2) {
                throw new NixyException("BLAHH!!! The end time of an event task must be specified.");
            }
            try {
                task = new EventTask(
                    eventTokens[0].trim(),
                    LocalDate.parse(eventTimeTokens[0].trim()),
                    LocalDate.parse(eventTimeTokens[1].trim())
                );
            } catch (DateTimeParseException e) {
                throw new NixyException("BLAHH!!! The start and end times of an event task must be valid dates. (e.g. 2024-12-31)");
            }
            return new Parsed(Command.EVENT, task);
        case "find":
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The keyword to search for cannot be empty.");
            }
            return new Parsed(Command.FIND, tokens[1].trim());
        default:
            throw new NixyException("BLAHH!!! I'm sorry, but I don't know what that means.");
        }
    }
}
