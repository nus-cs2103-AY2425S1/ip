package sammy;

import sammy.command.AddCommand;
import sammy.command.DeleteCommand;
import sammy.command.ExitCommand;
import sammy.command.ListCommand;
import sammy.task.Deadline;
import sammy.task.Event;
import sammy.task.Todo;;

public class Parser {
    public static Command parse(String fullCommand) throws SammyException {
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
            case "bye":
                return new ExitCommand();
            default:
                throw new InvalidCommandException();
        }
    }

    public static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task = null;
        switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length < 4) {
                    throw new IllegalArgumentException("Invalid deadline format: " + line);
                }
                task = new Deadline(description, parts[3].trim());
                break;
            case "E":
                if (parts.length < 5) {
                    throw new IllegalArgumentException("Invalid event format: " + line);
                }
                task = new Event(description, parts[3].trim(), parts[4].trim());
                break;
            default:
                throw new IllegalArgumentException("Unrecognized task type: " + taskType);
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }

    public static String taskToString(Task task) {
        return task.toString();
    }
}
