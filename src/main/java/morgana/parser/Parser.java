package morgana.parser;

import morgana.exceptions.MorganaException;
import morgana.commands.*;
import morgana.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String line) throws MorganaException {
        String[] input = line.trim().split(" ", 2);
        String cmd = input[0];
        String args = input.length > 1 ? input[1] : "";

        return switch (cmd) {
            case "list" ->  new ListCommand();
            case "mark" -> new MarkCommand(args);
            case "unmark" -> new UnmarkCommand(args);
            case "delete" -> new DeleteCommand(args);
            case "todo" -> new TodoCommand(args);
            case "deadline" -> new DeadlineCommand(args);
            case "event" -> new EventCommand(args);
            case "bye" -> new ByeCommand();
            default -> throw new MorganaException("Unknown command: %s".formatted(cmd));
        };
    }

    public static int parseTaskIndex(String args, TaskList tasks) throws MorganaException {
        if (args.isEmpty()) {
            throw new MorganaException("Please specify a task number.");
        }
        try {
            int index = Integer.parseInt(args.trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            return index;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new MorganaException("Please specify a valid task number.");
        }
    }

    public static LocalDateTime parseDateTime(String dateTime) throws MorganaException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new MorganaException("Invalid date/time format. Please use 'yyyy-MM-dd HHmm'.");
        }
    }

    public static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("X");
        String description = parts[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        Task task = switch (type) {
            case "T" -> new Todo(description);
            case "D" -> new Deadline(description, LocalDateTime.parse(parts[3], formatter));
            case "E" -> {
                LocalDateTime start = LocalDateTime.parse(parts[3], formatter);
                LocalDateTime end = LocalDateTime.parse(parts[4], formatter);
                yield new Event(description, start, end);
            }
            default -> null;
        };

        if (task != null) {
            task.markAsDone(isDone);
        }
        return task;
    }
}
