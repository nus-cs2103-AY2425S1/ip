package skywalker.parser;

import skywalker.command.*;
import skywalker.task.Deadline;
import skywalker.task.Event;
import skywalker.task.Task;
import skywalker.task.Todo;

/**
 * The Parser class is reponsible of receiving the user's input
 * and to convert it to commands or tasks that can be executed by the app.
 */
public class Parser {

    /**
     * This method looks into what type of command the user input
     * and then processes the remaining part of the input
     * @param fullCommand The string inputted by the user
     * @return The command object corresponding to the users' input
     * @throws Exception If the command is unknown/invalid.
     */
    public static Command parse(String fullCommand) throws Exception {
        String[] words = fullCommand.split(" ", 2);
        String commandWord = words[0];
        String arguments = words.length > 1 ? words[1] : "";

        switch (commandWord) {
            case "find":
                String keyword = arguments.trim();  // Extract and trim the keyword
                return new FindCommand(keyword);
            case "todo":
                return new AddCommand(new Todo(arguments));
            case "deadline":
                String[] parts = arguments.split("/by");
                return new AddCommand(new Deadline(parts[0].trim(), parts[1].trim()));
            case "event":
                String[] eventParts = arguments.split("/from|/to");
                return new AddCommand(new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim()));
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "delete":
                return new DeleteCommand(Integer.parseInt(arguments) - 1);
            case "mark":
                return new MarkCommand(Integer.parseInt(arguments) - 1, true);
            case "unmark":
                return new MarkCommand(Integer.parseInt(arguments) - 1, false);
            default:
                throw new Exception("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses a task string from the storage file and converts it into a Task object
     * The task string is expected to be in the format used for storage
     *
     * @param fileString The string that represent the task, stored in the file.
     * @return The Task object corresponding to the file string
     */
    public static Task parseTaskFromFileString(String fileString) {
        String[] parts = fileString.split(" \\| ");
        String taskTypeCode = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskTypeCode) {
            case "T":
                Todo todo = new Todo(description);
                if (isDone) todo.markDone();
                return todo;
            case "D":
                String by = parts[3];
                Deadline deadline = new Deadline(description, by);
                if (isDone) deadline.markDone();
                return deadline;
            case "E":
                String from = parts[3];
                String to = parts[4];
                Event event = new Event(description, from, to);
                if (isDone) event.markDone();
                return event;
            default:
                throw new IllegalArgumentException("Invalid task type found in file.");
        }
    }

    public static String taskToFileString(Task task) {
        String status = task.isDone ? "1" : "0";
        if (task instanceof Todo) {
            return "T | " + status + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + status + " | " + deadline.description + " | " + deadline.by;
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + status + " | " + event.description + " | " + event.from + " | " + event.to;
        }
        return "";
    }
}
