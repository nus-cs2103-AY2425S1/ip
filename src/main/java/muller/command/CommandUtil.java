package muller.command;

import muller.storage.Storage;
import muller.task.TaskList;
import muller.ui.Ui;

/**
 * Utility class for common validation methods.
 */
public class CommandUtil {
    /**
     * Checks if the input task, ui, storage are not null.
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    public static void assertionTest(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
    }

    /**
     * Checks if the input task index is valid.
     *
     * @param index
     * @param taskListSize
     * @return
     */
    public static boolean isTaskIndexValid(int index, int taskListSize) {
        return index >= 0 && index < taskListSize;
    }

    /**
     * Check if the input is complete for different tasks.
     *
     * @param commandInputs
     * @return
     */
    public static boolean isInputNotComplete(String[] commandInputs) {
        return commandInputs.length < 2;
    }

    /**
     * Checks if the input string is a numeric value.
     *
     * @param str The input string.
     * @return True if the string is numeric, false otherwise.
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Check the command to delete tasks is complete
     *
     * @param commandInputs
     * @return
     */
    public static boolean isDeleteCommandNotValid(String[] commandInputs) {
        return commandInputs.length < 2 || !isNumeric(commandInputs[1]);
    }

    /**
     * Check the command to Remind tasks is valid
     * @param commandInputs
     * @return
     */
    public static boolean isRemindCommandNotValid(String[] commandInputs) {
        return commandInputs.length < 2 || !isNumeric(commandInputs[1]);
    }

    /**
     * Check if the input is complete for different tasks.
     *
     * @param commandInputs
     * @return
     */
    public static boolean isFindCommandNotValid(String[] commandInputs) {
        return commandInputs.length < 2 || commandInputs[1].trim().isEmpty();
    }

    /**
     * Check the command to delete tasks is complete
     *
     * @param commandInputs
     * @return
     */
    public static boolean isMarkCommandNotValid(String[] commandInputs) {
        return commandInputs.length < 2 || !isNumeric(commandInputs[1]);
    }
}
