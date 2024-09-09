package spiderman;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Command {
    public String todo(String[] input, TaskList tasks) {
        String description = input[0].replaceFirst("todo", "").trim();
        if (description.isEmpty()) {
            return "The description of a todo cannot be empty.";
        }

        return tasks.addTask(new Todo(description));
    }

    public String deadline(String[] input, TaskList tasks) {

        String description = input[0].replaceFirst("deadline", "").trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (description.isEmpty()) {
            return "The description of a deadline cannot be empty.";
        }

        try {
            LocalDate by = LocalDate.parse(input[1]
                    .replaceFirst("by", "").trim(), formatter);
            return tasks.addTask(new Deadline(description, by));
        }
        catch (DateTimeParseException e) {
            return "The date is not in the correct format! It should be YYYY-MM-DD";
        }
        catch (Exception e) {
            return "The stated deadline should have a date";
        }
    }

    public String event(String[] input, TaskList tasks) {
        String description = input[0].replaceFirst("event", "").trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        if (description.isEmpty()) {
            return "The description of an event cannot be empty.";
        }

        String fromString = input[1].replaceFirst("from", "").trim();
        String toString = input[2].replaceFirst("to", "").trim();

        try {
            LocalDateTime from = LocalDateTime.parse(fromString, formatter);
            LocalDateTime to = LocalDateTime.parse(toString, formatter);
            return tasks.addTask(new Event(description, from, to));
        }
        catch (DateTimeParseException e) {
            return "The date and time is not in the correct format! It should be YYYY-MM-DD HH:mm";
        }
        catch (Exception e) {
            return "The from and/or to cannot be empty!";
        }
    }

    public String delete(String[] input, TaskList tasks) {
        int number = Integer.parseInt(input[1]) - 1;
        return tasks.deleteTask(number);
    }

    public String list(TaskList tasks) {
        return tasks.listTasks();
    }

    public String find(String input, TaskList tasks) {
        String keyword = input.replaceFirst("find", "").trim();
        if (keyword.isEmpty()) {
            return "The keyword for find cannot be empty!";
        } else {
            return tasks.findTasks(keyword);
        }
    }

    public String mark(String[] input, TaskList tasks) {
        int number = Integer.parseInt(input[1]) - 1;
        return tasks.markTaskAsDone(number);
    }

    public String unmark(String[] input, TaskList tasks) {
        int number = Integer.parseInt(input[1]) - 1;
        return tasks.markTaskAsNotDone(number);
    }

    public String bye() {
        return "Bye. Hope to see you again soon!";
    }
}
