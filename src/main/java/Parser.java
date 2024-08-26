import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parseCommand(String commandStr) throws KilluaException {
        String[] parts = commandStr.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        if (arguments.isEmpty() && !commandWord.equals("list") && !commandWord.equals("bye")) {
            throw new KilluaException("Arguments cannot be empty for command: " + commandWord);
        }

        switch (commandWord) {
        case "todo":
            return new AddCommand(new Todo(arguments));
        case "deadline":
            return new AddCommand(parseDeadline(arguments));
        case "event":
            return new AddCommand(parseEvent(arguments));
        case "delete":
            return new DeleteCommand(Parser.parseIndex(arguments));
        case "mark":
            return new MarkCommand(Parser.parseIndex(arguments));
        case "unmark":
            return new UnmarkCommand(Parser.parseIndex(arguments));
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "on":
            return new OnCommand(arguments);
        default:
            throw new KilluaException("Unknown command: " + commandWord);
        }
    }

    public static Deadline parseDeadline(String arguments) throws KilluaException {
        try {
            String[] parts = arguments.split(" /by ");
            String description = parts[0].strip();
            String dateTimeString = parts[1].strip();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter);
                return new Deadline(description, dateTime);
            } catch (DateTimeParseException e) {
                LocalDate date = LocalDate.parse(dateTimeString, dateFormatter);
                return new Deadline(description, date);
            }
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new KilluaException("Please use the correct format for deadlines: deadline <description> /by <yyyy-mm-dd> OR deadline <description> /by <yyyy-mm-dd hh:mm>");
        }
    }

    public static Event parseEvent(String arguments) throws KilluaException {
        try {
            String[] parts = arguments.split(" /from ");
            String description = parts[0].strip();
            String[] dateStrings = parts[1].split(" /to ");

            String fromDateTimeString = dateStrings[0].strip();
            String toDateTimeString = dateStrings[1].strip();

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try {
                LocalDateTime fromDateTime = LocalDateTime.parse(fromDateTimeString, dateTimeFormatter);
                LocalDateTime toDateTime = LocalDateTime.parse(toDateTimeString, dateTimeFormatter);
                return new Event(description, fromDateTime, toDateTime);
            } catch (DateTimeParseException e) {
                LocalDate fromDate = LocalDate.parse(fromDateTimeString, dateFormatter);
                LocalDate toDate = LocalDate.parse(toDateTimeString, dateFormatter);
                return new Event(description, fromDate, toDate);
            }
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new KilluaException("Please use the correct format for events: event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd> OR event <description> /from <yyyy-mm-dd hh:mm> /to <yyyy-mm-dd hh:mm>");
        }
    }

    public static int parseIndex(String argument) throws KilluaException {
        try {
            return Integer.parseInt(argument.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new KilluaException("Please provide a valid task number!");
        } catch (IndexOutOfBoundsException e) {
            throw new KilluaException("Task " + argument + " not found!");
        }
    }


    public static Task parseTask(String line) {
        char taskType = line.charAt(0);
        boolean isDone = line.charAt(4) == '1';
        String argument = line.substring(8);

        Task task;

        if (taskType == 'T') {
            task = new Todo(argument);
        } else if (taskType == 'D') {
            String[] strs = argument.split("\\|", 2);
            task = getDeadline(strs[0].strip(), strs[1].strip());
        } else if (taskType == 'E') {
            String[] strs = argument.split("\\|", 3);
            task = getEvent(strs[0].strip(), strs[1].strip(), strs[2].strip());
        } else {
            throw new IllegalArgumentException("Unknown task type: " + taskType);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    private static Task getDeadline(String description, String dateTimeString) {
        Task deadline;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter);
            deadline = new Deadline(description, dateTime);
        } catch (DateTimeParseException e1) {
            try {
                LocalDate date = LocalDate.parse(dateTimeString, dateFormatter);
                deadline = new Deadline(description, date);
            } catch (DateTimeParseException e2) {
                throw new IllegalArgumentException("Invalid date format: " + dateTimeString);
            }
        }

        return deadline;
    }

    private static Task getEvent(String description, String dateTimeStringFrom, String dateTimeStringTo) {
        Task event;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(dateTimeStringFrom, dateTimeFormatter);
            LocalDateTime toDateTime = LocalDateTime.parse(dateTimeStringTo, dateTimeFormatter);
            event = new Event(description, fromDateTime, toDateTime);
        } catch (DateTimeParseException e1) {
            try {
                LocalDate fromDate = LocalDate.parse(dateTimeStringFrom, dateFormatter);
                LocalDate toDate = LocalDate.parse(dateTimeStringTo, dateFormatter);
                event = new Event(description, fromDate, toDate);
            } catch (DateTimeParseException e2) {
                throw new IllegalArgumentException("Invalid date format: from '" + dateTimeStringFrom + "' to '" + dateTimeStringTo + "'");
            }
        }

        return event;
    }
}

