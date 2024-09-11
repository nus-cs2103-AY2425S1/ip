package Chatbot;

/**
 * The {@code Parser} class is responsible for interpreting user input and converting it into a {@code Command}.
 * It recognizes various command types and returns the appropriate {@code Command} enum value.
 */
public class Parser {

    /**
     * Parses the user's input and returns the corresponding {@code Command} enum.
     * The method checks the input against known command patterns and returns the appropriate command.
     * If the input does not match any known command, it returns {@code Command.UNKNOWN}.
     *
     * @param input the user's input as a {@code String}.
     * @return the corresponding {@code Command} based on the input.
     */
    public Command parseCommand(String input) {
        if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.equals("list")) {
            return Command.LIST;
        } else if (input.equals("list contacts")) {
            return Command.LIST_CONTACTS;
        } else if (input.startsWith("mark ")) {
            return Command.MARK;
        } else if (input.startsWith("todo ")) {
            return Command.TODO;
        } else if (input.startsWith("deadline ")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event ")) {
            return Command.EVENT;
        } else if (input.startsWith("delete ")) {
            return Command.DELETE;
        } else if (input.startsWith("find ")) {
            return Command.FIND;
        } else if (input.startsWith("contact ")) {
            return Command.CONTACT;
        } else {
            return Command.UNKNOWN;
        }
    }
}
