package sammy;

import sammy.command.*;
import sammy.task.Deadline;
import sammy.task.Event;
import sammy.task.Task;
import sammy.task.Todo;;

public class Parser {

    /**
     * Parses the input command and returns the corresponding Command object.
     *
     * @param fullCommand The full command input as a string.
     * @return A Command object that corresponds to the parsed command.
     * @throws SammyException If the command is invalid or if there is an issue during parsing.
     */
    public static Command parse(String fullCommand) throws SammyException {
        assert fullCommand != null && !fullCommand.isEmpty() : "Command cannot be null or empty";

        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
            case "todo":
                return new AddCommand(new Todo(arguments));
            case "deadline":
                String[] deadlineParts = arguments.split(" /by ");
                if (deadlineParts.length < 2) {
                    throw new InvalidCommandException();
                }
                return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]));
            case "event":
                String[] eventParts = arguments.split(" /from ");
                if (eventParts.length < 2) {
                    throw new InvalidCommandException();
                }
                String[] timeParts = eventParts[1].split(" /to ");
                if (timeParts.length < 2) {
                    throw new InvalidCommandException();
                }
                return new AddCommand(new Event(eventParts[0], timeParts[0], timeParts[1]));
            case "list":
                return new ListCommand();
            case "delete":
                return new DeleteCommand(Integer.parseInt(arguments) - 1);
            case "mark":
                return new MarkCommand(Integer.parseInt(arguments) - 1);
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(arguments) - 1);
            case "find":
                return new FindCommand(arguments);
            case "bye":
                return new ExitCommand();
            default:
                throw new InvalidCommandException();
        }
    }

    /**
     * Parses a line of text representing a task and returns the corresponding Task object.
     *
     * @param line The string representation of a task.
     * @return A Task object that corresponds to the parsed task.
     * @throws IllegalArgumentException If the task format is invalid or unrecognized.
     */
    public static Task parseTask(String line) {
        assert line != null && !line.isEmpty() : "Line cannot be null or empty";
        System.out.println("Parsing line: " + line);

        String taskType = line.substring(1,2);
        boolean isDone = line.charAt(4) == 'X';
        String description = line.substring(7);

        Task task = null;
        switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                String[] deadline = description.split("/by");
                if (deadline.length == 1) {
                    throw new IllegalArgumentException("Invalid deadline format: " + line);
                }
                task = new Deadline(deadline[0], deadline[1]);
                break;
            case "E":
                String[] event1 = description.split("/from");
                String[] event2 = event1[1].split("/to");
                if (event1.length == 1) {
                    throw new IllegalArgumentException("Invalid event format: " + line);
                }
                task = new Event(event1[0], event2[0], event2[1]);
                break;
            default:
                throw new IllegalArgumentException("Unrecognized task type: " + taskType);
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Converts a Task object to its string representation.
     *
     * @param task The Task object to be converted.
     * @return The string representation of the given Task.
     */
    public static String taskToString(Task task) {
        assert task != null : "Task cannot be null";
        return task.toString();
    }
}

