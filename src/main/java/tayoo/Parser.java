package tayoo;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import tayoo.command.AddTaskCommand;
import tayoo.command.Command;
import tayoo.command.DeleteAllCommand;
import tayoo.command.DeleteTaskCommand;
import tayoo.command.ExitCommand;
import tayoo.command.FindCommand;
import tayoo.command.ListCommand;
import tayoo.command.MarkTaskCommand;
import tayoo.exception.ParserException;
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
     * Contains all the commands which can be used to exit the Tayoo chatbot.
     */
    private static final String[] exitCodes = {"EXIT", "BYE", "GOODBYE", "CLOSE", "QUIT"};

    /**
     * Parses the user's input. Currently utilises a series of if-else checks to correlate the user's input with any
     * commands that have been implemented. The case of the input command is arbitrary as all commands are automatically
     * converted to upper case. This means that if implementing any new commands, they should be in all upper case when
     * being added to the if-else chain.
     *
     * @param command The user's input in a String format. The command should start with a substring representing the
     * type of command,
     * @return An object that is a subclass of the Command class.
     * @throws ParserException if there is an error when parsing a command, either because the command is not supported,
     * or the format of the command is wrong
     */
    public static Command parseCommand(String command) throws ParserException {
        String input = command.toUpperCase();

        if (Arrays.asList(exitCodes).contains(input)) {
            return new ExitCommand();
        } else if (input.startsWith("FIND")) {
            String toFind = input.substring(5).trim();
            return new FindCommand(toFind);
        } else if (input.equals("LIST")) {
            return new ListCommand();
        } else if (input.startsWith("MARK ")) {
            int taskNumber = Integer.parseInt(input.substring(5).trim()) - 1;
            return new MarkTaskCommand(taskNumber, true);
        } else if (input.startsWith("UNMARK ")) {
            int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
            return new MarkTaskCommand(taskNumber, false);
        } else if (input.startsWith("TODO")) {
            try {
                return new AddTaskCommand(new ToDo(command.substring(5).trim()));
            } catch (IndexOutOfBoundsException e) {
                throw new ParserException("Add a description to your todo!");
            }
        } else if (input.startsWith("DEADLINE")) {
            try {
                int deadlineIndex = input.indexOf("/BY ");
                if (deadlineIndex < 9) {
                    throw new ParserException("Deadline format incorrect. Format: \"deadline [taskName] /by "
                            + "[deadline]\"." + " Try again please");
                }
                if (deadlineIndex == 9) {
                    throw new ParserException("I see the deadline but no task :(");
                }

                String taskTitle = command.substring(9, deadlineIndex - 1).trim();
                String deadlineStr = command.substring(deadlineIndex + 4).trim();

                return new AddTaskCommand(new Deadline(taskTitle, deadlineStr));

            } catch (IndexOutOfBoundsException e) {
                throw new ParserException("You've made a fatal error! Report it to the developer or"
                        + " face eternal DOOM!!");
            }
        } else if (input.startsWith("EVENT")) {
            try {
                int startIndex = input.indexOf("/FROM ");
                int endIndex = input.indexOf("/TO ");
                String parsedStart = command.substring(startIndex + 5, endIndex - 1).trim();
                String parsedEnd = command.substring(endIndex + 4).trim();
                String eventTitle = command.substring(6, startIndex - 1);

                return new AddTaskCommand(new Event(eventTitle, parsedStart, parsedEnd));
            } catch (IndexOutOfBoundsException e) {
                throw new ParserException("Event format incorrect. Format: \"Event [taskName] /from [start] "
                        + "/to [end]\". Try again please");
            }
        } else if (input.startsWith("DELETE") || input.startsWith("REMOVE")) {
            try {
                if (input.substring(7).trim().equals("ALL")) {
                    return new DeleteAllCommand();
                }

                int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
                return new DeleteTaskCommand(taskNumber);
            } catch (IndexOutOfBoundsException e) {
                throw new ParserException("Hey, that task doesn't exist for me to delete!");
            } catch (NumberFormatException e) {
                throw new ParserException("Hey, that's not a task number! Give me a number please!");
            }
        } else {
            throw new ParserException("I'm not sure what that means :(");
        }
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
     * @throws ParserException if there is any error while parsing, for example, an invalid task entry
     */
    public static Task parseTask(String str) throws ParserException {
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
            //should not ever reach here
            scanner.close();
            throw new ParserException("Reached end of parse task. Invalid task type: " + task);
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
    static List<Task> parseFind(String input, List<? extends Task> tasklist) {
        String toCheck = input.toUpperCase();
        List<Task> toReturn = new ArrayList<>();
        int length = tasklist.size();
        for (int i = 0; i < length; i++) {
            if (tasklist.get(i).getTitle().toUpperCase().contains(toCheck)) {
                toReturn.add(tasklist.get(i));
            }
        }
        return toReturn;
    }
}
