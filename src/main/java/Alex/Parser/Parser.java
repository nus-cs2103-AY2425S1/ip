package Alex.Parser;

import Alex.Command.AddCommand;
import Alex.Command.AddDefaultCommand;
import Alex.Command.Command;
import Alex.Command.DeleteCommand;
import Alex.Command.ExitCommand;
import Alex.Command.FindCommand;
import Alex.Command.JokeCommand;
import Alex.Command.ListCommand;
import Alex.Command.MarkCommand;
import Alex.Exceptions.AlexException;
import Alex.Exceptions.EmptyTodoException;
import Alex.Exceptions.UnknownCommandException;
import Alex.Task.Deadline;
import Alex.Task.DefaultTask;
import Alex.Task.Event;
import Alex.Task.Todo;

/**
 * Parses user input into executable commands.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param userInput The input entered by the user.
     * @return The corresponding Command object.
     * @throws AlexException If the input is invalid or unrecognized.

     */
    public static Command parse(String userInput) throws AlexException {
        userInput = userInput.trim().toLowerCase(); // Normalize the input

        if (userInput.startsWith("todo ")) {
            return new AddCommand(new Todo(userInput.substring(5)));
        } else if (userInput.startsWith("deadline ")) {
            String[] parts = userInput.substring(9).split(" /by ");
            if (parts.length < 2) {
                throw new AlexException("Invalid deadline format. Correct format: deadline [description] /by [date]");
            }
            return new AddCommand(new Deadline(parts[0], parts[1]));
        } else if (userInput.startsWith("event ")) {
            String[] parts = userInput.substring(6).split(" /from | /to ");
            if (parts.length < 3) {
                throw new AlexException("Invalid event format. Correct format: event [description] /from [start] /to [end]");
            }
            return new AddCommand(new Event(parts[0], parts[1], parts[2]));
        } else if (userInput.startsWith("find ")) {
            return new FindCommand(userInput.substring(5));
        } else if (userInput.startsWith("mark ")) {
            return new MarkCommand(parseTaskIndex(userInput), true);
        } else if (userInput.startsWith("unmark ")) {
            return new MarkCommand(parseTaskIndex(userInput), false);
        } else if (userInput.startsWith("delete ")) {
            return new DeleteCommand(parseTaskIndex(userInput));
        } else if (userInput.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (userInput.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (userInput.equalsIgnoreCase("tell me a joke")) {
            return new JokeCommand();
        } else if (userInput.toLowerCase().equals("todo")) {
            throw new EmptyTodoException();
        } else if (userInput.toLowerCase().equals("blah")) {
            throw new UnknownCommandException();
        } else {
            // Default behavior for unrecognized commands
            return new AddDefaultCommand(new DefaultTask(userInput));
        }
    }

    /**
     * Parses the task index from the user input.
     *
     * @param userInput The input string from the user.
     * @return The zero-based index of the task, or -1 if parsing fails.
     */
    private static int parseTaskIndex(String userInput) {
        try {
            return Integer.parseInt(userInput.split(" ")[1]) - 1; // Convert to zero-based index
        } catch (Exception e) {
            return -1; // Return -1 if parsing fails
        }
    }
}



