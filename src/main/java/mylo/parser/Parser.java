package mylo.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import mylo.command.AddCommand;
import mylo.command.Command;
import mylo.command.DeleteCommand;
import mylo.command.ExitCommand;
import mylo.command.FindCommand;
import mylo.command.ListCommand;
import mylo.command.MarkStatusCommand;
import mylo.data.NoSuchCommandException;
import mylo.task.TaskType;
import mylo.utils.exceptions.IllegalValueException;
import mylo.utils.helpers.HelperFunctions;

/**
 * Parses user input into commands for the task management system.
 * <p></p>
 * <p>This class is responsible for interpreting the user's input and creating the appropriate
 * command objects that can be executed to manipulate tasks.</p>
 *
 * @author cweijin
 */
public class Parser {

    /**
     * Parses the given input string and returns the corresponding command.
     *
     * @param input The input string from the user.
     * @return A {@code Command} object representing the user's command.
     * @throws NoSuchCommandException If the input does not correspond to a valid command.
     * @throws IllegalValueException If the input contains illegal values.
     */
    public static Command parse(String input) throws NoSuchCommandException, IllegalValueException {
        String[] keys = input.split(" ");

        int index;
        Command result;

        switch (keys[0]) {
        case "list":
            if (keys.length == 1) {
                return new ListCommand();
            }
            LocalDateTime dateTime;
            switch (keys[1]) {
            case "today" -> dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
            case "on" -> dateTime = HelperFunctions.stringToDateTime(keys[2], true);
            default -> throw new NoSuchCommandException(input);
            }
            result = new ListCommand(dateTime);
            break;
        case "find":
            result = new FindCommand(keys[1]);
            break;
        case "delete":
            index = Integer.parseInt(keys[1]);
            result = new DeleteCommand(index);
            break;
        case "mark":
            index = Integer.parseInt(keys[1]);
            result = new MarkStatusCommand(true, index);
            break;
        case "unmark":
            index = Integer.parseInt(keys[1]);
            result = new MarkStatusCommand(false, index);
            break;
        case "todo":
            result = new AddCommand(TaskType.TODO, input.substring(4));
            break;
        case "deadline":
            result = new AddCommand(TaskType.DEADLINE, input.substring(8));
            break;
        case "event":
            result = new AddCommand(TaskType.EVENT, input.substring(5));
            break;
        case "bye":
            result = new ExitCommand();
            break;
        default:
            throw new NoSuchCommandException(input);
        }
        return result;
    }
}
