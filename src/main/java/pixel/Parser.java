package pixel;

import pixel.command.AddCommand;
import pixel.command.Command;
import pixel.command.DeleteCommand;
import pixel.command.ExitCommand;
import pixel.command.FindCommand;
import pixel.command.ListCommand;
import pixel.command.MarkCommand;
import pixel.command.PixelCommandEnum;
import pixel.command.SortCommand;
import pixel.task.Deadline;
import pixel.task.Event;
import pixel.task.Todo;

/**
 * The Parser class is responsible for parsing user input and converting it into
 * executable commands.
 */
public class Parser {
    /**
     * Parses the given full command and returns the corresponding Command object.
     *
     * @param fullCommand The full command entered by the user.
     * @return The Command object corresponding to the given command.
     * @throws PixelException If the command is not recognized or if there is an
     *                        error in parsing the command.
     */
    public static Command parser(String fullCommand) throws PixelException {
        String cmdString = fullCommand.split(" ")[0].toUpperCase();
        PixelCommandEnum cmd;

        boolean valid = false;
        for (PixelCommandEnum pixelCmd : PixelCommandEnum.values()) {
            if (cmdString.equals(pixelCmd.toString())) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new PixelException(String.format("OH NO!!! I don't understand '%s'! Try Again!", cmdString));
        }

        cmd = PixelCommandEnum.valueOf(cmdString.toUpperCase());

        String input = fullCommand.substring(cmdString.length()).strip();

        switch (cmd) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(input.strip());
        case UNMARK:
            return new MarkCommand(input.strip());
        case TODO:
            Todo todo = new Todo(input);
            return new AddCommand(todo);
        case DEADLINE:
            Deadline deadline = new Deadline(input);
            return new AddCommand(deadline);
        case EVENT:
            Event event = new Event(input);
            return new AddCommand(event);
        case DELETE:
            return new DeleteCommand(input.strip());
        case FIND:
            return new FindCommand(input.strip());
        case SORT:
            return new SortCommand();
        default:
            throw new PixelException("OH NO!!! I don't understand this! Try Again!");
        }
    }
}
