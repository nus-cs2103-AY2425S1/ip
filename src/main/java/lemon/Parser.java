package lemon;

import lemon.command.AddDeadlineCommand;
import lemon.command.AddEventCommand;
import lemon.command.AddTodoCommand;
import lemon.command.ByeCommand;
import lemon.command.Command;
import lemon.command.CommandType;
import lemon.command.DeleteTaskCommand;
import lemon.command.FindTaskCommand;
import lemon.command.HelpCommand;
import lemon.command.ListTaskCommand;
import lemon.command.MarkTaskCommand;
import lemon.command.UnmarkTaskCommand;

/**
 * Parse user input into commands
 * @author He Yiheng
 */
public class Parser {
    /**
     * Parse the input string from the user into the correct command object
     * @param input input command String
     * @return {@link Command} object relevant to the correct input command
     * @throws IllegalArgumentException exception for entering an invalid command
     */
    public static Command parseInputIntoCommand(String input) throws IllegalArgumentException {
        Command command = null;

        String[] splitInput = input.split(" ", 2);
        CommandType ct = CommandType.valueOf(splitInput[0].toUpperCase());

        switch (ct) {
        case BYE:
            command = new ByeCommand(ct);
            break;
        case LIST:
            command = new ListTaskCommand(ct);
            break;
        case MARK:
            command = new MarkTaskCommand(ct, input);
            break;
        case UNMARK:
            command = new UnmarkTaskCommand(ct, input);
            break;
        case DELETE:
            command = new DeleteTaskCommand(ct, input);
            break;
        case TODO:
            command = new AddTodoCommand(ct, input);
            break;
        case DEADLINE:
            command = new AddDeadlineCommand(ct, input);
            break;
        case EVENT:
            command = new AddEventCommand(ct, input);
            break;
        case FIND:
            command = new FindTaskCommand(ct, input);
            break;
        case HELP:
            command = new HelpCommand(ct);
            break;
        default:
            throw new IllegalArgumentException();
        }

        return command;
    }
}
