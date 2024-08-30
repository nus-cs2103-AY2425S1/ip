package chatbot;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles parsing of text representation of tasks
 * from and to text files
 *
 * @author celeschai
 */
public class Parser {
    /**
     * Accepted formats for date string input
     */
    private static final DateTimeFormatterBuilder builder =
            new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"))
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"))
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm a"));

    /**
     * Parses date string input into java LocalDateTime objects
     *
     * @param dateTime string representation of date time
     * @return LocalDateTime object for Task objects
     * @throws DateTimeParseException for incorrectly formatted input string
     */
    public static LocalDateTime parseStringToDateTime(String dateTime) throws DateTimeParseException {
        final DateTimeFormatter formatter = builder.toFormatter().withResolverStyle(ResolverStyle.LENIENT);
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Parses java LocalDateTime objects into date string input
     *
     * @param dateTime LocalDateTime object from Task objects
     * @return readable date time string for user
     */
    public static String parseDateTimeToString(LocalDateTime dateTime) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        return dateTime.format(formatter);
    }

    /**
     * Parses task list from text files to create
     * Task objects
     *
     * @param sc       scanner that reads source file
     * @param taskList task list object used by chatbot
     */
    public static void parseFromTxtTaskList(Scanner sc, TaskList taskList) {
        while (sc.hasNextLine()) {
            String next = sc.nextLine();

            // Regular expression to capture different parts of the string
            Pattern pattern = Pattern.compile("\\[(\\S)]\\[(.)] (\\S.*)");
            Matcher matcher = pattern.matcher(next);

            if (matcher.matches()) {
                String taskType = matcher.group(1);
                boolean taskMarkedDone = matcher.group(2).equals("X");
                String taskDetails = matcher.group(3);

                if (taskType.equals("T")) {
                    Todo todo = new Todo(taskDetails);
                    if (taskMarkedDone) {
                        todo.markAsDone();
                    }
                    taskList.addTask(todo);
                    continue;
                } else if (taskType.equals("D")) {
                    Pattern patternD = Pattern.compile("(\\S.*) \\(by: (\\S.*)\\)");
                    Matcher matcherD = patternD.matcher(taskDetails);

                    if (matcherD.matches()) {
                        Deadline deadline = new Deadline(matcherD.group(1), matcherD.group(2));
                        if (taskMarkedDone) {
                            deadline.markAsDone();
                        }
                        taskList.addTask(deadline);
                        continue;
                    }
                } else if (taskType.equals("E")) {
                    Pattern patternE = Pattern.compile("(\\S.*) \\(from: (\\S.*) to: (\\S.*)\\)");
                    Matcher matcherE = patternE.matcher(taskDetails);

                    if (matcherE.matches()) {
                        Event event = new Event(matcherE.group(1), matcherE.group(2), matcherE.group(3));
                        if (taskMarkedDone) {
                            event.markAsDone();
                        }
                        taskList.addTask(event);
                        continue;
                    }
                }
            }

            System.out.println("this line is in an invalid format");
        }
        sc.close();
    }
}
