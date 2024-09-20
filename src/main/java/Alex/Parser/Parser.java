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
import Alex.Task.FixedDurationTask;
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
            return parseTodoCommand(userInput);
        } else if (userInput.startsWith("deadline ")) {
            return parseDeadlineCommand(userInput);
        } else if (userInput.startsWith("event ")) {
            return parseEventCommand(userInput);
        } else if (userInput.startsWith("fixed duration ")) {
            return parseFixedDurationCommand(userInput);
        } else if (userInput.startsWith("find ")) {
            return parseFindCommand(userInput);
        } else if (userInput.startsWith("mark ")) {
            return parseMarkCommand(userInput);
        } else if (userInput.startsWith("unmark ")) {
            return parseUnmarkCommand(userInput);
        } else if (userInput.startsWith("delete ")) {
            return parseDeleteCommand(userInput);
        } else if (userInput.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (userInput.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (userInput.equalsIgnoreCase("tell me a joke")) {
            return new JokeCommand();
        } else if (userInput.toLowerCase().equals("todo")) {
            throw new EmptyTodoException();
        } else if (userInput.toLowerCase().equals("blah") || userInput.toLowerCase().equals("fixed duration")) {
            throw new UnknownCommandException();
        }

        // Default behavior for unrecognized commands
        return parseDefaultCommand(userInput);
    }

    /**
     * Parses a "todo" command from the user input and returns an AddCommand with a new Todo task.
     *
     * @param userInput The user input containing the todo command.
     * @return An AddCommand with the Todo task.
     */
    private static Command parseTodoCommand(String userInput) {
        return new AddCommand(new Todo(userInput.substring(5)));
    }

    /**
     * Parses a "deadline" command from the user input and returns an AddCommand with a new Deadline task.
     *
     * @param userInput The user input containing the deadline command.
     * @return An AddCommand with the Deadline task.
     * @throws AlexException If the deadline format is invalid.
     */
    private static Command parseDeadlineCommand(String userInput) throws AlexException {
        String[] parts = userInput.substring(9).split(" /by ");
        if (parts.length < 2) {
            throw new AlexException("Invalid deadline format. "
                   + "Correct format: deadline [description] /by yyyy-MM-dd HH:mm or a valid day.");
        }
        return new AddCommand(new Deadline(parts[0], parts[1]));
    }

    /**
     * Parses an "event" command from the user input and returns an AddCommand with a new Event task.
     *
     * @param userInput The user input containing the event command.
     * @return An AddCommand with the Event task.
     * @throws AlexException If the event format is invalid.
     */
    private static Command parseEventCommand(String userInput) throws AlexException {
        String[] parts = userInput.substring(6).split(" /from | /to ");
        if (parts.length < 3) {
            throw new AlexException("Invalid event format. "
                    + "Correct format: event [description] /from [start day and/or time]  /to [end day and/or time]");
        }
        return new AddCommand(new Event(parts[0], parts[1], parts[2]));
    }

    /**
     * Parses a "fixed duration" command from the user input and returns an AddCommand with a new FixedDurationTask.
     *
     * @param userInput The user input containing the fixed duration command.
     * @return An AddCommand with the FixedDurationTask.
     * @throws AlexException If the format or duration of the task is invalid.
     */
    private static Command parseFixedDurationCommand(String userInput) throws AlexException {
        String taskAndDuration = userInput.substring(15);

        // Check if the format is correct, with duration inside parentheses
        int openParenIndex = taskAndDuration.indexOf('(');
        int closeParenIndex = taskAndDuration.indexOf(')');

        if (openParenIndex == -1 || closeParenIndex == -1 || !taskAndDuration.contains(" minutes")) {
            throw new AlexException("Invalid format. Correct format: fixed duration [task] (XX minutes)");
        }

        // Extract task description and duration
        String taskDescription = taskAndDuration.substring(0, openParenIndex).trim();
        String durationPart = taskAndDuration.substring(openParenIndex + 1, closeParenIndex).trim();

        // Split duration part and check format
        String[] durationParts = durationPart.split(" ");
        int duration;
        try {
            duration = Integer.parseInt(durationParts[0]);
            if (!durationParts[1].equals("minutes")) {
                throw new AlexException("Invalid format. Duration must be in minutes.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new AlexException("Invalid duration format. Use: fixed duration [task] (XX minutes)");
        }

        return new AddCommand(new FixedDurationTask(taskDescription, duration));
    }

    /**
     * Parses a "find" command from the user input and returns a FindCommand to search for tasks.
     *
     * @param userInput The user input containing the find command.
     * @return A FindCommand that will search for tasks matching the query.
     */
    private static Command parseFindCommand(String userInput) {
        return new FindCommand(userInput.substring(5));
    }


    /**
     * Parses a "mark" command from the user input and returns a MarkCommand to mark a task as done.
     *
     * @param userInput The user input containing the mark command.
     * @return A MarkCommand that will mark the task as done.
     */
    private static Command parseMarkCommand(String userInput) {
        return new MarkCommand(parseTaskIndex(userInput), true);
    }

    /**
     * Parses an "unmark" command from the user input and returns a MarkCommand to mark a task as not done.
     *
     * @param userInput The user input containing the unmark command.
     * @return A MarkCommand that will mark the task as not done.
     */
    private static Command parseUnmarkCommand(String userInput) {
        return new MarkCommand(parseTaskIndex(userInput), false);
    }

    /**
     * Parses a "delete" command from the user input and returns a DeleteCommand to delete a task.
     *
     * @param userInput The user input containing the delete command.
     * @return A DeleteCommand that will delete the task at the specified index.
     */
    private static Command parseDeleteCommand(String userInput) {
        return new DeleteCommand(parseTaskIndex(userInput));
    }

    /**
     * Parses a default command (an unrecognized task type) from the user input and returns an AddDefaultCommand.
     *
     * @param userInput The unrecognized command entered by the user.
     * @return An AddDefaultCommand with a DefaultTask.
     */
    private static Command parseDefaultCommand(String userInput) {
        return new AddDefaultCommand(new DefaultTask(userInput));
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



