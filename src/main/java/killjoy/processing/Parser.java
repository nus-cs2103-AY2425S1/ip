package killjoy.processing;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import killjoy.main.UserInterface;
import killjoy.task.Task;



/**
 * Represents the Parser class of the KillJoy application.
 * Contains methods to parse user input.
 */
public class Parser {
    private static UserInterface ui;
    private static final String BY_KEYWORD = "by ";
    private static final String FROM_KEYWORD = "from ";
    private static final String TO_KEYWORD = "to ";

    /**
     * Constructor for the Parser class.
     *
     * @param ui The UserInterface object.
     */
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
     * Parses the user input to determine the date and time of the task.
     *
     * @param input The user input.
     * @return The date and time of the task.
     */
    public static String getByTimeString(String input) {
        return extractTimeString(input, 1, BY_KEYWORD);
    }

    /**
     * Parses the user input to determine the start time of the task.
     *
     * @param input The user input.
     * @return The start time of the task.
     */
    public static String getFromTimeString(String input) {
        return extractTimeString(input, 1, FROM_KEYWORD);
    }

    /**
     * Parses the user input to determine the end time of the task.
     *
     * @param input The user input.
     * @return The end time of the task.
     */
    public static String getToTimeString(String input) {
        return extractTimeString(input, 2, TO_KEYWORD);
    }

    /**
     * Extracts the time string from the input.
     *
     * @param input The user input.
     * @param slashIndex The index of the slash.
     * @param keyword The keyword to extract.
     * @return The time string.
     */
    private static String extractTimeString(String input, int slashIndex, String keyword) {
        String[] inputSplitBySlash = input.split("/");
        if (inputSplitBySlash.length <= slashIndex) {
            return null;
        }
        return inputSplitBySlash[slashIndex].replaceFirst(keyword, "");
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
            UserInterface.displayEnterNumberMessage();
            return null;
        }
        //Regex section referenced from https://jenkov.com/tutorials/java-regex/index.html
        String regex = "^(mark|unmark|delete)(\\s)(\\d+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) {
            UserInterface.displayInvalidCommandFormatMessage();
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
