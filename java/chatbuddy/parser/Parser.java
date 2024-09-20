package chatbuddy.parser;

import chatbuddy.command.*;
import chatbuddy.exception.ChatBuddyException;

/**
 * Parses user input and returns the appropriate command to execute.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param userInput The user's input string.
     * @return The command corresponding to the user input.
     * @throws ChatBuddyException If the user input is invalid.
     */
    public static Command parse(String userInput) throws ChatBuddyException {
        String[] parts = userInput.split(" ", 2);
        assert parts.length > 0 : "User input should not be empty";
        String commandWord = parts[0];

        switch (commandWord) {
        case "list":
            return new ListCommand();

        case "mark":
            return parseMarkCommand(parts);

        case "unmark":
            return parseUnmarkCommand(parts);

        case "todo":
            return parseTodoCommand(parts);

        case "deadline":
            return parseDeadlineCommand(parts);

        case "event":
            return parseEventCommand(parts);

        case "delete":
            return parseDeleteCommand(parts);

        case "find":
            return parseFindCommand(parts);

        case "update":
            return parseUpdateCommand(parts);

        case "bye":
            return new ExitCommand();

        default:
            throw new ChatBuddyException("Sorry, I don't understand that command.");
        }
    }

    /**
     * Parses the input for the 'mark' command and returns a MarkCommand.
     *
     * @param parts The user input split into command word and arguments.
     * @return A MarkCommand to mark the specified tasks as done.
     * @throws ChatBuddyException If the task number is not specified or invalid.
     */
    private static Command parseMarkCommand(String[] parts) throws ChatBuddyException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ChatBuddyException("Please specify the task number to mark.");
        }
        String[] markIndices = parts[1].split(" ");
        return new MarkCommand(markIndices);
    }

    /**
     * Parses the input for the 'unmark' command and returns an UnmarkCommand.
     *
     * @param parts The user input split into command word and arguments.
     * @return An UnmarkCommand to mark the specified tasks as not done.
     * @throws ChatBuddyException If the task number is not specified or invalid.
     */
    private static Command parseUnmarkCommand(String[] parts) throws ChatBuddyException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ChatBuddyException("Please specify the task number to unmark.");
        }
        String[] unmarkIndices = parts[1].split(" ");
        return new UnmarkCommand(unmarkIndices);
    }

    /**
     * Parses the input for the 'todo' command and returns an AddCommand for a ToDo task.
     *
     * @param parts The user input split into command word and arguments.
     * @return An AddCommand to add a new ToDo task.
     * @throws ChatBuddyException If the task description is empty or invalid.
     */
    private static Command parseTodoCommand(String[] parts) throws ChatBuddyException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ChatBuddyException("The description of a todo cannot be empty.");
        }
        return new AddCommand(parts[1], "T");
    }

    /**
     * Parses the input for the 'deadline' command and returns an AddCommand for a Deadline task.
     *
     * @param parts The user input split into command word and arguments.
     * @return An AddCommand to add a new Deadline task.
     * @throws ChatBuddyException If the task description is empty or invalid.
     */
    private static Command parseDeadlineCommand(String[] parts) throws ChatBuddyException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ChatBuddyException("The description of a deadline cannot be empty.");
        }
        return new AddCommand(parts[1], "D");
    }

    /**
     * Parses the input for the 'event' command and returns an AddCommand for an Event task.
     *
     * @param parts The user input split into command word and arguments.
     * @return An AddCommand to add a new Event task.
     * @throws ChatBuddyException If the task description is empty or invalid.
     */
    private static Command parseEventCommand(String[] parts) throws ChatBuddyException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ChatBuddyException("The description of an event cannot be empty.");
        }
        return new AddCommand(parts[1], "E");
    }

    /**
     * Parses the input for the 'delete' command and returns a DeleteCommand.
     *
     * @param parts The user input split into command word and arguments.
     * @return A DeleteCommand to delete the specified tasks.
     * @throws ChatBuddyException If the task number is not specified or invalid.
     */
    private static Command parseDeleteCommand(String[] parts) throws ChatBuddyException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ChatBuddyException("Please specify the task number to delete.");
        }
        String[] deleteIndices = parts[1].split(" ");
        return new DeleteCommand(deleteIndices);
    }

    /**
     * Parses the input for the 'find' command and returns a FindCommand.
     *
     * @param parts The user input split into command word and arguments.
     * @return A FindCommand to find tasks matching the specified keyword.
     * @throws ChatBuddyException If the keyword is not provided or invalid.
     */
    private static Command parseFindCommand(String[] parts) throws ChatBuddyException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ChatBuddyException("Please provide a keyword to search.");
        }
        return new FindCommand(parts[1]);
    }

    /**
     * Parses the input for the 'update' command and returns an UpdateCommand.
     *
     * @param parts The user input split into command word and arguments.
     * @return An UpdateCommand to update the specified task's field.
     * @throws ChatBuddyException If the task number, field type, or new details are invalid.
     */
    private static Command parseUpdateCommand(String[] parts) throws ChatBuddyException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ChatBuddyException("Usage: update <task number> <field type> <new details>");
        }

        String[] updateParts = parts[1].split(" ", 3); // Split into task number, field type, and new details
        if (updateParts.length < 3) {
            throw new ChatBuddyException("Usage: update <task number> <field type> <new details>");
        }

        try {
            int taskIndex = Integer.parseInt(updateParts[0]);  // Parse the task number
            String fieldType = updateParts[1];
            String newDetails = updateParts[2];

            if (!fieldType.equals("description") && !fieldType.equals("date")) {
                throw new ChatBuddyException("Invalid field type for update. Should be 'description' or 'date'.");
            }

            return new UpdateCommand(taskIndex, newDetails, fieldType);
        } catch (NumberFormatException e) {
            throw new ChatBuddyException("Task number must be a valid integer.");
        }
    }

}
