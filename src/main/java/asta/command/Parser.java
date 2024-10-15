package asta.command;

import asta.AstaException;

/**
 * The Parser class is responsible for interpreting user input and converting it into specific commands that the
 * application can execute. It serves as a bridge between raw user input and command execution.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command. The method checks the input against known commands
     * and returns a Command enum representing the type of command to execute.
     *
     * @param input The raw input string from the user.
     * @return A Command enum representing the type of command parsed from the input.
     */
    public static Command parse(String input) {
        String lowerCaseInput = input.toLowerCase();
        String[] words = lowerCaseInput.split(" ", 2); //Split by the first space

        // CHECKSTYLE.OFF: Indentation
        return switch (words[0]) {
            case "bye" -> Command.BYE;
            case "list" -> Command.LIST;
            case "mark" -> Command.MARK;
            case "unmark" -> Command.UNMARK;
            case "todo" -> Command.TODO;
            case "deadline" -> Command.DEADLINE;
            case "event" -> Command.EVENT;
            case "delete" -> Command.DELETE;
            case "find" -> Command.FIND;
            case "repeat" -> parseRecurringCommand(words[1]);
            default -> Command.UNKNOWN;
        };
        // CHECKSTYLE.ON: Indentation
    }

    /**
     * Parses recurring task commands based on the second part of the input.
     *
     * @param input The second part of the user input after "repeat".
     * @return A Command enum representing the recurring task command.
     */
    private static Command parseRecurringCommand(String input) {
        String[] recurringWords = input.split(" ", 2);
        return switch (recurringWords[0]) {
            // CHECKSTYLE.OFF: Indentation
            case "deadline" -> Command.RECURRING_DEADLINE;
            default -> Command.UNKNOWN;
            // CHECKSTYLE.ON: Indentation
        };
    }

    /**
     * Parses a recurrence type (e.g., daily, weekly) and returns the interval in days.
     *
     * @param recurrenceType The recurrence type (e.g., daily, weekly).
     * @return The interval in days for the recurrence.
     * @throws AstaException if the recurrence type is invalid.
     */
    public static int parseRecurrenceInterval(String recurrenceType) throws AstaException {
        return switch (recurrenceType.toLowerCase()) {
            // CHECKSTYLE.OFF: Indentation
            case "daily" -> 1;
            case "weekly" -> 7;
            case "monthly" -> 30;
            default -> throw new AstaException("Invalid recurrence type. Supported types: daily, weekly, monthly.");
            // CHECKSTYLE.ON: Indentation
        };
    }
}
