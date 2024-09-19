package elsa.ui;

import elsa.ElsaException;
import elsa.command.ByeCommand;
import elsa.command.Command;
import elsa.command.DeadlineCommand;
import elsa.command.DeleteCommand;
import elsa.command.EventCommand;
import elsa.command.FindCommand;
import elsa.command.ListCommand;
import elsa.command.MarkCommand;
import elsa.command.TodoCommand;
import elsa.command.UnmarkCommand;

/**
 * Interprets and processes user commands.
 * Converts user input into actionable commands for the application to execute.
 *
 * @author Aaron
 */
public class Parser {
    /**
     * This method creates a new scanner object to process user input.
     */
    public static Command parse(String userInput) throws ElsaException {
        if (userInput.contains("bye")) {
            return new ByeCommand();
        } else if (userInput.contains("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("mark")) {
            int index = Integer.parseInt(userInput.substring(5)) - 1;
            return new MarkCommand(index);
        } else if (userInput.startsWith("unmark")) {
            int index = Integer.parseInt(userInput.substring(7)) - 1;
            return new UnmarkCommand(index);
        } else if (userInput.startsWith("todo")) {
            // Ensure that the user input is long enough to contain a description
            String description = userInput.length() > 5 ? userInput.substring(5).trim() : "";
            if (description.isEmpty()) {
                throw new ElsaException("Oh, it appears that the description of your "
                        + "ToDo item is empty...");
            }
            return new TodoCommand(description);
        } else if (userInput.startsWith("deadline")) {
            String[] parts = userInput.split(" /by ", 2);
            // Check if there are two parts and if 'by' is in the correct format
            if (parts.length < 2 || parts[1].trim().length() != 16
                    || !parts[1].trim().matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}")) {
                throw new ElsaException("Oops, the format for the elsa.task.Deadline task is "
                        + "a bit off.\nPlease follow this format: deadline [activity] /by yyyy-mm-dd hh:mm");
            }
            String description = parts[0].substring(9).trim();
            String dueBy = parts[1].trim();
            // Check if the description is empty
            if (description.isEmpty()) {
                throw new ElsaException("Oh, it appears that the description of your "
                        + "elsa.task.Deadline task is empty...");
            }
            return new DeadlineCommand(description, dueBy);
        } else if (userInput.startsWith("event")) {
            String[] parts = userInput.split(" /from | /to ");
            return new EventCommand(parts[0].substring(6).trim(), parts[1].trim(), parts[2].trim());
        } else if (userInput.startsWith("delete")) {
            int index = Integer.parseInt(userInput.substring(7)) - 1;
            return new DeleteCommand(index);
        } else if (userInput.startsWith("find")) {
            // If user just enters "find" followed by blank spaces, return a ListCommand
            if (userInput.trim().length() == 4) {
                return new ListCommand();
            }
            String taskToFind = userInput.substring(5).trim();
            return new FindCommand(taskToFind);
        } else {
            // elsa.ui.Elsa will ask for clarification upon encountering any unrecognised input
            throw new ElsaException("Sorry, I'm unable to perform this action: " + userInput);
        }
    }
}
