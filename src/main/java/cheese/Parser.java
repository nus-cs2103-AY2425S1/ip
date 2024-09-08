package cheese;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import cheese.command.AddCommand;
import cheese.command.Command;
import cheese.command.ExitCommand;
import cheese.command.FindCommand;
import cheese.command.ListCommand;
import cheese.command.MarkCommand;
import cheese.command.UpdateCommand;
import cheese.exception.CheeseException;
import cheese.exception.InputException;
import cheese.task.Deadline;
import cheese.task.Event;
import cheese.task.Task;
import cheese.task.ToDo;

/**
 * Parses user input
 */
public class Parser {
    //Formats for correct commands
    private static final String EDIT_ITEM_FORMAT = "[Command] [Index]";
    private static final String DATE_FORMAT = "YYYY-MM-DD";
    private static final String DEADLINE_FORMAT = "deadline [name] /by [date]";
    private static final String EVENT_FORMAT = "event [name] /from [date] /to [date]";
    /**
     * Parse user input
     * @param input full user input
     * @param size size of task list
     * @return command to execute
     * @throws CheeseException incorrect input
     */
    public static Command parse(String input, int size) throws CheeseException {
        String[] inputTokens = input.split(" ");
        String command = inputTokens[0];
        String cleanedInput = input.replace(command, "").strip();
        int idx;
        //Switch statement for different responses to different commands
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            idx = getIdx(inputTokens, size);
            return new MarkCommand(idx, true);
        case "unmark":
            idx = getIdx(inputTokens, size);
            return new MarkCommand(idx, false);
        case "todo":
            ToDo todo = new ToDo(cleanedInput);
            return new AddCommand(todo);
        case "deadline":
            Deadline deadline = parseDeadline(cleanedInput);
            return new AddCommand(deadline);
        case "event":
            Event event = parseEvent(cleanedInput);
            return new AddCommand(event);
        case "delete":
            idx = getIdx(inputTokens, size);
            return new UpdateCommand(idx, true);
        case "find":
            return new FindCommand(cleanedInput);
        default:
            return new AddCommand(new Task(input));
        }
    }

    /**
     * Return Deadline parsed from user input
     * @param input cleaned input
     * @return Deadline from the input
     * @throws CheeseException if user inputs are incorrect.
     */
    public static Deadline parseDeadline(String input) throws CheeseException {
        String[] tokens = input.strip().split("/by");
        if (tokens.length != 2) {
            throw new InputException(DEADLINE_FORMAT, "Missing a /by");
        }
        if (tokens[1].isBlank()) {
            throw new InputException(DEADLINE_FORMAT, "Date is empty");
        }
        return new Deadline(tokens[0].strip(), parseDate(tokens[1]));
    }

    /**
     * Returns Event parsed from user input
     * @param input cleaned input
     * @return Event from input
     * @throws CheeseException if user inputs are incorrect
     */
    public static Event parseEvent(String input) throws CheeseException {
        String[] words = input.strip().split("/from");
        if (words.length < 2) {
            throw new InputException(EVENT_FORMAT, "Missing /from");
        }
        String[] dates = words[1].split("/to");
        if (dates.length < 2) {
            throw new InputException(EVENT_FORMAT, "Missing /to");
        }
        return new Event(words[0].strip(), parseDate(dates[0].strip()), parseDate(dates[1].strip()));
    }
    /**
     * Returns index of item in list requested from user input
     * @param inputTokens input from user
     * @return int
     * @throws CheeseException Missing/Incorrect input
     */
    public static int getIdx(String[] inputTokens, int size) throws CheeseException {
        if (inputTokens.length != 2) {
            throw new InputException(EDIT_ITEM_FORMAT, "Need location of cheese");
        }
        int idx;
        try {
            idx = Integer.parseInt(inputTokens[1]) - 1;
        } catch (NumberFormatException e) {
            throw new CheeseException(e.getMessage());
        }
        if (idx >= size || idx < 0) {
            throw new CheeseException("Incorrect location of cheese");
        }
        return idx;
    }

    /**
     * Returns LocalDate by parsing a dateStr in specific format
     * @param dateStr specific format for date
     * @return LocalDate
     * @throws CheeseException if dateStr wrong
     */
    public static LocalDate parseDate(String dateStr) throws CheeseException {
        LocalDate d;
        dateStr = dateStr.strip();
        try {
            d = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new InputException(DATE_FORMAT, "");
        }
        return d;
    }
}
