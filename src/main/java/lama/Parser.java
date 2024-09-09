package lama;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lama.command.AddCommand;
import lama.command.Command;
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

    /**
     * Parses the given user input and returns the corresponding command.
     *
     * @param command String input by user.
     * @return A command representing the user's input.
     * @throws LamaException Thrown if input command is invalid or improperly formatted.
     */
    @SuppressWarnings("checkstyle:Regexp")
    public static Command parse(String command) throws LamaException {
        assert command != null : "Command should not be null";
        assert !command.isBlank() : "Command should not be empty or blank";

        String[] words = command.split(" ", 2);

        assert words.length > 0 : "Command should not be an empty string";

        switch (words[0].toLowerCase()) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            if (words.length < 2 || words[1].isBlank() || words[1].isEmpty()) {
                throw new LamaException("Please specify the number that wanted to be marked as done!");
            }

            return new MarkCommand(Integer.parseInt(words[1].trim()) - 1);

        case "unmark":
            if (words.length < 2 || words[1].isBlank() || words[1].isEmpty()) {
                throw new LamaException("Please specify the number that wanted to be unmarked!");
            }

            return new UnmarkCommand(Integer.parseInt(words[1].trim()) - 1);

        case "todo":
            if (words.length < 2 || words[1].isEmpty() || words[1].isBlank()) {
                throw new LamaException("Please specify the description of TODO!");
            }

            return new AddCommand(new Todo(words[1].trim()));

        case "deadline":
            if (words.length < 2 || words[1].isEmpty() || words[1].isBlank()) {
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

        case "event":
            if (words.length < 2 || words[1].isBlank() || words[1].isEmpty()) {
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

            if (time.length < 2 || time[1].isEmpty() || time[1].isBlank()) {
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

        case "delete":
            if (words.length < 2 || words[1].isBlank() || words[1].isEmpty()) {
                throw new LamaException("Please specify the number that wanted to delete!");
            }

            return new DeleteCommand(Integer.parseInt(words[1].trim()) - 1);

        case "find":
            if (words.length < 2 || words[1].isBlank() || words[1].isEmpty()) {
                throw new LamaException("Please specify the keyword you wanted to search!");
            }

            return new FindCommand(words[1].trim());

        default:
            throw new LamaException("Sorry, I don't know what you want to do!\n"
                    + "You can either choose to use:\n"
                    + "1. todo [Your TODO]\n"
                    + "2. deadline [Your TODO] /by [date of deadline]\n"
                    + "3. event [Your event] /from [start time] /to [end time]\n"
                    + "4. list\n"
                    + "5. mark [number of todo in the list]\n"
                    + "6. unmark [number of todo in the list]\n"
                    + "7. find [keywords]\n"
                    + "8. bye");
        }


    }

}
