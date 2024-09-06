package charlotte.parser;

import charlotte.command.AddCommand;
import charlotte.command.Command;
import charlotte.command.DeleteCommand;
import charlotte.command.ExitCommand;
import charlotte.command.FindCommand;
import charlotte.command.ListCommand;
import charlotte.command.MarkCommand;
import charlotte.command.UnmarkCommand;
import charlotte.exception.CharlotteException;
import charlotte.task.Deadline;
import charlotte.task.Event;
import charlotte.task.ToDo;

/**
 * The Parser class is responsible for interpreting user input
 * and returning the appropriate command to be executed.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param userInput The input string entered by the user.
     * @return The Command object corresponding to the user's input.
     * @throws CharlotteException If the user input is not recognized or formatted incorrectly.
     */
    public static Command parse(String userInput) throws CharlotteException {
        String[] inputParts = userInput.split(" ", 2);
        String command = inputParts[0];

        switch (command) {
        case "list":
            return new ListCommand();
        case "mark":
            if (inputParts.length < 2) {
                throw new CharlotteException("Oops! You need to specify the task number!");
            }
            return new MarkCommand(Integer.parseInt(inputParts[1]));
        case "unmark":
            if (inputParts.length < 2) {
                throw new CharlotteException("Oops! You need to specify the task number!");
            }
            return new UnmarkCommand(Integer.parseInt(inputParts[1]));
        case "delete":
            if (inputParts.length < 2) {
                throw new CharlotteException("Oops! You need to specify the task number!");
            }
            return new DeleteCommand(Integer.parseInt(inputParts[1]));
        case "todo":
            if (inputParts.length < 2) {
                throw new CharlotteException("Oops! The description of a todo cannot be empty!");
            }
            return new AddCommand(new ToDo(inputParts[1]));
        case "deadline":
            if (inputParts.length < 2 || !inputParts[1].contains(" /by ")) {
                throw new CharlotteException("Oops! The correct format for deadline is: deadline task /by date");
            }
            String[] deadlineParts = inputParts[1].split(" /by ");
            return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]));
        case "event":
            if (inputParts.length < 2 || !inputParts[1].contains(" /from ") || !inputParts[1].contains(" /to ")) {
                throw new CharlotteException("Oops! The correct format for event is: event task /from start /to end");
            }
            String[] eventParts = inputParts[1].split(" /from | /to ");
            return new AddCommand(new Event(eventParts[0], eventParts[1], eventParts[2]));
        case "find":
            if (inputParts.length < 2) {
                throw new CharlotteException("Oops! You need to specify a keyword to find!");
            }
            return new FindCommand(inputParts[1]);
        case "bye":
            return new ExitCommand();
        default:
            throw new CharlotteException("Sorry I don't know what that means :( Please try again!");
        }
    }
}
