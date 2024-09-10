package joe;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import joe.command.Command;
import joe.command.DeadlineCommand;
import joe.command.DeleteCommand;
import joe.command.EventCommand;
import joe.command.ExitCommand;
import joe.command.FindCommand;
import joe.command.ListCommand;
import joe.command.TodoCommand;
import joe.command.ToggleCommand;
import joe.command.UnknownCommand;

/**
 * The {@code Parser} class is used to parse raw input from the user and
 * return a {@code Command} object to be executed by the driver.
 */
public class Parser {
    /**
     * Parses the raw input by the user and returns a {@code Command} object to be executed.
     * The method determines the type of command based on the input and constructs the appropriate
     * {@code Command} object.
     *
     * @param rawInput The raw string input by the user.
     * @return A {@code Command} object that will be executed.
     * @throws JoeException if the command is unknown or if the input is invalid.
     */
    public static Command parse(String rawInput) {
        assert rawInput != null : "rawInput should not be null";
        if (rawInput.isEmpty()) {
            throw new JoeException("\tOOPS! You did not enter anything");
        }
        String[] inputArr = rawInput.split(" ");
        String strCommand = inputArr[0].toUpperCase();
        Commands command = null;
        try {
            command = Commands.valueOf(strCommand);
        } catch (IllegalArgumentException e) {
            throw new JoeException("Unknown command");
        }
        switch (command) {
        case BYE -> {
            return new ExitCommand();
        }
        case LIST -> {
            return new ListCommand();
        }
        case TODO -> {
            if (inputArr.length == 1) {
                throw new JoeException("OOPS!!! The description of a todo cannot be empty.");
            }
            String desc = String.join(" ", Arrays.copyOfRange(inputArr, 1, inputArr.length));
            return new TodoCommand(desc);
        }
        case DEADLINE -> {
            int byIdx = Arrays.asList(inputArr).indexOf("/by");
            if (byIdx == -1) {
                throw new JoeException("Oops! Try: deadline {desc} /by {duedate}");
            }
            String taskDesc = String.join(" ", Arrays.copyOfRange(inputArr, 1, byIdx));
            String taskBy = String.join("", Arrays.copyOfRange(inputArr, byIdx + 1, inputArr.length));
            try {
                LocalDate taskByDate = LocalDate.parse(taskBy);
                return new DeadlineCommand(taskDesc, taskByDate);
            } catch (DateTimeParseException e) {
                throw new JoeException("Please enter a date with the format yyyy-mm-dd");
            }
        }
        case EVENT -> {
            int fromIdx = Arrays.asList(inputArr).indexOf("/from");
            int toIdx = Arrays.asList(inputArr).indexOf("/to");
            if (fromIdx == -1) {
                throw new JoeException("Oops! Try: event {desc} /from {start} /to {end}");
            }
            String eventDesc = String.join(" ", Arrays.copyOfRange(inputArr, 1, fromIdx));
            if (toIdx != -1) {
                String eventFrom = String.join(" ", Arrays.copyOfRange(inputArr, fromIdx + 1, toIdx));
                String eventTo = String.join(" ", Arrays.copyOfRange(inputArr, toIdx + 1, inputArr.length));
                try {
                    LocalDate fromDate = LocalDate.parse(eventFrom);
                    LocalDate toDate = LocalDate.parse(eventTo);
                    return new EventCommand(eventDesc, fromDate, toDate);
                } catch (DateTimeParseException e) {
                    throw new JoeException("Please enter a date with the format yyyy-mm-dd");
                }
            } else {
                try {
                    String eventFrom = String.join(" ", Arrays.copyOfRange(inputArr, fromIdx + 1, inputArr.length));
                    LocalDate fromDate = LocalDate.parse(eventFrom);
                    LocalDate maxDate = LocalDate.MAX;
                    return new EventCommand(eventDesc, fromDate, maxDate);
                } catch (DateTimeParseException e) {
                    throw new JoeException("Please enter a date with the format yyyy-mm-dd");
                }
            }
        }
        case DELETE -> {
            return new DeleteCommand(inputArr);
        }
        case MARK, UNMARK -> {
            return new ToggleCommand(command, inputArr);
        }
        case FIND -> {
            if (inputArr.length != 2) {
                throw new JoeException("Find currently only supports a single word query");
            }
            String query = inputArr[1];
            return new FindCommand(query);
        }
        default -> {
            return new UnknownCommand();
        }
        }
    }
}
