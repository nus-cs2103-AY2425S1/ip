package nave;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code Parser} class is responsible for interpreting user input and
 * converting it into appropriate commands or data for further processing.
 * It provides methods for handling different types of commands and parsing
 * various aspects of the input.
 */
public class Parser {

    /**
     * The {@code Command} enum defines the different types of commands
     * that the {@code Parser} can recognize.
     */
    public enum Command {
        LIST, HELP, MARK, UNMARK, TASK, DELETE, UNSURE, FIND
    }

    /**
     * Determines the appropriate {@code Command} based on the user input.
     * <p>
     * This method uses regular expressions to match the input against various
     * command patterns and returns the corresponding {@code Command}.
     * </p>
     *
     * @param input the user input string
     * @return the {@code Command} corresponding to the input
     */
    public Command handleInput(String input) {
        // Regex for mark/unmark commands
        Pattern markPattern = Pattern.compile("^(mark|unmark) (\\d+)$");
        Matcher markMatcher = markPattern.matcher(input);

        // Regex for delete command
        Pattern deletePattern = Pattern.compile("^delete (\\d+)$");
        Matcher deleteMatcher = deletePattern.matcher(input);

        // Regex for task commands
        Pattern taskPattern = Pattern.compile("^(todo|deadline|event)\\s?(.*)$");
        Matcher taskMatcher = taskPattern.matcher(input);

        //Regex checking for find
        Pattern findPattern = Pattern.compile("^find (.+)$");
        Matcher findMatcher = findPattern.matcher(input);

        if (input.equals("list")) {
            return Command.LIST;
        } else if (input.equals("/help")) {
            return Command.HELP;
        } else if (markMatcher.matches()) {
            String markOrNot = markMatcher.group(1);
            return markOrNot.equals("mark") ? Command.MARK : Command.UNMARK;
        } else if (taskMatcher.matches()) {
            return Command.TASK;
        } else if (deleteMatcher.matches()) {
            return Command.DELETE;
        } else if (findMatcher.matches()) {
            return Command.FIND;
        } else {
            return Command.UNSURE;
        }
    }

    /**
     * Parses the input string to extract the task index for a mark command.
     *
     * @param input the input string containing the mark command
     * @return the index of the task to be marked, or -1 if the input is invalid
     */
    public int parseMark(String input) {
        Pattern markPattern = Pattern.compile("^(mark) (\\d+)$");
        Matcher markMatcher = markPattern.matcher(input);
        return markMatcher.matches() ? Integer.parseInt(markMatcher.group(2)) : -1;
    }

    /**
     * Parses the input string to extract the task index for an unmark command.
     *
     * @param input the input string containing the unmark command
     * @return the index of the task to be unmarked, or -1 if the input is invalid
     */
    public int parseUnmark(String input) {
        Pattern markPattern = Pattern.compile("^(unmark) (\\d+)$");
        Matcher markMatcher = markPattern.matcher(input);
        return markMatcher.matches() ? Integer.parseInt(markMatcher.group(2)) : -1;
    }

    /**
     * Parses the input string to extract the task index for a delete command.
     *
     * @param input the input string containing the delete command
     * @return the index of the task to be deleted, or -1 if the input is invalid
     */
    public int parseDelete(String input) {
        Pattern deletePattern = Pattern.compile("^delete (\\d+)$");
        Matcher deleteMatcher = deletePattern.matcher(input);
        return deleteMatcher.matches() ? Integer.parseInt(deleteMatcher.group(1)) : -1;
    }

    /**
     * Parses the input string to create a {@code Task} object based on the command.
     *
     * @param input the input string containing the task command
     * @return a {@code Task} object corresponding to the input command
     * @throws WrongInputException if the input does not match the expected task patterns
     */
    public Task parseTask(String input) throws WrongInputException {
        Pattern taskPattern = Pattern.compile("^(todo|deadline|event)\\s(.*)$");
        Matcher taskMatcher = taskPattern.matcher(input);
        if (!taskMatcher.matches()) {
            throw new WrongInputException("There is an error in your command");
        }

        return switch (taskMatcher.group(1)) {
        case "todo" -> Todo.handleInput(taskMatcher.group(2));
        case "deadline" -> Deadline.handleInput(taskMatcher.group(2));
        case "event" -> Event.handleInput(taskMatcher.group(2));
        default -> throw new WrongInputException("Unknown task type");
        };
    }

    /**
     * Extracts the keyword from a "find" command string.
     * <p>
     * This method uses a regular expression to match and extract the keyword from the input string.
     * The input string is expected to start with the word "find" followed by a space and then the keyword.
     * If the input matches this pattern, the keyword is returned. If the input does not match the pattern,
     * an empty string is returned.
     * </p>
     *
     * @param input The input string that may contain a "find" command followed by a keyword.
     *              The expected format is "find [keyword]", where [keyword] is any non-empty string.
     * @return The keyword extracted from the input string if it matches the pattern; otherwise, an empty string.
     *         The returned keyword is everything after the initial "find " in the input string.
     */
    public String parseFind(String input) {
        Pattern findPattern = Pattern.compile("^find (.+)$");
        Matcher findMatcher = findPattern.matcher(input);
        return findMatcher.matches() ? findMatcher.group(1) : "";
    }
}
