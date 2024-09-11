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
        String[] parts = userInput.split(" ");
        String command = parts[0].toLowerCase();

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            if (parts.length == 1) {
                throw new InvalidTodoArgument();
            }
            return new AddCommand(new ToDo(userInput.substring(5)));
        case "deadline":
            if (parts.length <= 3 || !userInput.contains("/by")) {
                throw new InvalidDeadlineArgument();
            }
            return new AddCommand(new Deadline(userInput.substring(9)));
        case "event":
            if (parts.length <= 5 || !userInput.contains("/from") || !userInput.contains("/to")) {
                throw new InvalidEventArgument();
            }
            return new AddCommand(new Event(userInput.substring(6)));
        case "mark":
            if (parts.length == 1) {
                throw new InvalidMarkArgument();
            }
            return new MarkCommand(Integer.parseInt(parts[1]) - 1);

        case "unmark":
            if (parts.length == 1) {
                throw new InvalidUnmarkArgument();
            }
            return new UnmarkCommand(Integer.parseInt(parts[1]) - 1);

        case "delete":
            if (parts.length == 1) {
                throw new InvalidDeleteArgument();
            }
            return new DeleteCommand(Integer.parseInt(parts[1]) - 1);
        case "search":
            if (parts.length == 1) {
                throw new InvalidSearchArgument();
            }
            LocalDate searchDate = LocalDate.parse(parts[1]);
            return new SearchCommand(searchDate);
        case "find":
            if (parts.length == 1) {
                throw new InvalidFindArgument();
            }
            return new FindCommand(userInput.substring(5));
        default:
            throw new InvalidFridayCommand(userInput);
        }
    }
}
