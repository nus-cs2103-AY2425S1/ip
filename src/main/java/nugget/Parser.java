package nugget;

import java.time.format.DateTimeParseException;

/**
 * The {@code Parser} class is responsible for parsing user input and converting it into {@code Command} objects.
 * It handles various types of commands such as adding tasks, marking tasks, deleting tasks, and more.
 */
public class Parser {

    private TaskList tasks;

    /**
     * Constructs a {@code Parser} with the specified task list.
     *
     * @param tasks The list of tasks to be manipulated by the commands.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the user input and returns the corresponding {@code Command}.
     *
     * @param command The user input string.
     * @return The {@code Command} object corresponding to the user input.
     * @throws NuggetException If the user input is invalid or the command is unknown.
     */
    public Command parse(String command) throws NuggetException {
        String[] splitText = command.split(" ", 2);

        if (splitText.length < 2) {
            if (splitText[0].equals("list")) {
                return new ListCommand();
            } else if (splitText[0].equals("todo")) {
                throw new EmptyDescriptionException();
            } else if (splitText[0].equals("deadline")) {
                throw new EmptyDescriptionException();
            } else if (splitText[0].equals("event")) {
                throw new EmptyDescriptionException();
            } else if (splitText[0].equals("find")) {
                throw new EmptyDescriptionException();
            }
            throw new UnknownCommandException();
        }

        String action = splitText[0].trim();
        String details = splitText.length > 1 ? splitText[1].trim() : "";
        switch (action) {
        case "mark":
            return new MarkTaskCommand(parseIndex(details));
        case "unmark":
            return new UnmarkTaskCommand(parseIndex(details));
        case "delete":
            return new DeleteTaskCommand(parseIndex(details));
        case "todo":
            return new AddTaskCommand(new Todo(details));
        case "deadline":
            return new AddTaskCommand(parseDeadline(details));
        case "event":
            return new AddTaskCommand(parseEvent(details));
        case "find":
            return new FindTaskCommand(details);
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses the task index from the provided details string.
     *
     * @param details The details string containing the task index.
     * @return The zero-based task index.
     * @throws NuggetException If the index is invalid or missing.
     */
    private int parseIndex(String details) throws NuggetException {
        try {
            int index = Integer.parseInt(details.trim()) - 1;
            if ((index < 0) || (index >= tasks.size())) {
                throw new InvalidTaskNumberException();
            }
            return index;
        } catch (NumberFormatException e) {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Parses the user input for a deadline task.
     *
     * @param details The user input string containing the task description and deadline.
     * @return A {@code Deadline} task.
     * @throws NuggetException If the input format is incorrect.
     */
    private Task parseDeadline(String details) throws NuggetException {
        String[] parts = details.split(" /by ", 2);

        if (parts.length < 2) {
            throw new EmptyDescriptionException();
        }
        return new Deadline(parts[0].trim(), parts[1].trim());
    }

    /**
     * Parses the user input for an event task.
     *
     * @param details The user input string containing the task description, start time, and end time.
     * @return An {@code Event} task.
     * @throws NuggetException If the input format is incorrect.
     */
    private Task parseEvent(String details) throws NuggetException {
        String[] parts = details.split(" /from ", 2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException();
        }
        String[] timeParts = parts[1].split(" /to ", 2);
        if (timeParts.length < 2) {
            throw new EmptyDescriptionException();
        }
        return new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
    }
}
