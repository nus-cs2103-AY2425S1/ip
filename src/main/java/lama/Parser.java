package lama;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lama.command.AddCommand;
import lama.command.AliasCommand;
import lama.command.AliasListCommand;
import lama.command.Command;
import lama.command.DeleteAliasCommand;
import lama.command.DeleteCommand;
import lama.command.ExitCommand;
import lama.command.FindCommand;
import lama.command.ListCommand;
import lama.command.MarkCommand;
import lama.command.UnmarkCommand;
import lama.task.Deadline;
import lama.task.Event;
import lama.task.Todo;

/**
 * Parses user input and returns the corresponding command.
 */
public class Parser {

    private static final String UNKNOWN_COMMAND = "Sorry, I don't know what you want to do!\n"
            + "You can either choose to use:\n"
            + "1. todo [Your TODO]\n"
            + "2. deadline [Your TODO] /by [date of deadline]\n"
            + "3. event [Your event] /from [start time] /to [end time]\n"
            + "4. list\n"
            + "5. mark [number of todo in the list]\n"
            + "6. unmark [number of todo in the list]\n"
            + "7. find [keywords]\n"
            + "8. alias [alias] [command]\n"
            + "9. aliases\n"
            + "10. remove [alias]\n"
            + "11. bye";

    /**
     * Parses the given user input and returns the corresponding command.
     *
     * @param command String input by user.
     * @return A command representing the user's input.
     * @throws LamaException Thrown if input command is invalid or improperly formatted.
     */
    public static Command parse(String command) throws LamaException {
        assert command != null : "Command should not be null";
        assert !command.isBlank() : "Command should not be empty or blank";

        if (command.trim().isEmpty()) {
            throw new LamaException(UNKNOWN_COMMAND);
        }

        String[] words = command.split(" ", 2);

        assert words.length > 0 : "Command should not be an empty string";

        String resolvedCommand = AliasManager.getCommand(words[0]);

        switch (resolvedCommand.toLowerCase()) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            return handleMarkUnmarkCommand(words, true);

        case "unmark":
            return handleMarkUnmarkCommand(words, false);

        case "todo":
            return handleTodoCommand(words);

        case "deadline":
            return handleDeadlineCommand(words);

        case "event":
            return handleEventCommand(words);

        case "delete":
            return handleDeleteCommand(words);

        case "find":
            return handleFindCommand(words);

        case "alias":
            return handleAliasCommand(words);

        case "aliases":
            return new AliasListCommand();

        case "remove":
            return handleDeleteAliasCommand(words);

        default:
            throw new LamaException(UNKNOWN_COMMAND);
        }

    }

    private static Command handleMarkUnmarkCommand(String[] words, boolean isMark) throws LamaException {
        if (words.length < 2 || words[1].isBlank() || words[1].isEmpty()) {
            throw new LamaException(isMark ? "Please specify the number that wanted to be marked as done!"
                    : "Please specify the number that wanted to be unmarked!");
        }

        return isMark
                ? new MarkCommand(Integer.parseInt(words[1].trim()) - 1)
                : new UnmarkCommand(Integer.parseInt(words[1].trim()) - 1);
    }

    private static Command handleTodoCommand(String[] words) throws LamaException {
        boolean isInvalidInput = words.length < 2 || words[1].isBlank() || words[1].isEmpty();

        if (isInvalidInput) {
            throw new LamaException("Please specify the description of TODO!");
        }

        return new AddCommand(new Todo(words[1].trim()));
    }

    private static Command handleDeadlineCommand(String[] words) throws LamaException {
        boolean isInvalidInput = words.length < 2 || words[1].isBlank() || words[1].isEmpty();

        if (isInvalidInput) {
            throw new LamaException("Please specify the description of deadline!");
        }

        String[] half = words[1].split(" /by ");

        if (half.length < 2) {
            throw new LamaException("Please specify the date of deadline in the format of:\n"
                    + "deadline [description] /by [date]");
        }

        if (half[0].isEmpty() || half[0].isBlank()) {
            throw new LamaException("Please specify the description of deadline!");
        }

        try {
            LocalDate date = LocalDate.parse(half[1].trim());
            return new AddCommand(new Deadline(half[0].trim(), date));
        } catch (DateTimeException e) {
            throw new LamaException("Date format should follow yyyy-MM-dd");
        }
    }

    private static Command handleEventCommand(String[] words) throws LamaException {
        boolean isInvalidInput = words.length < 2 || words[1].isBlank() || words[1].isEmpty();
        if (isInvalidInput) {
            throw new LamaException("Please specify the description of event in the format of:\n"
                    + "event [description] /from [start time] /to [end time]");
        }

        String[] first = words[1].split(" /from ");
        if (first.length < 2) {
            throw new LamaException("Please specify the start time of event in the format of:\n"
                    + "event [description] /from [start time] /to [end time]");
        }

        if (first[0].isEmpty() || first[0].isBlank()) {
            throw new LamaException("Please specify the description of event in the format of:\n"
                    + "event [description] /from [start time] /to [end time]");
        }

        String[] time = first[1].split(" /to ");
        boolean isValidTime = time.length < 2 || time[1].isEmpty() || time[1].isBlank();
        if (isValidTime) {
            throw new LamaException("Please specify the end time of event in the format of:\n"
                    + "event [description] /from [start time] /to [end time]");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime from = LocalDateTime.parse(time[0].trim(), formatter);
            LocalDateTime to = LocalDateTime.parse(time[1].trim(), formatter);
            return new AddCommand(new Event(first[0].trim(), from, to));
        } catch (DateTimeException e) {
            throw new LamaException("Date time format should follow yyyy-MM-dd HHmm");
        }
    }

    private static Command handleDeleteCommand(String[] words) throws LamaException {
        boolean isInvalidInput = words.length < 2 || words[1].isBlank() || words[1].isEmpty();
        if (isInvalidInput) {
            throw new LamaException("Please specify the number that wanted to delete!");
        }

        return new DeleteCommand(Integer.parseInt(words[1].trim()) - 1);
    }

    private static Command handleFindCommand(String[] words) throws LamaException {
        boolean isInvalidInput = words.length < 2 || words[1].isBlank() || words[1].isEmpty();
        if (isInvalidInput) {
            throw new LamaException("Please specify the keyword you wanted to search!");
        }

        return new FindCommand(words[1].trim());
    }

    private static Command handleAliasCommand(String[] words) throws LamaException {
        boolean isInvalidInput = words.length < 2 || words[1].isBlank() || words[1].isEmpty();

        if (isInvalidInput) {
            throw new LamaException("Please specify both alias and command.");
        }

        String[] aliasParts = words[1].split(" ", 2);

        boolean isValidAliasParts = aliasParts.length < 2 || aliasParts[1].isEmpty() || aliasParts[1].isBlank();

        if (isValidAliasParts) {
            throw new LamaException("Please specify both alias and command.");
        }

        return new AliasCommand(aliasParts[0], aliasParts[1]);
    }

    private static Command handleDeleteAliasCommand(String[] words) throws LamaException {
        boolean isInvalidInput = words.length < 2 || words[1].isBlank() || words[1].isEmpty();

        if (isInvalidInput) {
            throw new LamaException("Please specify the alias you want to delete");
        }

        return new DeleteAliasCommand(words[1].trim());
    }

}
