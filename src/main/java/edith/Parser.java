package edith;

import edith.command.Command;
import edith.command.ExitCommand;
import edith.command.ListCommand;
import edith.command.AddCommand;
import edith.command.ListOnDateCommand;
import edith.command.MarkCommand;
import edith.command.UnmarkCommand;
import edith.command.DeleteCommand;
import edith.command.FindCommand;

/**
 * Parses user input and converts it into executable commands for the Edith chatbot.
 * The Parser class interprets user commands and creates instances of appropriate
 * Command subclasses based on the input.
 */
public class Parser {
    /**
     * Parses the provided user input and returns the corresponding Command.
     * This method interprets the user's command and creates the appropriate command
     * instance to be executed.
     *
     * <p>Possible commands include:
     * <ul>
     *     <li>"bye" - Returns an ExitCommand.</li>
     *     <li>"list" - Returns a ListCommand.</li>
     *     <li>"list [date]" - Returns a ListOnDateCommand with the specified date.</li>
     *     <li>"mark [index]" - Returns a MarkCommand with the specified task index.</li>
     *     <li>"unmark [index]" - Returns a UnmarkCommand with the specified task index.</li>
     *     <li>"delete [index]" - Returns a DeleteCommand with the specified task index.</li>
     *     <li>"find [keyword]" - Returns a FindCommand with the specified keyword.</li>
     *     <li>"todo [task]" - Returns an AddCommand to add a ToDo task.</li>
     *     <li>"deadline [task] /by [dueDate]" - Returns an AddCommand to add a Deadline task.</li>
     *     <li>"event [task] /from [startTime] /to [endTime]" - Returns an AddCommand to add an Event task.</li>
     * </ul>
     *
     * @param userInput The raw input string from the user.
     * @return The corresponding Command based on the user input.
     * @throws EdithException If the user input does not match any recognized command format.
     */
    public Command parse(String userInput) {
        if (userInput.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (userInput.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("list ")) {
            String date = userInput.substring(5).trim();
            return new ListOnDateCommand(date);
        } else if (userInput.startsWith("mark ")) {
            int index = Integer.parseInt(userInput.substring(5).trim()) - 1;
            return new MarkCommand(index);
        } else if (userInput.startsWith("unmark ")) {
            int index = Integer.parseInt(userInput.substring(7).trim()) - 1;
            return new UnmarkCommand(index);
        } else if (userInput.startsWith("delete ")) {
            int index = Integer.parseInt(userInput.substring(7).trim()) - 1;
            return new DeleteCommand(index);
        } else if (userInput.startsWith("find ")) {
            String keyword = userInput.substring(5).trim();
            return new FindCommand(keyword);
        } else if (userInput.startsWith("todo ") || userInput.startsWith("t ")
                || userInput.startsWith("deadline ") || userInput.startsWith("d ")
                || userInput.startsWith("event ") || userInput.startsWith("e ")) {
            return new AddCommand(userInput);
        } else {
            throw new EdithException("Sorry but that is not an instruction I can execute.");
        }
    }
}
