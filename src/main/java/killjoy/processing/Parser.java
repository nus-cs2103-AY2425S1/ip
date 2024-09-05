package killjoy.processing;

import killjoy.task.Task;
import killjoy.main.UserInterface;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.time.format.DateTimeParseException;

/**
 * Represents the Parser class of the KillJoy application.
 * Contains methods to parse user input.
 */
public class Parser {
    private static UserInterface ui;

    public Parser(UserInterface ui) {
        this.ui = ui;
    }

    /**
     * Parses the user input to determine the type of task.
     *
     * @param input The user input.
     * @return The type of task.
     */
    public static Task.TaskType parseUserInput(String input) {
        String[] inputSplitBySlash = input.split("/");
        String[] inputSplitBySpace = inputSplitBySlash[0].split(" ");
        String typeOfTask = inputSplitBySpace[0];

        try {
            return Task.TaskType.valueOf(typeOfTask.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Parses the user input to determine the description of the task.
     *
     * @param input The user input.
     * @return The description of the task.
     */
    public static String getDescriptionFromInput(String input) {
        String[] inputSplitBySlash = input.split("/");
        return inputSplitBySlash[0].replaceFirst("todo ", "")
                .replaceFirst("deadline ", "").replaceFirst("event ", "");
    }

    /**
     * Parses the user input to determine the by date of the task.
     *
     * @param input The user input.
     * @return The by date of the task.
     */
    public static String getByTimeString(String input) {
        String[] inputSplitBySlash = input.split("/");
        if (inputSplitBySlash.length < 2) {
            return null;
        }
        return inputSplitBySlash[1].replaceFirst("by ", "");
    }

    /**
     * Parses the user input to determine the from date of the task.
     *
     * @param input The user input.
     * @return The from date of the task.
     */
    public static String getFromTimeString(String input) {
        String[] inputSplitBySlash = input.split("/");
        if (inputSplitBySlash.length < 2) {
            return null;
        }
        return inputSplitBySlash[1].replaceFirst("from ", "");
    }

    /**
     * Parses the user input to determine the to date of the task.
     *
     * @param input The user input.
     * @return The to date of the task.
     */
    public static String getToTimeString(String input) {
        String[] inputSplitBySlash = input.split("/");
        if (inputSplitBySlash.length < 3) {
            return null;
        }
        return inputSplitBySlash[2].replaceFirst("to ", "");
    }

    /**
     * Parses the input to get index of task to mark, unmark or delete.
     *
     * @param input The user input.
     * @return The index and type of action.
     */
    public static String[] parseMarkUnmarkDelete(String input) {
        String[] inputAsList = input.split(" ");

        if (inputAsList.length == 1) {
            ui.displayEnterNumberMessage();
            return null;
        }
        //Regex section referenced from https://jenkov.com/tutorials/java-regex/index.html
        String regex = "^(mark|unmark|delete)(\\s)(\\d+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) {
            ui.displayInvalidCommandFormatMessage();
            return null;
        }
        return new String[]{matcher.group(1), matcher.group(3)};
    }

    /**
     * Parses the input to get the date and time.
     *
     * @param dateTime The date and time.
     * @return The date and time.
     */
    public static LocalDateTime parseStringToLocalDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime.trim() + "T00:00");
        } catch (DateTimeParseException d) {
            try {
                String[] parts = dateTime.split(" ");
                String time = parts[0] + "T" + parts[1];
                return LocalDateTime.parse(time);
            } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                return null;
            }
        }

    }

    public static String[] parseTaskInfo(String taskInfo) {
        return taskInfo.split("\\|");
    }


}
