package duck.parser;

import duck.commands.*;

/**
 * Parses user input and creates corresponding Command instances based on the input message.
 */
public class Parser {

    /**
     * Parses the given message and returns the appropriate Command based on the instruction in the message.
     *
     * @param message The user input message to parse.
     * @return A Command instance corresponding to the instruction in the message.
     *         Returns an InvalidCommand if the instruction is not recognized.
     */
    public static Command parse(String message) {
        try {
            CommandWord instruction = CommandWord.valueOf(getInstruction(message));
            return switch (instruction) {
                case LIST -> new ListCommand(message);
                case MARK -> new MarkCommand(message);
                case UNMARK -> new UnmarkCommand(message);
                case DELETE -> new DeleteCommand(message);
                case TODO -> new ToDoCommand(message);
                case DEADLINE -> new DeadlineCommand(message);
                case EVENT -> new EventCommand(message);
                case ON -> new OnCommand(message);
                case BYE -> new ByeCommand(message);
            };
        } catch (IllegalArgumentException e) {
            return new InvalidCommand("");
        }
    }

    /**
     * Extracts the instruction part from the given message.
     * The instruction is the first word of the message, converted to uppercase.
     *
     * @param message The user input message.
     * @return The instruction part of the message in uppercase.
     */
    private static String getInstruction(String message) {
        return message.split(" ")[0].toUpperCase();
    }
}
