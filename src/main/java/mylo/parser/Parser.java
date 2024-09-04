package mylo.parser;

import mylo.command.*;
import mylo.data.InsufficientInfoException;
import mylo.data.NoSuchCommandException;
import mylo.task.TaskList;
import mylo.task.TaskType;
import mylo.utils.exceptions.IllegalValueException;
import mylo.utils.helpers.HelperFunctions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Parser {
    public static Command parse(String input) throws NoSuchCommandException, IllegalValueException {
        String[] keys = input.split(" ");
        switch (keys[0]) {
        case "list": {
            if (keys.length == 1) return new ListCommand();
            LocalDateTime dateTime;
            switch (keys[1]) {
            case "today" -> dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
            case "on" -> dateTime = HelperFunctions.stringToDateTime(keys[2], true);
            default -> throw new NoSuchCommandException(input);
            }
            return new ListCommand(dateTime);
        }
        case "delete": {
            int index = Integer.parseInt(keys[1]);
            return new DeleteCommand(index);
        }
        case "mark": {
            int index = Integer.parseInt(keys[1]);
            return new MarkStatusCommand(true, index);
        }
        case "unmark": {
            int index = Integer.parseInt(keys[1]);
            return new MarkStatusCommand(false, index);
        }
        case "todo":
            return new AddCommand(TaskType.TODO, input.substring(4));
        case "deadline":
            return new AddCommand(TaskType.DEADLINE, input.substring(8));

        case "event":
            return new AddCommand(TaskType.EVENT, input.substring(5));

        case "bye":
            return new ExitCommand();
        default:
            throw new NoSuchCommandException(input);
        }
    }
}
