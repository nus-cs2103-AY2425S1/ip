package bottle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bottle.command.Command;
import bottle.command.DeleteCommand;
import bottle.command.addDeadlineTask;
import bottle.command.addEventTask;
import bottle.command.addTodoTask;
import bottle.command.exitCommand;
import bottle.command.listCommand;
import bottle.command.markCommand;
import bottle.command.unMarkCommand;
import bottle.task.Deadline;
import bottle.task.Event;
import bottle.task.Task;
import bottle.task.Todo;

/**
 * The type Parser.
 */
public class Parser {

    /**
     * Parse date time local date time.
     *
     * @param dateTimeStr the date time str
     * @return the local date time
     */
    private static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("OOPS!!! The date format is incorrect. Please use: dd/MM/yyyy HHmm");
        }
    }

    /**
     * Parse command command.
     *
     * @param input the input
     * @return the command
     */
    public Command parseCommand(String input) {
        try {
            if (input.equalsIgnoreCase("bye")) {
                return new exitCommand();
            } else if (input.equalsIgnoreCase("list")) {
                return new listCommand();
            } else if (input.startsWith("mark ")) {
                String taskIndexStr = input.substring(5);
                int taskIndex = Integer.parseInt(taskIndexStr) - 1;
                return new markCommand(taskIndex);
            } else if (input.startsWith("unmark ")) {
                String taskIndexStr = input.substring(7);
                int taskIndex = Integer.parseInt(taskIndexStr) - 1;
                return new unMarkCommand(taskIndex);
            } else if (input.startsWith("todo ")) {
                return new addTodoTask(input.substring(5));
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.split(" /by ");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("OOPS!!! The deadline format is incorrect. Use: deadline <description> /by <dd/MM/yyyy HHmm>");
                }
                String description = parts[0];
                LocalDateTime by = parseDateTime(parts[1]);
                return new addDeadlineTask(description, by);
            } else if (input.startsWith("event ")) {
                String[] parts = input.split(" /from | /to ");
                if (parts.length != 3) {
                    throw new IllegalArgumentException("OOPS!!! The event format is incorrect. Use: event <description> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>");
                }
                String description = parts[0];
                LocalDateTime from = parseDateTime(parts[1]);
                LocalDateTime to = parseDateTime(parts[2]);
                return new addEventTask(description, from, to);
            } else if (input.startsWith("delete ")) {
                String indexStr = input.substring(7);
                int taskIndex = Integer.parseInt(indexStr) - 1;
                return new DeleteCommand(taskIndex);
            } else if (input.startsWith("find ")) {
                String filterString = input.substring(5);
                return new findCommand(filterString);
            } else {
                throw new RuntimeException("OOPS!!! Something went wrong.");
            }

        } catch (RuntimeException e) {
            System.out.println("Error parsing input" + e.getMessage());
            return new invalidCommand();
        }
    }

    /**
     * Parse task task.
     *
     * @param input the input
     * @return the task
     */
    public Task parseTask(String input) {
        String[] parts = input.split("\\|");
        Task task;
        try {
            task = switch (parts[0]) {
                case "T " -> new Todo(parts[2]);
                case "D " -> new Deadline(parts[2], parseDateTime(parts[3]));
                case "E " -> new Event(parts[2], parseDateTime(parts[3]), parseDateTime(parts[4]));
                default -> throw new IllegalArgumentException("Wrong input format");
            };
            if (parts[1].equals(" 1 ")) {
                task.mark();
            } else if (parts[1].equals(" 0 ")) {
                task.unMark();
            } else {
                throw new IllegalArgumentException("Wrong isMarked input format");
            }
            return task;
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("Wrong input format" + e.getMessage());
            return null;
        }
    }
}

