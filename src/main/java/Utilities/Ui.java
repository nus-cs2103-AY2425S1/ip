package utilities;

import tasks.Task;

/**
 * Generates system messages for the application.
 */
public class Ui {
    /**
     * Returns user message for creation of tasks.
     * @param name String name of task.
     * @param size Integer size of current task list.
     */
    public static String updateUserOnAddition(String name, int size) {
        String message = "";

        message += "I've added: ";
        message += name;
        message += String.format("\nNow you have %d tasks in the list! Time to get to work bro!", size);

        return message;
    }

    /**
     * Returns user message for deletion of tasks.
     * @param t Task to be deleted.
     */
    public static String updateUserOnDeletion(Task t) {
        String message = "";

        message += "Noted! I've removed this task bro: ";
        message += t.toString();

        return message;
    }

    /**
     * Returns user message for updating of task tag.
     * @param t Task to be tagged.
     */
    public static String updateUserOnTag(Task t) {
        String message = "";

        message += "Noted! I have successfully tagged this task bro: ";
        message += t.toString();
        message += t.getTag();

        return message;
    }

    /**
     * Returns user message for task uncompletion.
     * @param t Task to be flagged as not done.
     */
    public static String updateUserOnUncompletion(Task t) {
        String message = "";

        message += "Fine! I've marked the task as not done yet bro: ";
        message += t.toString();

        return message;
    }

    /**
     * Returns user message for task completion.
     * @param t Task to be flagged as done.
     */
    public static String updateUserOnCompletion(Task t) {
        String message = "";

        message += "Good job! I've marked this task as done bro: ";
        message += t.toString();

        return message;
    }

    /**
     * Returns user message when an error occurs in the application.
     * Only checked exceptions are handled here.
     * @param e Exception raised.
     * @return String message that includes exception details.
     */
    public static String updateUserOnError(Exception e) {
        String message = "";

        message += "The following error has occured, please try again bro. \n";
        message += e.getMessage();

        return message;
    }
}
