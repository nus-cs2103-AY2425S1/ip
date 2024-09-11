package parser;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.SearchCommand;
import command.UnmarkCommand;
import fridayException.FridayException;
import fridayException.InvalidDeadlineArgument;
import fridayException.InvalidDeleteArgument;
import fridayException.InvalidEventArgument;
import fridayException.InvalidFindArgument;
import fridayException.InvalidFridayCommand;
import fridayException.InvalidMarkArgument;
import fridayException.InvalidSearchArgument;
import fridayException.InvalidTodoArgument;
import fridayException.InvalidUnmarkArgument;
import task.Deadline;
import task.Event;
import task.ToDo;

import java.time.LocalDate;

/**
 * Represents a parser that parses the user input.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param userInput The user input.
     * @return The corresponding command.
     * @throws FridayException If the user input is invalid.
     */
    public static Command parseUserInput(String userInput) throws FridayException {
        assert userInput != null : "User input should not be null";
        assert !userInput.trim().isEmpty() : "User input should not be empty";

        String[] parts = userInput.split(" ");
        String command = parts[0].toLowerCase();

        assert command != null && !command.isEmpty() : "Command should not be null or empty";

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            assert parts.length > 1 : "ToDo command must have a description";

            if (parts.length == 1) {
                throw new InvalidTodoArgument();
            }
            return new AddCommand(new ToDo(userInput.substring(5)));
        case "deadline":
            assert parts.length > 3 : "Deadline command must have sufficient arguments";
            assert userInput.contains("/by") : "Deadline command must contain '/by'";

            if (parts.length <= 3 || !userInput.contains("/by")) {
                throw new InvalidDeadlineArgument();
            }
            return new AddCommand(new Deadline(userInput.substring(9)));
        case "event":
            assert parts.length > 5 : "Event command must have sufficient arguments";
            assert userInput.contains("/from")
                    && userInput.contains("/to") : "Event command must contain both '/from' and '/to'";

            if (parts.length <= 5 || !userInput.contains("/from") || !userInput.contains("/to")) {
                throw new InvalidEventArgument();
            }
            return new AddCommand(new Event(userInput.substring(6)));
        case "mark":
            assert parts.length > 1 : "Mark command must include an index";

            if (parts.length == 1) {
                throw new InvalidMarkArgument();
            }
            return new MarkCommand(Integer.parseInt(parts[1]) - 1);

        case "unmark":
            assert parts.length > 1 : "Unmark command must include an index";

            if (parts.length == 1) {
                throw new InvalidUnmarkArgument();
            }
            return new UnmarkCommand(Integer.parseInt(parts[1]) - 1);

        case "delete":
            assert parts.length > 1 : "Delete command must include an index";

            if (parts.length == 1) {
                throw new InvalidDeleteArgument();
            }
            return new DeleteCommand(Integer.parseInt(parts[1]) - 1);
        case "search":
            assert parts.length > 1 : "Search command must include a date";

            if (parts.length == 1) {
                throw new InvalidSearchArgument();
            }
            LocalDate searchDate = LocalDate.parse(parts[1]);
            return new SearchCommand(searchDate);
        case "find":
            assert parts.length > 1 : "Find command must include a search term";

            if (parts.length == 1) {
                throw new InvalidFindArgument();
            }
            return new FindCommand(userInput.substring(5));
        default:
            assert !command.isEmpty() : "Command cannot be an empty string";
            throw new InvalidFridayCommand(userInput);
        }
    }
}
