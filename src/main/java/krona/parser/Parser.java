package krona.parser;

import krona.command.*;
import krona.task.Deadline;
import krona.task.Event;
import krona.task.Task;
import krona.task.ToDo;

public class Parser {

    public static Command parse(String input) {
        String[] words = input.split(" ", 2);
        switch (words[0]) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "todo":
                return new AddCommand(new ToDo(words[1]));
            case "deadline":
                String[] deadlineParts = words[1].split("/by ", 2);
                return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]));
            case "event":
                String[] eventParts = words[1].split("/from ", 2);
                String[] timeParts = eventParts[1].split("/to ", 2);
                return new AddCommand(new Event(eventParts[0], timeParts[0], timeParts[1]));
            case "mark":
                return new MarkCommand(Integer.parseInt(words[1]) - 1);
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(words[1]) - 1);
            case "delete":
                return new DeleteCommand(Integer.parseInt(words[1]) - 1);
            default:
                return new InvalidCommand();
        }
    }

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
        }

        if (task != null && parts[1].equals("1")) {
            task.markDone();
        }

        return task;
    }

    public static String taskToString(Task task) {
        String type = task instanceof ToDo ? "T" :
                task instanceof Deadline ? "D" : "E";
        String status = task.isDone ? "1" : "0";
        String extra = "";

        if (task instanceof Deadline) {
            extra = " | " + ((Deadline) task).getDateTime();
        } else if (task instanceof Event) {
            extra = " | " + ((Event) task).getStartDateTime() + " to " + ((Event) task).getEndDateTime();
        }

        return type + " | " + status + " | " + task.getDescription() + extra;
    }
}