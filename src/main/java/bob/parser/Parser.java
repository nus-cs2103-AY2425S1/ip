package bob.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import bob.command.ByeCommand;
import bob.command.Command;
import bob.command.DeadlineCommand;
import bob.command.DeleteCommand;
import bob.command.EventCommand;
import bob.command.FindCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.TodoCommand;
import bob.ui.Ui;

/**
 * Handles user commands.
 */
public class Parser {

    /**
     * Parses string format of date and returns a LocalDate object.
     *
     * @param date String format of date.
     * @return LocalDate object.
     */
    @SuppressWarnings("checkstyle:EmptyCatchBlock")
    public static LocalDate parseDate(String date) {
        List<DateTimeFormatter> inputFormatters = List.of(
                DateTimeFormatter.ofPattern("yyyy-MM-dd"), //type 1 date format
                DateTimeFormatter.ofPattern("dd-MM-yyyy"), //type 2 date format
                DateTimeFormatter.ofPattern("dd/MM/yyyy"), //type 3 date format
                DateTimeFormatter.ofPattern("MMM dd yyyy") //type 4 date format

        );
        DateTimeParseException dateTimeParseException = null;
        for (DateTimeFormatter formatter : inputFormatters) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                dateTimeParseException = e;
            }
        }
        return null;
    }


    /**
     * Parses the input given by user.
     * @param input Input by user.
     * @return Command to be executed.
     */
    public static Command parseCommand(String input) {
        String[] inputWordsList = input.split("\s+");
        String keyword = inputWordsList[0];
        switch (keyword) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(input, true);
        case "unmark":
            return new MarkCommand(input, false);
        case "delete":
            return new DeleteCommand(input);
        case "event":
            return new EventCommand(input);
        case "deadline":
            return new DeadlineCommand(input);
        case "todo":
            return new TodoCommand(input);
        case "find":
            return new FindCommand(input);
        case "bye":
            return new ByeCommand(input);
        default:
            Ui.requestValidCommand();
            return new Command(input);
        }
    }
}
