package loafy.parser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
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
     * Accepts both day of week and d/M/yyyy, with optional HHmm as input.
     *
     * @param dateTime The user input representing date and time.
     * @return The LocalDateTime object.
     */
    static LocalDateTime parseDateTime(String dateTime) throws LoafyException {
        assert ! dateTime.isEmpty();

        String[] dateTimeArr = dateTime.split(" ");
        String date = dateTimeArr[0];
        LocalDate dateObj = parseDate(date);
        LocalTime timeObj;

        if (dateTimeArr.length == 2) {
            String time = dateTimeArr[1];
            timeObj = parseTime(time);
        } else {
            timeObj = LocalTime.of(23, 59, 59);
        }
        return dateObj.atTime(timeObj);
    }

    private static LocalDate parseDate(String date) throws LoafyException {
        // handle date in day of week format
        try {
            LocalDate now = LocalDate.now();
            int day = DayOfWeek.valueOf(date.toUpperCase()).getValue();
            int today = now.getDayOfWeek().getValue();
            return now.plusDays(day - today);
        } catch (IllegalArgumentException e) {
            // handle date in d/M/yyyy format
            try {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                return LocalDate.parse(date, dateFormatter);
            } catch (DateTimeParseException exception) {
                throw LoafyException.ofWrongDateFormat();
            }
        }
    }

    private static LocalTime parseTime(String time) throws LoafyException {
        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
            return LocalTime.parse(time, timeFormatter);
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
    private static Command handleExit(String[] inputArr) throws LoafyException {
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
    private static Command handleList(String[] inputArr) throws LoafyException {
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
    private static Command handleMarkUnmarkDelete(String[] inputArr) throws LoafyException {
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
    private static Command handleToDo(String[] inputArr) throws LoafyException {
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
    private static Command handleDeadline(String[] inputArr) throws LoafyException {
        int i = Arrays.asList(inputArr).indexOf("/by");

        if (i == -1) {
            throw LoafyException.ofNoDeadline();
        }

        String name = joinRange(inputArr, 1, i);
        String dateTime = joinRange(inputArr, i + 1, inputArr.length);

        if (name.isEmpty() || dateTime.isEmpty()) {
            throw LoafyException.ofNoDeadline();
        }

        LocalDateTime dateTimeObj = parseDateTime(dateTime);
        Task task = new Deadline(name, dateTimeObj);
        return new AddCommand(task);
    }

    /**
     * Returns an event command.
     * Throws a LoafyException if user input has an unexpected format.
     *
     * @param inputArr An array of words of the user input.
     * @return the event command.
     */
    private static Command handleEvent(String[] inputArr) throws LoafyException {
        int fromIndex = Arrays.asList(inputArr).indexOf("/from");
        int toIndex = Arrays.asList(inputArr).indexOf("/to");

        if (fromIndex == -1 || toIndex == -1) {
            throw LoafyException.ofNoEventDates();
        }

        if (fromIndex > toIndex) {
            throw LoafyException.ofWrongOrder();
        }

        String name = joinRange(inputArr, 1, fromIndex);
        String startDateString = joinRange(inputArr, fromIndex + 1, toIndex);
        String endDateString = joinRange(inputArr, toIndex + 1, inputArr.length);

        if (name.isEmpty() || startDateString.isEmpty() || endDateString.isEmpty()) {
            throw LoafyException.ofNoEventDates();
        }

        LocalDateTime startDate = parseDateTime(startDateString);
        LocalDateTime endDate = parseDateTime(endDateString);
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
    private static Command handleFind(String[] inputArr) throws LoafyException {
        if (inputArr.length == 1) {
            throw LoafyException.ofEmptyFind();
        }
        return new FindCommand(inputArr[1]);
    }
}
