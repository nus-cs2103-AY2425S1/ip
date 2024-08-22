package tina;

import tina.task.Deadline;
import tina.task.Event;
import tina.task.Task;
import tina.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The <code>Parser</code> class handles the parsing of user input commands and task data.
 * It provides methods to interpret commands, convert strings into <code>Task</code> objects,
 * and format dates.
 */
public class Parser {

    /**
     * Parses a task creation command and adds the task to the provided <code>TaskList</code>.
     *
     * @param input The user input command for creating a task.
     * @param tasks The <code>TaskList</code> to which the parsed task will be added.
     * @throws TinaException if the command is invalid or the description is missing.
     */
    private static void parseTask(String input, TaskList tasks) throws TinaException {
        try {
            if (input.startsWith("todo")) {
                String des = input.substring(5);
                tasks.addTask(new Todo(des));
            }
            else if (input.startsWith("deadline")) {
                int byIdx = input.indexOf("/by");
                String des = input.substring(9, byIdx - 1);
                String end = input.substring(byIdx + 4);
                tasks.addTask(new Deadline(des, end));
            }
            else if (input.startsWith("event")) {
                int fromIdx = input.indexOf("/from");
                int toIdx = input.indexOf("/to");
                String des = input.substring(6, fromIdx - 1);
                String start = input.substring(fromIdx + 6, toIdx - 1);
                String end = input.substring(toIdx + 4);
                tasks.addTask(new Event(des, start, end));
            } else {
                throw new TinaException("I have no idea what that means");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new TinaException("Where is your description?");
        }
    }

    /**
     * Parses user input to execute commands like list, mark, unmark, delete, or add a task.
     *
     * @param input The user input command.
     * @param tasks The <code>TaskList</code> on which the command will be executed.
     * @throws TinaException if the command is invalid, the index is missing, or the index format is incorrect.
     */
    public static void parseInput(String input, TaskList tasks) throws TinaException {
        try {
            if (input.equals("list")) {
                tasks.listTask();
            } else if (input.startsWith("mark")) {
                int idx = Integer.parseInt(input.substring(5));
                tasks.markTask(idx);
            } else if (input.startsWith("unmark")) {
                int idx = Integer.parseInt(input.substring(7));
                tasks.unmarkTask(idx);
            } else if (input.startsWith("delete")) {
                int idx = Integer.parseInt(input.substring(7));
                tasks.deleteTask(idx);
            } else {
                parseTask(input, tasks);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new TinaException("What is the index?");
        } catch (NumberFormatException e) {
            throw new TinaException("Enter the index after the space.");
        }
    }

    /**
     * Parses a string representation of a task from storage and converts it into a <code>Task</code> object.
     *
     * @param input The string representation of the task from storage.
     * @return The parsed <code>Task</code> object.
     * @throws TinaException if the task type is invalid.
     */
    public static Task parseLine(String input) {
        char type = input.charAt(0);
        boolean isMark = input.charAt(2) == '1';
        switch(type) {
            case 'T':
                String desT = input.substring(4);
                return new Todo(desT, isMark);
            case 'D':
                String[] parts = input.substring(4).split(" \\| ");
                String desD = parts[0];
                String endD = parts[1];
                return new Deadline(desD, isMark, endD);
            case 'E':
                String[] partsE = input.substring(4).split(" \\| ");
                String desE = partsE[0];
                String start = partsE[1];
                String end = partsE[2];
                return new Event(desE, isMark, start, end);
            default:
                throw new TinaException("Invalid task type: " + type);
        }
    }

    /**
     * Parses a date and time string into a <code>LocalDateTime</code> object to be stored in task.
     *
     * @param input The date and time string in the format "d/M/yyyy HHmm".
     * @return The parsed <code>LocalDateTime</code> object.
     * @throws TinaException if the date and time format is invalid.
     */
    public static LocalDateTime parseDate(String input) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            return LocalDateTime.parse(input, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new TinaException("Invalid date and time format");
        }
    }

    /**
     * Formats a <code>LocalDateTime</code> object into a custom string representation.
     *
     * @param input The <code>LocalDateTime</code> object to be formatted.
     * @return A string of the date and time in the custom format "MMM dd yyyy HH:mm".
     */
    public static String formatDate(LocalDateTime input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return input.format(formatter);
    }
}
