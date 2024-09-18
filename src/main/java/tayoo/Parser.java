package tayoo;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tayoo.command.AddTaskCommand;
import tayoo.command.Command;
import tayoo.command.CommandType;
import tayoo.command.DeleteAllCommand;
import tayoo.command.DeleteTaskCommand;
import tayoo.command.ExitCommand;
import tayoo.command.FindCommand;
import tayoo.command.ListCommand;
import tayoo.command.MarkTaskCommand;
import tayoo.exception.TayooException;
import tayoo.tasks.Deadline;
import tayoo.tasks.Event;
import tayoo.tasks.Task;
import tayoo.tasks.ToDo;

/**
 * The Parser class contains all the methods related to parsing. This includes parsing user commands, dates, time and
 * text input. All methods within this class should be static.
 */
public class Parser {

    /**
     * Parses the user's input. The case of the input command is arbitrary as all commands are automatically
     * converted to upper case.
     *
     * @param command The user's input in a String format. The command should start with a substring representing the
     * type of command.
     * @return An object that is a subclass of the Command class.
     * @throws TayooException if there is an error when parsing a command, either because the command is not supported,
     * or the format of the command is wrong
     */
    public static Command parseCommand(String command) throws TayooException {
        //@@author prave1n-reused
        String[] inputParts = command.split(" ", 2);
        CommandType commandType = parseCommandType(inputParts[0]);

        switch (commandType) {
        case TODO:
            return parseToDo(inputParts);
        case DEADLINE:
            return parseDeadline(inputParts);
        case EVENT:
            return parseEvent(inputParts);
        case MARK:
            return parseMark(inputParts, true);
        case UNMARK:
            return parseMark(inputParts, false);
        case FIND:
            return parseFind(inputParts);
        case DELETE:
            return parseDelete(inputParts);
        case LIST:
            return new ListCommand();
        case EXIT:
            return new ExitCommand();
        default:
            throw new TayooException("Invalid task type");
        }
    }

    private static Command parseToDo(String[] inputParts) {
        assert inputParts.length == 2: "Todo command must have title";

        return new AddTaskCommand(new ToDo(inputParts[1]));
    }

    private static Command parseDeadline(String[] inputParts) {
        //@@author prave1n-reused
        assert inputParts.length == 2: "Deadline command must have title and deadline";

        String[] deadlineParts = inputParts[1].split("(?i)/by", 2);

        assert deadlineParts.length == 2 : "Input should have title and deadline";

        String title = deadlineParts[0].trim();
        String deadline = deadlineParts[1].trim();
        return new AddTaskCommand(new Deadline(title, deadline));
    }

    private static Command parseEvent(String[] inputParts) {
        //@@author prave1n-reused
        assert inputParts.length == 2: "Event command must have title, from and to";

        String[] eventParts = inputParts[1].split("(?i)/to|/from", 3);

        assert eventParts.length == 3 : "Input should have title, start and end";

        String title = eventParts[0].trim();
        String start = eventParts[1].trim();
        String end = eventParts[2].trim();
        return new AddTaskCommand(new Event(title, start, end));
    }

    private static Command parseDelete(String[] inputParts) throws TayooException{
        assert inputParts.length == 2: "Delete command should be followed be either an index or \"all\"";

        String[] deleteIndicesArray = inputParts[1].split("\\s");

        if (deleteIndicesArray[0].equalsIgnoreCase("ALL")) {
            if (deleteIndicesArray.length == 1) {
                return new DeleteAllCommand();
            } else {
                throw new TayooException("Unexpected input after Delete All command");
            }
        }

        List<Integer> deleteIndicesList= new ArrayList<>();

        for (String index : deleteIndicesArray) {
            try {
                deleteIndicesList.add(Integer.parseInt(index));
            } catch (NumberFormatException e) {
                throw new TayooException("Please provide only integers to delete command");
            }
        }

        return new DeleteTaskCommand(deleteIndicesList);
    }
    private static Command parseMark(String[] inputParts, boolean isComplete) throws TayooException{
        assert inputParts.length == 2: "Mark command must have one or more indices";

        String[] markIndicesArray = inputParts[1].split("\\s");
        List<Integer> markIndicesList= new ArrayList<>();

        for (String index : markIndicesArray) {
            try {
                markIndicesList.add(Integer.parseInt(index));
            } catch (NumberFormatException e) {
                throw new TayooException("Please provide only integers to mark command");
            }
        }

        return new MarkTaskCommand(markIndicesList, isComplete);
    }

    private static Command parseFind(String[] inputParts) {
        assert inputParts.length == 2 : "Find command must include substring to look for";

        return new FindCommand(inputParts[1]);
    }


    /**
     * Parses the task after it is read from the tasklist.txt, then returns the parsed task as a Task object. The parser
     * splits the input string using the '|' char as a delimiter between sections of the parsed string. Depending on the
     * type of task parsed, the scanner will scan through a different number of segments depending on the .txt
     * representation of the task
     *
     * <p>The typical format of the task is as follows</p>
     * <p>[Task type] | [Task completion status] | [Task name/title] | [Other relevant info] | ... </p>
     *
     * @param str The string representation of any task within the tasklist.txt file
     * @return The Task that is represented by the input string
     * @throws TayooException if there is any error while parsing, for example, an invalid task entry
     */
    public static Task parseTask(String str) throws TayooException {
        Scanner scanner = new Scanner(str);
        scanner.useDelimiter("\\|");
        boolean isComplete;
        String title;
        String task = scanner.next().trim();

        switch(task) {
        case ("Task"):
            isComplete = Boolean.parseBoolean(scanner.next().trim());
            title = scanner.next().trim();
            scanner.close();
            return new Task(title, isComplete);
        case ("Todo"):
            isComplete = Boolean.parseBoolean(scanner.next().trim());
            title = scanner.next().trim();
            scanner.close();
            return new ToDo(title, isComplete);
        case ("Event"):
            isComplete = Boolean.parseBoolean(scanner.next().trim());
            title = scanner.next().trim();
            String start = scanner.next().trim();
            String end = scanner.next().trim();
            scanner.close();
            return new Event(title, start, end, isComplete);
        case ("Deadline"):
            isComplete = Boolean.parseBoolean(scanner.next().trim());
            title = scanner.next().trim();
            String deadlineStr = scanner.next().trim();
            scanner.close();
            return new Deadline(title, deadlineStr, isComplete);
        default:
            scanner.close();
            throw new TayooException("Reached end of parse task. Invalid task type: " + task);
        }
    }

    /**
     * Parses the date and time. The method contains a series of potential formats for dates and times separately. It
     * takes in a String which may represent a date. The formatter first tries to match this string input to any one
     * of the preset date formats, the time is optional but can be included. If this fails, the parser will then try
     * to interpret the input as a time only input. Therefore, any one of the following are valid inputs; "01/03/2024",
     * "01 Mar 2024 18:00" and "1800". If the parser is unable to parse the input string as either date or time only, it
     * returns {@code null}. The method returns an object of type TemporalAccessor, because the object returned must
     * implement the method {@code format()} in order to be reformatted later on into a standardised format for the
     * chatbot to print out.
     *
     * @param dateTime The string input that represents the time
     * @return An instance that implements TemporalAccessor
     */
    public static TemporalAccessor parseDateTime(String dateTime) {

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
                .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("dd MM yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .optionalStart()
                .appendOptional(DateTimeFormatter.ofPattern(" HH:mm"))
                .appendOptional(DateTimeFormatter.ofPattern(" HHmm"))
                .optionalEnd()
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();

        DateTimeFormatter timeFormatter = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ofPattern("HH:mm"))
                .appendOptional(DateTimeFormatter.ofPattern("HHmm"))
                .toFormatter();

        try {
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            try {
                return LocalTime.parse(dateTime, timeFormatter);
            } catch (DateTimeParseException e2) {
                return null;
            }
        }
    }

    /**
     * Given a string to check for, this method will iterate through the given list of tasks and check if each task's
     * title contains the string to check for as a substring. If true, then adds that task to a list of tasks which
     * will be return. The capitalisation of input should be arbitrary.
     *
     * @param input The string input to be checked for
     * @param tasklist The tasklist to be iterated through
     * @return A list of tasks which contains the input string as a substring in the title
     */
    static List<Task> findTaskInTasklist(String input, List<Task> tasklist) {
        String substringToCheck = input.toUpperCase();
        List<Task> toReturn = new ArrayList<>();

        int length = tasklist.size();
        for (int i = 0; i < length; i++) {
            Task task = tasklist.get(i);
            String taskName = task.getTitle().toUpperCase();

            if (taskName.contains(substringToCheck)) {
                toReturn.add(task);
            }
        }
        return toReturn;
    }

    private static CommandType parseCommandType(String command) throws TayooException {
        //@@author prave1n-reused
        switch(command.toUpperCase()) {
        case "LIST":
            return CommandType.LIST;
        case "MARK":
            return CommandType.MARK;
        case "UNMARK":
            return CommandType.UNMARK;
        case "TODO":
            return CommandType.TODO;
        case "DEADLINE":
            return CommandType.DEADLINE;
        case "EVENT":
            return CommandType.EVENT;
        case "DELETE":
        case "REMOVE":
            return CommandType.DELETE;
        case "QUIT":
        case "BYE":
        case "EXIT":
        case "GOODBYE":
        case "CLOSE":
            return CommandType.EXIT;
        case "FIND":
            return CommandType.FIND;
        default:
            throw new TayooException("Invalid task type");
        }
    }

}
