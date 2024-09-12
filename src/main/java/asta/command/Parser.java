package asta.command;

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
            default -> Command.UNKNOWN;
        };
        // CHECKSTYLE.ON: Indentation
    }
}
