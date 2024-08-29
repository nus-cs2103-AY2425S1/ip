package lict;

import lict.command.*;
import java.util.Arrays;

public class Parser {

    enum CommandType {
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        LIST,
        BYE,
        FIND,
    }

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

                case FIND:
                    c = new FindCommand(info);
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
