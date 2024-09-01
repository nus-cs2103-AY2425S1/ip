package Utility;

import Exceptions.BrockException;
import Tasks.TaskList;

public class Utility {
    public enum Action {
        MARK,
        UNMARK
    }

    public enum Context {
        DUE,
        START,
        END
    }

    public static boolean isNotInteger(String commandWord) {
        try {
            Integer.parseInt(commandWord);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }
        return false;
    }

    public static int getTargetIndex(String command) {
        char targetItemNumber = command.charAt(command.length() - 1);
        return Character.getNumericValue(targetItemNumber) - 1;
    }

    private static String getLabel(int dateTimeWords, Context context) throws BrockException {
        String label = "";
        switch (context) {
        case DUE:
            label = "Due ";
            break;
        case START:
            label = "Start ";
            break;
        case END:
            label = "End ";
            break;
        }

        if (dateTimeWords > 2) {
            throw new BrockException(String.format("Valid %s date & time must follow one of the below formats:\n"
                    , label.toLowerCase())
                    + "<yyyy-mm-dd> OR\n"
                    + "<yyyy-mm-dd> <24hr-time>");
        }
        return label;
    }

    public static String[] validateDateTime(String dateTimeString, int dateTimeWords, Context context)
            throws BrockException {
        String label = getLabel(dateTimeWords, context);

        String dateStringFinal = "";
        String timeStringFinal = "";
        if (dateTimeWords == 1) {
            String dateString = dateTimeString.trim();
            String[] dateParts = dateString.split("-");

            if (dateParts.length != 3) {
                throw new BrockException(label + "date & time following <yyyy-mm-dd> format:\n"
                        + label + "date is not in the <yyyy-mm-dd> format!");
            }
            dateStringFinal = dateString;
        }
        if (dateTimeWords == 2) {
            String[] dateTimeParts = dateTimeString.trim()
                    .split(" ");
            String dateString = dateTimeParts[0];
            String timeString = dateTimeParts[1];
            String[] dateParts = dateString.split("-");

            if (dateParts.length != 3) {
                throw new BrockException(label + "date & time following <yyyy-mm-dd> <24hr-time> format:\n"
                        + label + "date is not in the <yyyy-mm-dd> format!");
            }
            if (isNotInteger(timeString)) {
                throw new BrockException(label + "date & time following <yyyy-mm-dd> <24hr-time> format:\n"
                        + label + "time is not a number!");
            } else {
                int time = Integer.parseInt(timeString);
                if (time < 0 || time > 2359) {
                    throw new BrockException(label + "date & time following <yyyy-mm-dd> <24hr-time> format:\n"
                            + label + "time must be between 0000 - 2359!");
                }
                // Convert to string
                // Force length == 4 with 0s as front-padding
                timeStringFinal = String.format("%04d", time);
            }
            dateStringFinal = dateString;
        }

        if (timeStringFinal.isEmpty()) {
            return new String[]{dateStringFinal};
        } else {
            return new String[]{dateStringFinal, timeStringFinal};
        }
    }

    public static void validateStatus(String command, Action action, TaskList tasks) throws BrockException {
        String actionName = action == Action.MARK
                ? "Mark"
                : "Unmark";
        String[] commandWords = command.split(" ");
        int commandLength = commandWords.length;

        if (commandLength == 1) {
            throw new BrockException("Missing task number!");
        }
        if (commandLength > 2 || isNotInteger(commandWords[1])) {
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
