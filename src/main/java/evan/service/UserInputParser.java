package evan.service;

import static java.lang.Integer.parseInt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import evan.command.Command;
import evan.command.DeadlineCommand;
import evan.command.DeleteCommand;
import evan.command.EventCommand;
import evan.command.FindCommand;
import evan.command.ListCommand;
import evan.command.MarkCommand;
import evan.command.TodoCommand;
import evan.command.UnmarkCommand;
import evan.exception.InvalidUserInputException;

/**
 * Represents a parser that validates the user's input and converts it into commands that the chatbot can interpret and
 * execute.
 * Throws InvalidUserInputException when the user's input is invalid.
 */
public class UserInputParser {
    // Regular expression patterns to capture information from the user input
    private final Pattern keywordPattern = Pattern.compile("^(\\w+)");
    private final Pattern todoPattern = Pattern.compile("^todo\\s+(.+)$");
    private final Pattern deadlinePattern = Pattern.compile("^deadline\\s+(.+)\\s+/by\\s+(.+)$");
    private final Pattern eventPattern = Pattern.compile("^event\\s+(.+)\\s+/from\\s+(.+)\\s+/to\\s+(.+)$");
    private final Pattern markPattern = Pattern.compile("^mark\\s+(\\d+)$");
    private final Pattern unmarkPattern = Pattern.compile("^unmark\\s+(\\d+)$");
    private final Pattern deletePattern = Pattern.compile("^delete\\s+(\\d+)$");
    private final Pattern findPattern = Pattern.compile("^find\\s+(.+)$");

    /**
     * Parses the given user input and returns a Command that matches the user input.
     *
     * @return Command that the chatbot can execute.
     * @throws InvalidUserInputException If the user input is not recognised.
     */
    public Command parse(String userInput) throws InvalidUserInputException {
        Matcher keywordMatcher = keywordPattern.matcher(userInput);

        if (keywordMatcher.find()) {
            String keyword = keywordMatcher.group(1); // Extract the keyword in the user input

            switch (keyword) {
            case "list" -> {
                return new ListCommand();
            }
            case "todo" -> {
                Matcher todoMatcher = todoPattern.matcher(userInput);
                if (todoMatcher.find()) {
                    String description = todoMatcher.group(1);
                    return new TodoCommand(description);
                }
                throw new InvalidUserInputException("The 'todo' command requires <description>.");
            }
            case "deadline" -> {
                Matcher deadlineMatcher = deadlinePattern.matcher(userInput);
                if (deadlineMatcher.find()) {
                    String description = deadlineMatcher.group(1);
                    String by = deadlineMatcher.group(2);
                    return new DeadlineCommand(description, by);
                }
                throw new InvalidUserInputException("The 'deadline' command requires <description> and <when>.");
            }
            case "event" -> {
                Matcher eventMatcher = eventPattern.matcher(userInput);
                if (eventMatcher.find()) {
                    String description = eventMatcher.group(1);
                    String from = eventMatcher.group(2);
                    String to = eventMatcher.group(3);
                    return new EventCommand(description, from, to);
                }
                throw new InvalidUserInputException("The 'event' command requires <description>, <start>, and <end>.");

            }
            case "mark" -> {
                Matcher markMatcher = markPattern.matcher(userInput);
                if (markMatcher.find()) {
                    int taskNumber = parseInt(markMatcher.group(1));
                    return new MarkCommand(taskNumber);
                }
                throw new InvalidUserInputException("The 'mark' command requires <task_number>.");
            }
            case "unmark" -> {
                Matcher unmarkMatcher = unmarkPattern.matcher(userInput);
                if (unmarkMatcher.find()) {
                    int taskNumber = parseInt(unmarkMatcher.group(1));
                    return new UnmarkCommand(taskNumber);
                }
                throw new InvalidUserInputException("The 'unmark' command requires <task_number>.");
            }
            case "delete" -> {
                Matcher deleteMatcher = deletePattern.matcher(userInput);
                if (deleteMatcher.find()) {
                    int taskNumber = parseInt(deleteMatcher.group(1));
                    return new DeleteCommand(taskNumber);
                }
                throw new InvalidUserInputException("The 'delete' command requires <task_number>.");
            }
            case "find" -> {
                Matcher findMatcher = findPattern.matcher(userInput);
                if (findMatcher.find()) {
                    String description = findMatcher.group(1);
                    return new FindCommand(description);
                }
                throw new InvalidUserInputException("The 'find' command requires <description>.");
            }
            default -> throw new InvalidUserInputException("No such command exists.");
            }
        }
        throw new InvalidUserInputException("No command was provided.");
    }
}
