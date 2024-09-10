package utilities;

import tasks.Task;

/**
 * Generates system messages for the application.
 */
public class Ui {
    public static String updateUserOnAddition(String name, int size) {
        String message = "";

        message += "I've added: ";
        message += name;
        message += String.format("\nNow you have %d in the list!", size);

        return message;
    }

    public static String updateUserOnDeletion(Task t) {
        String message = "";

        message += "Noted! I've removed this task: ";
        message += t.toString();

        return message;
    }

    public static String updateUserOnUncompletion(Task t) {
        String message = "";

        message += "OK! I've marked the task as not done yet: ";
        message += t.toString();

        return message;
    }

    public static String updateUserOnCompletion(Task t) {
        String message = "";

        message += "Good job! I've marked this task as done: ";
        message += t.toString();

        return message;
    }

    public static String updateUserOnError(Exception e) {
        String message = "";

        message += "Some error has occured, please try again.";
        message += e.getMessage();

        return message;
    }
}
