package loafy.parser;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

import java.util.Arrays;

import loafy.command.AddCommand;
import loafy.command.Command;
import loafy.command.DeleteCommand;
import loafy.command.ExitCommand;
import loafy.command.FindCommand;
import loafy.command.ListCommand;
import loafy.command.MarkCommand;
import loafy.loafyexception.LoafyException;
import loafy.task.Deadline;
import loafy.task.Event;
import loafy.task.Task;
import loafy.task.Todo;

/**
 * Represents a parser to handle user commands.
 */
public class Parser {

    /**
     * Parses the user input into a command.
     * Returns the relevant command.
     * If the input is invalid, an error message is shown to inform the user.
     *
     * @param line User input in String format.
     * @return A command corresponding to the user input.
     */
    public static Command parse(String line) throws LoafyException {
        String[] inputArr = line.split(" ");
        Command command;

        switch (inputArr[0]) {
        case "bye":
            command = handleExit(inputArr);
            break;
        case "list":
            command = handleList(inputArr);
            break;
        case "mark":
            // Fallthrough
        case "unmark":
            // Fallthrough
        case "delete":
            command = handleMarkUnmarkDelete(inputArr);
            break;
        case "todo":
            command = handleToDo(inputArr);
            break;
        case "deadline":
            command = handleDeadline(inputArr);
            break;
        case "event":
            command = handleEvent(inputArr);
            break;
        case "find":
            command = handleFind(inputArr);
            break;
        default:
            throw LoafyException.ofInvalidCommand();
        }

        return command;
    }

    /**
     * Returns a substring from the specified range of the word array.
     *
     * @param arr An array of words of the original string.
     * @param startIndex The index of the first word of the substring.
     * @param endIndex The index of the last word of the substring.
     * @return The generated substring.
     */
    public static String joinRange(String[] arr, int startIndex, int endIndex) {
        String[] subArr = Arrays.copyOfRange(arr, startIndex, endIndex);
        return String.join(" ", subArr);
    }

    /**
     * Parses the user input into a LocalDateTime object.
     * Throws a LoafyException if user input has an unexpected format.
     *
     * @param date The user input representing a date.
     * @return The LocalDateTime object.
     */
    public static LocalDateTime parseDate(String date) throws LoafyException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw LoafyException.ofWrongDateFormat();
        }
    }

    /**
     * Returns an exit command.
     * Throws a LoafyException if user input has an unexpected format.
     *
     * @param inputArr An array of words of the user input.
     * @return the exit command.
     */
    public static Command handleExit(String[] inputArr) throws LoafyException {
        if (inputArr.length != 1) {
            throw LoafyException.ofInvalidCommand();
        }

        return new ExitCommand();
    }

    /**
     * Returns a list command.
     * Throws a LoafyException if user input has an unexpected format.
     *
     * @param inputArr An array of words of the user input.
     * @return the list command.
     */
    public static Command handleList(String[] inputArr) throws LoafyException {
        if (inputArr.length != 1) {
            throw LoafyException.ofInvalidCommand();
        }

        return new ListCommand();
    }

    /**
     * Returns a mark or delete command corresponding to the user input.
     * Throws a LoafyException if user input has an unexpected format.
     *
     * @param inputArr An array of words of the user input.
     * @return The mark or delete command.
     */
    public static Command handleMarkUnmarkDelete(String[] inputArr) throws LoafyException {
        if (inputArr.length != 2) {
            throw LoafyException.ofInvalidAction();
        }

        try {
            int taskId = Integer.parseInt(inputArr[1]);
            if (inputArr[0].equals("delete")) {
                return new DeleteCommand(taskId);
            } else {
                boolean isDone = inputArr[0].equals("mark");
                return new MarkCommand(isDone, taskId);
            }
        } catch (NumberFormatException e) {
            throw LoafyException.ofInvalidAction();
        }
    }

    /**
     * Returns a todo command.
     * Throws a LoafyException if user input has an unexpected format.
     *
     * @param inputArr An array of words of the user input.
     * @return the todo command.
     */
    public static Command handleToDo(String[] inputArr) throws LoafyException {
        if (inputArr.length == 1) {
            throw LoafyException.ofEmptyTodo();
        }

        String name = joinRange(inputArr, 1, inputArr.length);
        Task task = new Todo(name);
        return new AddCommand(task);
    }

    /**
     * Returns a deadline command.
     * Throws a LoafyException if user input has an unexpected format.
     *
     * @param inputArr An array of words of the user input.
     * @return the deadline command.
     */
    public static Command handleDeadline(String[] inputArr) throws LoafyException {
        int i = Arrays.asList(inputArr).indexOf("/by");

        if (i == -1) {
            throw LoafyException.ofNoDeadline();
        }

        String name = joinRange(inputArr, 1, i);
        String date = joinRange(inputArr, i + 1, inputArr.length);

        if (name.isEmpty() || date.isEmpty()) {
            throw LoafyException.ofNoDeadline();
        }

        LocalDateTime dateTime = parseDate(date);
        Task task = new Deadline(name, dateTime);
        return new AddCommand(task);
    }

    /**
     * Returns an event command.
     * Throws a LoafyException if user input has an unexpected format.
     *
     * @param inputArr An array of words of the user input.
     * @return the event command.
     */
    public static Command handleEvent(String[] inputArr) throws LoafyException {
        int fromIndex = Arrays.asList(inputArr).indexOf("/from");
        int toIndex = Arrays.asList(inputArr).indexOf("/to");

        if (fromIndex == -1 || toIndex == -1) {
            throw LoafyException.ofNoEventDates();
        }

        String name = joinRange(inputArr, 1, fromIndex);
        String startDateString = joinRange(inputArr, fromIndex + 1, toIndex);
        String endDateString = joinRange(inputArr, toIndex + 1, inputArr.length);

        if (name.isEmpty() || startDateString.isEmpty() || endDateString.isEmpty()) {
            throw LoafyException.ofNoEventDates();
        }

        LocalDateTime startDate = parseDate(startDateString);
        LocalDateTime endDate = parseDate(endDateString);
        Task task = new Event(name, startDate, endDate);
        return new AddCommand(task);
    }

    /**
     * Returns a find command.
     * Throws a LoafyException if user input has an unexpected format.
     *
     * @param inputArr An array of words of the user input.
     * @return the find command.
     */
    public static Command handleFind(String[] inputArr) throws LoafyException {
        if (inputArr.length == 1) {
            throw LoafyException.ofEmptyFind();
        }
        return new FindCommand(inputArr[1]);
    }
}
