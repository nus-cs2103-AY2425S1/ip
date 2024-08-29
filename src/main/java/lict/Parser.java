package lict;

import lict.command.*;
import java.util.Arrays;

/**
 * The {@code Parser} class is responsible for interpreting and converting user input strings into specific {@code Command} objects.
 * It recognizes various command types and ensures that the input is parsed correctly before creating the corresponding command.
 */
public class Parser {

    /**
     * The {@code Parser} class is responsible for interpreting and converting user input strings into specific {@code Command} objects.
     * It recognizes various command types and ensures that the input is parsed correctly before creating the corresponding command.
     */
    enum CommandType {
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        LIST,
        BYE,
    }

    /**
     * Parses the user input and returns the corresponding {@code Command} object.
     *
     * @param command The full user input string.
     * @return A {@code Command} object representing the user's command.
     * @throws LictException If the input does not match any known command types.
     */
    public static Command parse(String command) throws LictException {
        String[] commandParts = command.split("\\s+", 2);
        Command c = null;
        try {
            CommandType type = CommandType.valueOf(commandParts[0].trim().toUpperCase());
            String info;
            if (commandParts.length == 2) {
                info = commandParts[1].trim();
            } else {
                info = "";
            }

            switch (type) {
            case LIST:
                c = new ListCommand();
                break;

            case MARK:
                c = new MarkCommand(info);
                break;

            case UNMARK:
                c = new UnmarkCommand(info);
                break;

            case DELETE:
                c = new DeleteCommand(info);
                break;

            case TODO:
                c = new TodoCommand(info);
                break;

            case DEADLINE:
                c = new DeadlineCommand(info);
                break;

            case EVENT:
                c = new EventCommand(info);
                break;

            case BYE:
                c = new ByeCommand();
                break;

            default:
                //Fallthrough
            }
        } catch (IllegalArgumentException e) {
            throw new LictException("OOPS!!! I'm sorry, but I don't know what that means. Please only input tasks which start with these words: " + Arrays.toString(CommandType.values()).toLowerCase());
        }
        return c;
    }
}
