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


public class Parser {
    public static Command parse(String line) throws LoafyException {
        String[] arr = line.split(" ");

        if (arr.length == 0) {
            throw LoafyException.ofEmptyInput();
        } else if (line.equals("bye")) {
            return new ExitCommand();
        } else if (line.equals("list")) {
            return new ListCommand();
        } else if (arr[0].equals("mark")
                || arr[0].equals("unmark")
                || arr[0].equals("delete")) {
            if (arr.length != 2) {
                throw LoafyException.ofInvalidAction();
            } else {
                try {
                    int taskId = Integer.parseInt(arr[1]);
                    if (arr[0].equals("delete")) {
                        return new DeleteCommand(taskId);
                    } else {
                        boolean isDone = arr[0].equals("mark");
                        return new MarkCommand(isDone, taskId);
                    }
                } catch (NumberFormatException e) {
                    throw LoafyException.ofInvalidAction();
                }
            }
        } else if (arr[0].equals("todo")) {
            if (arr.length == 1) {
                throw LoafyException.ofEmptyTodo();
            } else {
                String name = joinRange(arr, 1, arr.length);
                Task task = new Todo(name);
                return new AddCommand(task);
            }
        } else if (arr[0].equals("deadline")) {
            int i = Arrays.asList(arr).indexOf("/by");
            if (i == -1) {
                throw LoafyException.ofNoDeadline();
            } else {
                String name = joinRange(arr, 1, i);
                String date = joinRange(arr, i + 1, arr.length);
                if (name.isEmpty() || date.isEmpty()) {
                    throw LoafyException.ofNoDeadline();
                } else {
                    LocalDateTime dateTime = parseDate(date);
                    Task task = new Deadline(name, dateTime);
                    return new AddCommand(task);
                }
            }
        } else if (arr[0].equals("event")) {
            int fromIndex = Arrays.asList(arr).indexOf("/from");
            int toIndex = Arrays.asList(arr).indexOf("/to");
            if (fromIndex == -1 || toIndex == -1) {
                throw LoafyException.ofNoEventDates();
            } else {
                String name = joinRange(arr, 1, fromIndex);
                String startDateString = joinRange(arr, fromIndex + 1, toIndex);
                String endDateString = joinRange(arr, toIndex + 1, arr.length);
                if (name.isEmpty() || startDateString.isEmpty() || endDateString.isEmpty()) {
                    throw LoafyException.ofNoEventDates();
                } else {
                    LocalDateTime startDate = parseDate(startDateString);
                    LocalDateTime endDate = parseDate(endDateString);
                    Task task = new Event(name, startDate, endDate);
                    return new AddCommand(task);
                }
            }
        } else if (arr[0].equals("find")) {
            if (arr.length == 1) {
                throw LoafyException.ofEmptyFind();
            }
            return new FindCommand(arr[1]);
        } else {
            throw LoafyException.ofInvalidCommand();
        }
    }

    public static String joinRange(String[] arr, int startIndex, int endIndex) {
        String[] subArr = Arrays.copyOfRange(arr, startIndex, endIndex);
        return String.join(" ", subArr);
    }

    public static LocalDateTime parseDate(String date) throws LoafyException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw LoafyException.ofWrongDateFormat();
        }
    }
}
