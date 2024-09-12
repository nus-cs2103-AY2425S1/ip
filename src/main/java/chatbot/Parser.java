package chatbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;

/**
 * Handles parsing of text representation of tasks
 * from and to text files.
 *
 * @author celeschai
 */
public class Parser {
    /**
     * Accepts multiple formats for date string input.
     */
    private static final DateTimeFormatterBuilder builder =
            new DateTimeFormatterBuilder()
                    // Accepts both AM or am as input
                    .parseCaseInsensitive()
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"))
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"))
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm a"));

    /**
     * Parses date string input into java LocalDateTime objects.
     *
     * @param dateTime String representation of date time.
     * @return LocalDateTime object for Task objects.
     * @throws DateTimeParseException For incorrectly formatted input string.
     */
    public static LocalDateTime parseStringToDateTime(String dateTime) throws DateTimeParseException {
        final DateTimeFormatter formatter = builder.toFormatter().withResolverStyle(ResolverStyle.LENIENT);
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Parses java LocalDateTime objects into date string input.
     *
     * @param dateTime LocalDateTime object from Task objects.
     * @return Readable date time string for user.
     */
    public static String parseDateTimeToString(LocalDateTime dateTime) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        return dateTime.format(formatter);
    }

    /**
     * Parses task list from text files to create
     * Task objects.
     *
     * @param sc       Scanner that reads source file
     * @param taskList TaskList object used by chatbot.
     */
    public static void parseFromTxtTaskList(Scanner sc, TaskList taskList) {
        while (sc.hasNextLine()) {
            String next = sc.nextLine();
            Matcher matcher = getMatcher(next, "\\[(\\S)]\\[(.)] (\\S.*)");

            if (matcher.matches()) {
                String taskType = matcher.group(1);
                boolean taskMarkedDone = matcher.group(2).equals("X");
                String taskDetails = matcher.group(3);
                if (taskType.equals("T")) {
                    handleTodo(taskList, taskDetails, taskMarkedDone);
                    continue;
                } else if (taskType.equals("D")) {
                    Matcher matcherD = getMatcher(taskDetails, "(\\S.*) \\(by: (\\S.*)\\)");

                    if (matcherD.matches()) {
                        handleDeadline(taskList, matcherD, taskMarkedDone);
                        continue;
                    }
                } else if (taskType.equals("E")) {
                    Matcher matcherE = getMatcher(taskDetails, "(\\S.*) \\(from: (\\S.*) to: (\\S.*)\\)");

                    if (matcherE.matches()) {
                        handleEvent(taskList, matcherE, taskMarkedDone);
                        continue;
                    }
                }
            }
            System.out.println("this line is in an invalid format");
        }
        sc.close();
    }

    private static void handleDeadline(TaskList taskList, Matcher matcherD, boolean taskMarkedDone) {
        Deadline deadline = new Deadline(matcherD.group(1), matcherD.group(2));
        if (taskMarkedDone) {
            deadline.markAsDone();
        }
        taskList.addTask(deadline);
    }

    private static void handleEvent(TaskList taskList, Matcher matcherE, boolean taskMarkedDone) {
        Event event = new Event(matcherE.group(1), matcherE.group(2), matcherE.group(3));
        if (taskMarkedDone) {
            event.markAsDone();
        }
        taskList.addTask(event);
    }

    private static void handleTodo(TaskList taskList, String taskDetails, boolean taskMarkedDone) {
        Todo todo = new Todo(taskDetails);
        if (taskMarkedDone) {
            todo.markAsDone();
        }
        taskList.addTask(todo);
    }

    public static Matcher getMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);

    }
}
