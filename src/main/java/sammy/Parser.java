package sammy;

import sammy.command.*;
import sammy.task.Deadline;
import sammy.task.Event;
import sammy.task.Task;
import sammy.task.Todo;;

public class Parser {

    private static final String DEADLINE_DELIMITER = " /by ";
    private static final String EVENT_FROM_DELIMITER = " /from ";
    private static final String EVENT_TO_DELIMITER = " /to ";

    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String LIST_COMMAND = "list";
    private static final String DELETE_COMMAND = "delete";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String FIND_COMMAND = "find";
    private static final String BYE_COMMAND = "bye";

    /**
     * Parses the input command and returns the corresponding Command object.
     *
     * @param fullCommand The full command input as a string.
     * @return A Command object that corresponds to the parsed command.
     * @throws SammyException If the command is invalid or if there is an issue during parsing.
     */
    public static Command parse(String fullCommand) throws SammyException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
        case TODO_COMMAND:
            return new AddCommand(new Todo(arguments));
        case DEADLINE_COMMAND:
            return parseDeadlineCommand(arguments);
        case EVENT_COMMAND:
            return parseEventCommand(arguments);
        case LIST_COMMAND:
            return new ListCommand();
        case DELETE_COMMAND:
            return new DeleteCommand(Integer.parseInt(arguments) - 1);
        case MARK_COMMAND:
            return new MarkCommand(Integer.parseInt(arguments) - 1);
        case UNMARK_COMMAND:
            return new UnmarkCommand(Integer.parseInt(arguments) - 1);
        case FIND_COMMAND:
            return new FindCommand(arguments);
        case BYE_COMMAND:
            return new ExitCommand();
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses a deadline command and returns the corresponding AddCommand object.
     *
     * @param arguments The arguments containing the task description and deadline.
     * @return AddCommand with a Deadline task.
     */
    private static Command parseDeadlineCommand(String arguments) {
        String[] deadlineParts = arguments.split(DEADLINE_DELIMITER);
        return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]));
    }

    /**
     * Parses an event command and returns the corresponding AddCommand object.
     *
     * @param arguments The arguments containing the task description and event times.
     * @return AddCommand with an Event task.
     */
    private static Command parseEventCommand(String arguments) {
        String[] eventParts = arguments.split(EVENT_FROM_DELIMITER);

        String[] timeParts = eventParts[1].split(EVENT_TO_DELIMITER);

        return new AddCommand(new Event(eventParts[0], timeParts[0], timeParts[1]));
    }

    /**
     * Parses a line of text representing a task and returns the corresponding Task object.
     *
     * @param line The string representation of a task.
     * @return A Task object that corresponds to the parsed task.
     * @throws IllegalArgumentException If the task format is invalid or unrecognized.
     */
    public static Task parseTask(String line) {
        System.out.println("Parsing line: " + line);

        String taskType = line.substring(1, 2);
        boolean isDone = line.charAt(4) == 'X';
        String description = line.substring(7);

        Task task = createTaskFromLine(taskType, description);
        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Creates a Task object based on the task type and description.
     *
     * @param taskType The type of task (e.g., "T", "D", "E").
     * @param description The description of the task.
     * @return A Task object that corresponds to the task type.
     * @throws IllegalArgumentException If the task type is unrecognized or format is invalid.
     */
    private static Task createTaskFromLine(String taskType, String description) {
        switch (taskType) {
        case "T":
            return new Todo(description);
        case "D":
            return parseDeadlineTask(description);
        case "E":
            return parseEventTask(description);
        default:
            throw new IllegalArgumentException("Unrecognized task type: " + taskType);
        }
    }

    /**
     * Parses the description of a deadline task.
     *
     * @param description The string containing the task description and deadline.
     * @return A Deadline task.
     */
    private static Task parseDeadlineTask(String description) {
        String[] deadline = description.split("/by");
        return new Deadline(deadline[0], deadline[1]);
    }

    /**
     * Parses the description of an event task.
     *
     * @param description The string containing the task description and event times.
     * @return An Event task.
     */
    private static Task parseEventTask(String description) {
        String[] eventParts = description.split("/from");

        String[] timeParts = eventParts[1].split("/to");

        return new Event(eventParts[0], timeParts[0], timeParts[1]);
    }

    /**
     * Converts a Task object to its string representation.
     *
     * @param task The Task object to be converted.
     * @return The string representation of the given Task.
     */
    public static String taskToString(Task task) {
        return task.toString();
    }
}
