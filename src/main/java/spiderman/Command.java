package spiderman;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Command {
    public void todo(String[] input, TaskList tasks) {
        String description = input[0].replaceFirst("todo", "").trim();
        if (description.isEmpty()) {
            System.out.println("The description of a todo cannot be empty.");
            return;
        }

        tasks.addTask(new Todo(description));
    }

    public void deadline(String[] input, TaskList tasks) {

        String description = input[0].replaceFirst("deadline", "").trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (description.isEmpty()) {
            System.out.println("The description of a deadline cannot be empty.");
            return;
        }

        try {
            LocalDate by = LocalDate.parse(input[1]
                    .replaceFirst("by", "").trim(), formatter);
            tasks.addTask(new Deadline(description, by));
        }
        catch (DateTimeParseException e) {
            System.out.println("The date is not in the correct format!");
        }
        catch (Exception e) {
            System.out.println("The stated deadline should have a date");
        }
    }

    public void event(String[] input, TaskList tasks) {
        String description = input[0].replaceFirst("event", "").trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        if (description.isEmpty()) {
            System.out.println("The description of an event cannot be empty.");
            return;
        }

        String fromString = input[1].replaceFirst("from", "").trim();
        String toString = input[2].replaceFirst("to", "").trim();

        try {
            LocalDateTime from = LocalDateTime.parse(fromString, formatter);
            LocalDateTime to = LocalDateTime.parse(toString, formatter);
            tasks.addTask(new Event(description, from, to));
        }
        catch (DateTimeParseException e) {
            System.out.println("The date and time is not in the correct format!");
        }
        catch (Exception e) {
            System.out.println("The from and/or to cannot be empty!");
        }
    }

    public void delete(String[] input, TaskList tasks) {
        int number = Integer.parseInt(input[1]) - 1;
        tasks.deleteTask(number);
    }

    public void list(TaskList tasks) {
        tasks.listTasks();
    }

    public void find(String input, TaskList tasks) {
        String keyword = input.replaceFirst("find", "").trim();
        if (keyword.isEmpty()) {
            System.out.println("The keyword for find cannot be empty!");
        } else {
            tasks.findTasks(keyword);
        }
    }

    public void mark(String[] input, TaskList tasks) {
        int number = Integer.parseInt(input[1]) - 1;
        tasks.markTaskAsDone(number);
    }

    public void unmark(String[] input, TaskList tasks) {
        int number = Integer.parseInt(input[1]) - 1;
        tasks.markTaskAsNotDone(number);
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
