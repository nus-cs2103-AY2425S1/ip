package krona.parser;

import krona.command.AddCommand;
import krona.command.Command;
import krona.command.DeleteCommand;
import krona.command.ExitCommand;
import krona.command.FindCommand;
import krona.command.HelpCommand;
import krona.command.InvalidCommand;
import krona.command.ListCommand;
import krona.command.MarkCommand;
import krona.command.UnmarkCommand;
import krona.task.Deadline;
import krona.task.Event;
import krona.task.Task;
import krona.task.ToDo;

/**
 * Handles the parsing of user input and task data for the Krona chatbot.
 */
public class Parser {

    /**
     * Parses the user's input command and returns the corresponding Command object.
     *
     * @param input The user input as a string.
     * @return The Command object corresponding to the user input.
     */
    public static Command parse(String input) {
        String[] words = input.split(" ", 2);

        switch (words[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "help":
            return new HelpCommand();
        case "todo":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                return new InvalidCommand("The description of a todo cannot be empty.");
            }
            return new AddCommand(new ToDo(words[1].trim()));
        case "deadline":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                return new InvalidCommand("The description and time of a deadline cannot be empty.");
            }
            String[] deadlineParts = words[1].split("/by ", 2);
            if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                return new InvalidCommand("The time of a deadline cannot be empty.");
            }
            return new AddCommand(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
        case "event":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                return new InvalidCommand("The description and times of an event cannot be empty.");
            }
            String[] eventParts = words[1].split("/from ", 2);
            if (eventParts.length < 2 || eventParts[1].trim().isEmpty()) {
                return new InvalidCommand("The start time of an event cannot be empty.");
            }
            String[] timeParts = eventParts[1].split("/to ", 2);
            if (timeParts.length < 2 || timeParts[1].trim().isEmpty()) {
                return new InvalidCommand("The end time of an event cannot be empty.");
            }
            return new AddCommand(new Event(eventParts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
        case "mark":
            return new MarkCommand(Integer.parseInt(words[1]) - 1);
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(words[1]) - 1);
        case "delete":
            return new DeleteCommand(Integer.parseInt(words[1]) - 1);
        case "find":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                return new InvalidCommand("The search keyword cannot be empty.");
            }
            return new FindCommand(words[1].trim());
        default:
            return new InvalidCommand("Invalid Command! Use \"help\" to see available commands");
        }
    }

    /**
     * Parses a string representing a task from the storage file and converts it into a Task object.
     *
     * @param line The string representing a task in the storage file.
     * @return The Task object parsed from the string, or null if parsing fails.
     */
    public static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        Task task = null;

        switch (parts[0]) {
        case "T":
            task = new ToDo(parts[2]);
            break;
        case "D":
            task = new Deadline(parts[2], parts[3]);
            break;
        case "E":
            String[] times = parts[3].split(" to ");
            task = new Event(parts[2], times[0], times[1]);
            break;
        default:
            System.out.println("Unknown task: " + parts[0]);
        }

        if (task != null && parts[1].equals("1")) {
            task.markDone();
        }

        return task;
    }

    /**
     * Converts a Task object into a string format suitable for storage in a file.
     *
     * @param task The Task object to be converted.
     * @return The string representation of the Task, formatted for storage.
     */
    public static String taskToString(Task task) {
        String type = task instanceof ToDo ? "T"
                : task instanceof Deadline ? "D" : "E";
        String status = task.isDone() ? "1" : "0";
        String extra = "";

        if (task instanceof Deadline) {
            extra = " | " + ((Deadline) task).getDateTime();
        } else if (task instanceof Event) {
            extra = " | " + ((Event) task).getStartDateTime() + " to " + ((Event) task).getEndDateTime();
        }

        return type + " | " + status + " | " + task.getDescription() + extra;
    }
}
