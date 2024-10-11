package mortalreminder.errorhandling;

import mortalreminder.backend.tasklistmanager.TaskList;

/**
 * Creates a throwable exception class that is specific to this project.
 */
public class MortalReminderException extends Exception {
    public MortalReminderException(String message) {
        super(message);
    }

    public static String getUnreachableCodeErrorMessage() {
        return "This statement should be unreachable, code has an error!";
    }

    public static String getInvalidNumberFormatErrorMessage() {
        return "Please enter a valid number after the command!";
    }

    public static String getInvalidDateFormatErrorMessage() {
        return "Please enter a valid date in dd-MM-yyyy HHmm (24hr format)!";
    }

    public static String getInvalidDescriptionErrorMessage() {
        return "Description cannot be empty!";
    }

    public static String getInvalidDeadlineDescriptionErrorMessage() {
        return "Please input the correct number of details for deadlines!"
                + " Remember that you need to include '/by' in the command.";
    }

    public static String getInvalidEventDescriptionErrorMessage() {
        return "Please input the correct number of details for deadlines! "
                + "Remember that you need to include /from and /to in the command.";
    }

    public static String getAlreadyMarkedErrorMessage() {
        return "This task has already been marked as done.";
    }

    public static String getAlreadyNotMarkedErrorMessage() {
        return "This task has already been marked as NOT done.";
    }

    public static String getOutOfTaskListBoundsErrorMessage(TaskList taskList) {
        return "Invalid task number!\n"
                + "Please input a number between 1 and "
                + taskList.getSize();
    }

    public static String getQueryOnEmptyListErrorMessage() {
        return "List is empty!";
    }

    public static String getStorageFileCorruptedErrorMessage() {
        return "File might be corrupted! Please use clear_tasks to restart the file.";
    }

    public static String getFileCannotBeCreatedErrorMessage() {
        return "File could not be created!";
    }

    public static String getFileNotFoundErrorMessage() {
        return "File not found!";
    }

    public static String getExtraSpaceInAlternativeCommandErrorMessage() {
        return "Please specify a command word followed by a space"
                + "and a current saved version of the command word";
    }

    public static String getCorruptedAlternativeCommandFileErrorMessage() {
        return "Alternative command file does not exist!";
    }

    public static String getNoSimilarTasksFoundMessage() {
        return "No similar tasks found!";
    }

    public static String getUnknownCommandWordErrorMessage(String commandWord) {
        return "Unknown command word: "
                + commandWord
                + ". Please put in a known recognised command as the second word in the command";
    }
}
