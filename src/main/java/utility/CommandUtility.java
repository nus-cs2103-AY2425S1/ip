package utility;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import exceptions.BrockException;
import task.TaskList;

/**
 * Class with commonly used utility functions for commands
 * to reduce code duplication
 */
public class CommandUtility {
    /**
     * Enum used to represent mark and unmark actions.
     * To help validate mark and unmark commands.
     */
    public enum Action {
        MARK,
        UNMARK
    }

    /**
     * Enum used to represent due, start and end dateTimes.
     * To help validate dateTimes of deadline and event commands.
     */
    public enum Context {
        DUE,
        START,
        END
    }

    /**
     * Checks if a string holds an integer or not.
     *
     * @param commandWord String to be checked.
     * @return True if string does not hold an integer, false otherwise.
     */
    public static boolean isNotInteger(String commandWord) {
        try {
            Integer.parseInt(commandWord);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }
        return false;
    }

    /**
     * Gets the task index of a {@code Task} object in the task list.
     * From a command that specifies a task number.
     *
     * @param command Command to be examined.
     * @return Task index obtained.
     */
    public static int getTaskIndex(String command) {
        char taskNumber = command.charAt(command.length() - 1);
        return Character.getNumericValue(taskNumber) - 1;
    }

    /**
     * Gets the label to be used in validating date and time.
     *
     * @param context       Indicates if method is looking at due dateTime, start dateTime or end dateTime.
     * @return Label based on context.
     * @throws BrockException If more than 2 words.
     */
    private static String getLabel(Context context) throws BrockException {
        String label;
        // Can ignore style error for this enhanced switch statement
        label = switch (context) {
            case DUE -> "Due ";
            case START -> "Start ";
            case END -> "End ";
        };
        return label;
    }

    private static String[] processDateTimeString(String dateTimeString) {
        return dateTimeString.trim()
                .split(" ");
    }

    private static void validateDate(String dateString, String label) throws BrockException {
        String[] dateParts = dateString.split("-");
        if (dateParts.length != 3) {
            throw new BrockException(label + "date & time following <yyyy-mm-dd> format:\n"
                    + label + "date is not in the <yyyy-mm-dd> format!");
        }
    }

    private static void validateTime(String timeString, String label) throws BrockException {
        if (CommandUtility.isNotInteger(timeString)) {
            throw new BrockException(label + "date & time following <yyyy-mm-dd> <24hr-time> format:\n"
                    + label + "time is not a number!");
        } else {
            try {
                LocalTime.parse(timeString,
                        java.time.format.DateTimeFormatter.ofPattern("HHmm"));
            } catch (DateTimeParseException e) {
                throw new BrockException("Time must be in HHmm format and between 0000 and 2359!");
            }
        }
    }


    /**
     * Checks if the dateTime string given is valid or not.
     *
     * @param dateTimeString String representing dateTime.
     * @param context        Indicates if method is looking at due dateTime, start dateTime or end dateTime.
     * @return Formatted date and time strings separately.
     * @throws BrockException If dateTime string is invalid.
     */
    public static String[] validateDateTime(String dateTimeString, Context context)
            throws BrockException {

        String label = getLabel(context);
        String[] dateTimeParts = processDateTimeString(dateTimeString);

        String dateStringFinal;
        String timeStringFinal = "";
        if (dateTimeParts.length > 2) {
            throw new BrockException(String.format("Valid %s date & time must follow one of the below formats:\n",
                    label.toLowerCase())
                    + "<yyyy-mm-dd> OR\n"
                    + "<yyyy-mm-dd> <24hr-time>");
        }

        if (dateTimeParts.length == 2) {
            String timeString = dateTimeParts[1];
            validateTime(timeString, label);
            timeStringFinal = timeString;
        }

        String dateString = dateTimeParts[0];
        validateDate(dateString, label);
        dateStringFinal = dateString;

        if (timeStringFinal.isEmpty()) {
            return new String[]{dateStringFinal};
        } else {
            return new String[]{dateStringFinal, timeStringFinal};
        }
    }

    /**
     * Checks if the mark or unmark command is valid.
     *
     * @param command Either the mark or unmark command.
     * @param action  Specifying which command type.
     * @param tasks   {@code TaskList} object that stores the current tasks in an {@code ArrayList}.
     * @throws BrockException If the command is invalid.
     */
    public static void validateStatus(String command, Action action, TaskList tasks) throws BrockException {
        String actionName = action == Action.MARK
                ? "Mark"
                : "Unmark";
        String[] commandWords = command.split(" ");
        int commandLength = commandWords.length;

        if (commandLength == 1) {
            throw new BrockException("Missing task number!");
        }
        if (commandLength > 2 || CommandUtility.isNotInteger(commandWords[1])) {
            throw new BrockException(actionName
                    + " command is in the form "
                    + actionName.toLowerCase()
                    + " <task-number>!");
        }

        int taskNumber = Integer.parseInt(commandWords[1]);
        int totalTasks = tasks.numTasks();
        if (taskNumber > totalTasks || taskNumber < 1) {
            throw new BrockException("Task number does not exist!");
        }
    }
}
