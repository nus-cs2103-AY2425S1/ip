package mgtow.util;

import mgtow.task.Deadline;
import mgtow.task.Event;
import mgtow.task.Task;
import mgtow.task.ToDo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String[] parseCommand(String fullCommand) {
        return fullCommand.split(" ", 2);
    }

    public static Task createTask(String[] commandParts) throws InvalidTaskException {
        if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
            throw new InvalidTaskException("What you want me to add?");
        }

        switch (commandParts[0]) {
            case "todo":
                return new ToDo(commandParts[1]);
            case "deadline":
                String[] deadlineParts = commandParts[1].split(" /by ");
                if (deadlineParts.length != 2) {
                    throw new InvalidTaskException("By when? Try again");
                }
                if (deadlineParts[0].trim().isEmpty()) {
                    throw new InvalidTaskException("What you want me to add?");
                }
                if (deadlineParts[1].trim().isEmpty()) {
                    throw new InvalidTaskException("By when? Try again");
                }
                return new Deadline(deadlineParts[0], deadlineParts[1]);
            case "event":
                String[] eventParts = commandParts[1].split(" /from ");
                if (eventParts.length != 2) {
                    throw new InvalidTaskException("Start when? Try again");
                }
                if (eventParts[0].trim().isEmpty()) {
                    throw new InvalidTaskException("What you want me to add?");
                }
                String[] timeParts = eventParts[1].split(" /to ");
                if (timeParts.length != 2) {
                    throw new InvalidTaskException("Till when? Try again");
                }
                if (timeParts[0].trim().isEmpty()) {
                    throw new InvalidTaskException("Start when? Try again");
                }
                if (timeParts[1].trim().isEmpty()) {
                    throw new InvalidTaskException("Till when? Try again");
                }
                return new Event(eventParts[0], timeParts[0], timeParts[1]);
            default:
                throw new InvalidTaskException("Unknown command: " + commandParts[0]);
        }
    }

    public static Task parseTaskFromStorage(String line) throws InvalidTaskException {
        String[] parts = line.split("\\|");
        if (parts.length < 3) {
            throw new InvalidTaskException("Invalid task format in storage");
        }
        Task task;
        switch (parts[0]) {
            case "T":
                task = new ToDo(parts[2]);
                break;
            case "D":
                if (parts.length < 4) {
                    throw new InvalidTaskException("Invalid deadline format in storage");
                }
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                if (parts.length < 5) {
                    throw new InvalidTaskException("Invalid event format in storage");
                }
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            default:
                throw new InvalidTaskException("Unknown task type: " + parts[0]);
        }
        if (parts[1].equals("1")) {
            task.markDone();
        }
        return task;
    }

    public static String taskToStorageString(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getType()).append("|");
        sb.append(task.getStatus().equals("X") ? "1" : "0").append("|");
        sb.append(task.getDesc());
        if (task instanceof Deadline) {
            sb.append("|").append(((Deadline) task).getDeadline());
        } else if (task instanceof Event) {
            sb.append("|").append(((Event) task).getStart());
            sb.append("|").append(((Event) task).getEnd());
        }
        return sb.toString();
    }

    public static LocalDate parseDate(String dateStr) {
        return LocalDate.parse(dateStr, DATE_FORMAT);
    }
}