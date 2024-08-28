package Asta.command;

/**
 * The Parser class is responsible for interpreting user input and converting it into specific commands
 * that the application can execute. It serves as a bridge between raw user input and command execution.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     * The method checks the input against known commands and returns a Command enum representing the type of command
     * to execute.
     *
     * @param input The raw input string from the user.
     * @return A Command enum representing the type of command parsed from the input.
     */
    public static Command parse(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return Command.BYE;
        } else if (input.equalsIgnoreCase("list")) {
            return Command.LIST;
        } else if (input.startsWith("mark ")) {
            return Command.MARK;
        } else if (input.startsWith("unmark ")) {
            return Command.UNMARK;
        } else if (input.startsWith("todo ")) {
            return Command.TODO;
        } else if (input.startsWith("deadline ")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event ")) {
            return Command.EVENT;
        } else if (input.startsWith("delete ")) {
            return Command.DELETE;
        } else {
            return Command.UNKNOWN;
        }
    }
}
