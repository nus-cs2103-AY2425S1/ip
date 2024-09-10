package xbot.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import xbot.TaskList;
import xbot.XBotException;
import xbot.storage.Storage;
import xbot.task.Deadline;
import xbot.task.Event;
import xbot.task.Task;
import xbot.task.ToDo;
import xbot.ui.Ui;

/**
 * The Parser class provides utility methods to parse user input and tasks
 * in the XBot application. It also handles the processing of user commands.
 */
public class Parser {

    /**
     * Parses a line of text representing a task from storage and returns the corresponding Task object.
     *
     * @param line The line of text to parse.
     * @return The Task object represented by the text, or null if the format is unknown.
     */
    public static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length >= 3) {
            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String description = parts[2].trim();
            switch (type) {
            case "T":
                Task todo = new ToDo(description);
                if (isDone) {
                    todo.setIsDone();
                }
                return todo;
            case "D":
                String deadline = parts[3].trim();
                Task deadlineTask = new Deadline(description, deadline);
                if (isDone) {
                    deadlineTask.setIsDone();
                }
                return deadlineTask;
            case "E":
                String from = parts[3].trim();
                String to = parts[4].trim();
                Task eventTask = new Event(description, from, to);
                if (isDone) {
                    eventTask.setIsDone();
                }
                return eventTask;
            default:
                System.out.println("Unknown task type: " + type);
            }
        }
        return null;
    }

    /**
     * Checks if a given date string is in a valid format.
     *
     * @param date The date string to validate.
     * @return True if the date string matches one of the supported formats, false otherwise.
     */
    public static boolean isValidDateFormat(String date) {
        List<String> formats = new ArrayList<>();
        formats.add("yyyy-MM-dd");
        formats.add("d/M/yyyy");
        formats.add("d/M/yyyy HHmm");

        for (String format : formats) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            try {
                if (format.contains("HHmm")) {
                    LocalDateTime.parse(date, formatter);
                } else {
                    LocalDate.parse(date, formatter);
                }
                return true;
            } catch (DateTimeParseException e) {
                //Do nothing
            }
        }
        return false;
    }

    /**
     * Converts a Task object into a string formatted for saving to a file.
     *
     * @param task The Task object to convert.
     * @return The formatted string representation of the task.
     */
    public static String taskToFileString(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getType() + " | ");
        sb.append(task.isDone() ? "1 | " : "0 | ");
        sb.append(task.getDescription());
        if (task instanceof Deadline) {
            sb.append(" | ").append(((Deadline) task).getBy());
        } else if (task instanceof Event) {
            sb.append(" | ").append(((Event) task).getFrom())
                    .append(" | ").append(((Event) task).getTo());
        }
        return sb.toString();
    }

    /**
     * Converts the deadline to a different date format.
     * The method attempts to parse the deadline using multiple date formats.
     * If successful, it returns the date in a standard format; otherwise, it returns an error message.
     *
     * @param by The original deadline string to be converted.
     * @return The formatted deadline string, or an error message if the conversion fails.
     */
    public static String changeDateFormat(String by) {

        List<String> formats = new ArrayList<>();
        formats.add("yyyy-MM-dd");
        formats.add("d/M/yyyy");
        formats.add("d/M/yyyy HHmm");

        for (String format : formats) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            try {
                if (format.contains("HHmm")) {
                    LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
                    return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"));
                } else {
                    LocalDate date = LocalDate.parse(by, formatter);
                    return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                }
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return "TimeDate cannot be converted to another format :'0";
    }

    /**
     * Processes a user input string and executes the corresponding command.
     *
     * @param input The user's input string.
     * @param list The TaskList object managing the current tasks.
     * @param ui The Ui object for interacting with the user.
     * @param storage The Storage object for saving tasks.
     * @throws XBotException If the input is invalid or an unknown command is given.
     */
    public String processInput(String input, TaskList list, Ui ui, Storage storage) throws XBotException {
        String[] words = input.split("\\s+", 2);
        String command = words[0].toLowerCase();
        String rest = words.length > 1 ? words[1] : "";
        String output;
        switch(command) {
        case "list":
            output = ui.showTaskList(list);
            break;
        case "mark":
            output = list.markDone(rest);
            storage.saveTask(list);
            break;
        case "unmark":
            output = list.markUndone(rest);
            storage.saveTask(list);
            break;
        case "todo":
            if (rest.isEmpty()) {
                throw new XBotException("The description of the todo cannot be empty!");
            }
            output = list.addTodo(rest);
            storage.saveTask(list);
            break;
        case "event":
            if (rest.isEmpty()) {
                throw new XBotException("The description of the event cannot be empty!");
            }
            output = list.addEvent(rest);
            storage.saveTask(list);
            break;
        case "deadline":
            if (rest.isEmpty()) {
                throw new XBotException("The description of the deadline cannot be empty!");
            }
            output = list.addDeadline(rest);
            storage.saveTask(list);
            break;
        case "delete":
            if (rest.isEmpty()) {
                throw new XBotException("The task number to be deleted cannot be empty!");
            }
            output = list.deleteTask(rest);
            storage.saveTask(list);
            break;
        case "find":
            if (rest.isEmpty()) {
                throw new XBotException("There is nothing to find!");
            }
            output = list.findTask(rest);
            break;
        case "bye":
            output = "Hope to see you again!";
            break;
        default:
            throw new XBotException("I'm sorry, but I don't know what that means :-(");
        }
        return output;
    }
}
