import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {
    static Command parse(String line) throws LoafyException {
        String[] arr = line.split(" ");
        if (arr.length == 0) {
            throw LoafyException.emptyInput();
        } else if (line.equals("bye")) {
            return new ExitCommand();
        } else if (line.equals("list")) {
            return new ListCommand();
        } else if (arr[0].equals("mark")
                || arr[0].equals("unmark")
                || arr[0].equals("delete")) {
            if (arr.length != 2) {
                throw LoafyException.invalidAction();
            } else {
                try {
                    int taskId = Integer.parseInt(arr[1]);
                    String msg;
                    if (arr[0].equals("delete")) {
                        return new DeleteCommand(taskId);
                    } else {
                        // set mark to true if "mark", false if "unmark";
                        boolean mark = arr[0].equals("mark");
                        return new MarkCommand(mark, taskId);
                    }
                } catch (NumberFormatException e) {
                    throw LoafyException.invalidAction();
                }
            }
        } else if (arr[0].equals("todo")) {
            if (arr.length == 1) {
                throw LoafyException.emptyTodo();
            } else {
                String name = joinRange(arr, 1, arr.length);
                Task task = new Todo(name);
                return new AddCommand(task);
            }
        } else if (arr[0].equals("deadline")) {
            int i = Arrays.asList(arr).indexOf("/by");
            if (i == -1) {
                throw LoafyException.noDeadline();
            } else {
                String name = joinRange(arr, 1, i);
                String date = joinRange(arr, i + 1, arr.length);
                if (name.isEmpty() || date.isEmpty()) {
                    throw LoafyException.noDeadline();
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
                throw LoafyException.noEventDates();
            } else {
                String name = joinRange(arr, 1, fromIndex);
                String from = joinRange(arr, fromIndex + 1, toIndex);
                String to = joinRange(arr, toIndex + 1, arr.length);
                if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    throw LoafyException.noEventDates();
                } else {
                    LocalDateTime fromDateTime = parseDate(from);
                    LocalDateTime toDateTime = parseDate(to);
                    Task task = new Event(name, fromDateTime, toDateTime);
                    return new AddCommand(task);
                }
            }
        } else {
            throw LoafyException.invalidCommand();
        }
    }

    static String joinRange(String[] arr, int start, int end) {
        String[] subArr = Arrays.copyOfRange(arr, start, end);
        return String.join(" ", subArr);
    }

    static LocalDateTime parseDate(String date) throws LoafyException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(date, dtf);
        } catch (DateTimeParseException e) {
            throw LoafyException.wrongDateFormat();
        }
    }
}
