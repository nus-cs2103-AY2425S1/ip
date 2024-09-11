package botimusprime.parser;

import botimusprime.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Parser class handles the parsing of user commands and the conversion of strings to date and time formats.
 * It interacts with the TaskList, Ui, and Storage components to process user input and manage tasks.
 */
public class Parser {
    private botimusprime.tasks.TaskList taskList;
    private Ui ui;
    private botimusprime.storage.Storage storage;

    /**
     * Constructs a Parser instance with the specified TaskList, UI, and Storage.
     *
     * @param taskList The TaskList to keep track of tasks.
     * @param ui       The Ui component that handles interactions with the user.
     * @param storage  The Storage component to handle saving and loading tasks from disk.
     */
    public Parser(botimusprime.tasks.TaskList taskList, Ui ui, botimusprime.storage.Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Converts a date and time string into a LocalDateTime object.
     * The method tries to parse the string using different date and time formats.
     *
     * @param timeString The date and/or time string to be converted.
     * @return A LocalDateTime object representing the parsed date and time.
     */
    public static LocalDateTime stringToDateTime(String timeString) {
        DateTimeFormatter isoDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter isoDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter slashDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        DateTimeFormatter slashDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Pattern isoDateTimePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{4}");
        Pattern isoDatePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Pattern slashDateTimePattern = Pattern.compile("\\d{2}/\\d{2}/\\d{4} \\d{4}");
        Pattern slashDatePattern = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");

        if (isoDateTimePattern.matcher(timeString).matches()) {
            return LocalDateTime.parse(timeString, isoDateTimeFormatter);
        } else if (isoDatePattern.matcher(timeString).matches()) {
            return LocalDate.parse(timeString, isoDateFormatter).atStartOfDay();
        } else if (slashDateTimePattern.matcher(timeString).matches()) {
            return LocalDateTime.parse(timeString, slashDateTimeFormatter);
        } else if (slashDatePattern.matcher(timeString).matches()) {
            return LocalDate.parse(timeString, slashDateFormatter).atStartOfDay();
        } else {
            return null;
        }
    }

    /**
     * Parses the user input and executes the corresponding command.
     * The method recognizes commands such as "bye", "list", "mark",
     * "unmark", "to\do", "deadline", "event", and "delete".
     * If the command is not recognized, an error message is displayed.
     *
     * @param input The user input string to be parsed.
     * @return an output string based on the user input.
     */
    public String parse(String input) {
        Pattern listPattern = Pattern.compile("^list$");
        Pattern deletePattern = Pattern.compile("^delete (\\d+)$");
        Pattern markPattern = Pattern.compile("^mark (\\d+)$");
        Pattern unmarkPattern = Pattern.compile("^unmark (\\d+)$");
        Pattern todoPattern = Pattern.compile("^todo (.+)$");
        Pattern deadlinePattern = Pattern.compile("^deadline (.+) /by (.+)$");
        Pattern eventPattern = Pattern.compile("^event (.+) /from (.+) /to (.+)$");
        Pattern findPattern = Pattern.compile("^find (.+)$");
        Pattern viewPattern = Pattern.compile("^view (.+)$");

        Matcher listMatcher = listPattern.matcher(input);
        Matcher deleteMatcher = deletePattern.matcher(input);
        Matcher markMatcher = markPattern.matcher(input);
        Matcher unmarkMatcher = unmarkPattern.matcher(input);
        Matcher todoMatcher = todoPattern.matcher(input);
        Matcher eventMatcher = eventPattern.matcher(input);
        Matcher deadlineMatcher = deadlinePattern.matcher(input);
        Matcher findMatcher = findPattern.matcher(input);
        Matcher viewMatcher = viewPattern.matcher(input);

        boolean isEmpty = taskList.isEmpty();
        boolean isDeleteMarkOrUnmark = deleteMatcher.matches()
                || unmarkMatcher.matches() || markMatcher.matches();

        if (isDeleteMarkOrUnmark && isEmpty) {
            return "Your todo list is empty, human. This operation is invalid.";
        }

        if (listMatcher.matches()) {
            return taskList.showList();
        } else if (markMatcher.matches()) {
            int index = Integer.parseInt(markMatcher.group(1));
            return taskList.markDone(index);
        } else if (unmarkMatcher.matches()) {
            int index = Integer.parseInt(unmarkMatcher.group(1));
            return taskList.markUndone(index);
        } else if (todoMatcher.matches()) {
            return taskList.addToDo(input);
        } else if (deadlineMatcher.matches()) {
            return taskList.addDeadline(input);
        } else if (eventMatcher.matches()) {
            return taskList.addEvent(input);
        } else if (deleteMatcher.matches()) {
            int index = Integer.parseInt(deleteMatcher.group(1));
            return taskList.delete(index);
        } else if (findMatcher.matches()) {
            return taskList.findTask(input);
        } else if (viewMatcher.matches()) {
            return taskList.viewSchedule(input);
        } else if (input.isEmpty()) {
           return "plz type smth bro";
        } else {
            return "I do not comprehend what you are trying to type, human. Try again.";
        }
    }


    public boolean isBye(String input) {
        return input.equals("bye");
    }
}