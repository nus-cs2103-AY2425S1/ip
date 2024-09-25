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
            return handleMarkInput(userInput);
        } else if (userInput.startsWith("unmark")) {
            return handleUnmarkInput(userInput);
        } else if (userInput.startsWith("todo")) {
            return handleTodoInput(userInput);
        } else if (userInput.startsWith("deadline")) {
            return handleDeadlineInput(userInput);
        } else if (userInput.startsWith("event")) {
            return handleEventInput(userInput);
        } else if (userInput.startsWith("delete")) {
            return handleDeleteInput(userInput);
        } else if (userInput.startsWith("find")) {
            return handleFindInput(userInput);
        } else {
            // elsa.ui.Elsa will ask for clarification upon encountering any unrecognised input
            throw new ElsaException("Sorry, I'm unable to perform this action: " + userInput);
        }
    }

    private static Command handleMarkInput(String userInput) throws ElsaException {
        // Ensure that the user input is long enough to contain a task number
        if (userInput.length() > 5) {
            String indexStr = userInput.substring(5).trim();
            // Check if the input after "mark" is a number
            if (indexStr.matches("\\d+")) {
                int index = Integer.parseInt(indexStr) - 1;
                return new MarkCommand(index);
            } else {
                // The input after "mark" is not a valid number
                throw new ElsaException("Oops! It appears that the task number entered isn't valid. Could you "
                        + "please try again.");
            }
        } else {
            // No number provided after "mark"
            throw new ElsaException("Oops! It appears that there was no task number entered. Could you please "
                    + "try again.");
        }
    }

    private static Command handleUnmarkInput(String userInput) throws ElsaException {
        // Ensure that the user input is long enough to contain a task number
        if (userInput.length() > 7) {
            String indexStr = userInput.substring(7).trim();
            // Check if the input after "unmark" is a number
            if (indexStr.matches("\\d+")) {
                int index = Integer.parseInt(indexStr) - 1;
                return new UnmarkCommand(index);
            } else {
                // The input after "unmark" is not a valid number
                throw new ElsaException("Oops! It appears that the task number entered isn't valid. Could you "
                        + "please try again.");
            }
        } else {
            // No number provided after "unmark"
            throw new ElsaException("Oops! It appears that there was no task number entered. Could you please "
                    + "try again.");
        }
    }

    private static Command handleTodoInput(String userInput) throws ElsaException {
        // Ensure that the user input is long enough to contain a description
        String description = userInput.length() > 5 ? userInput.substring(5).trim() : "";
        if (description.isEmpty()) {
            throw new ElsaException("Oh, it appears that the description of your "
                    + "ToDo task is empty...");
        }
        return new TodoCommand(description);
    }

    private static Command handleDeadlineInput(String userInput) throws ElsaException {
        String[] parts = userInput.split("/by", 2);
        // Check if there are two parts and if 'by' is in the correct format
        if (parts.length < 2 || parts[1].trim().length() != 16
                || !parts[1].trim().matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}")) {
            throw new ElsaException("Oops, the format for the Deadline task is a bit off.\nPlease follow this "
                    + "format: deadline [activity] /by yyyy-mm-dd hh:mm");
        }
        String description = parts[0].substring(9).trim();
        String dueBy = parts[1].trim();
        // Check if the description is empty
        if (description.isEmpty()) {
            throw new ElsaException("Oh, it appears that the description of your "
                    + "Deadline task is empty...");
        }
        return new DeadlineCommand(description, dueBy);
    }

    private static Command handleEventInput(String userInput) throws ElsaException {
        String[] parts = userInput.split("/from | /to");
        // Ensure there are exactly three parts: event description, start time, and end time
        if (parts.length != 3) {
            throw new ElsaException("Oops, the format for the Event task is a bit off.\nPlease follow this "
                    + "format: event [activity] /from [start time] /to [end time]");
        }
        String description = parts[0].substring(6).trim();
        String start = parts[1].trim();
        String end = parts[2].trim();
        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new ElsaException("Oh, it appears that the description of your "
                    + "Event task is empty...");
        }
        return new EventCommand(description, start, end);
    }

    private static Command handleDeleteInput(String userInput) throws ElsaException {
        // Ensure that the user input is long enough to contain a task number
        if (userInput.length() > 7) {
            String indexStr = userInput.substring(7).trim();
            // Check if the input after "delete" is a number
            if (indexStr.matches("\\d+")) {
                int index = Integer.parseInt(indexStr) - 1;
                return new DeleteCommand(index);
            } else {
                // The input after "delete" is not a valid number
                throw new ElsaException("Oops! It appears that the task number entered isn't valid. Could you "
                        + "please try again.");
            }
        } else {
            // No number provided after "delete"
            throw new ElsaException("Oops! It appears that there was no task number entered. Could you please "
                    + "try again.");
        }
    }

    private static Command handleFindInput(String userInput) {
        // If user just enters "find" followed by blank spaces, return a ListCommand
        if (userInput.trim().length() == 4) {
            return new ListCommand();
        }
        String taskToFind = userInput.substring(5).trim();
        return new FindCommand(taskToFind);
    }
}
