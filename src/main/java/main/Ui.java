package main;

import task.Task;

/**
 * Represents the user interface for interacting with the application.
 * This class handles the display of messages to the user, including task status updates
 * and other interactions.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     */
    public static String welcome() {
        return "Welcome to Hyperion!\nI am the Attack Titan, type something or else I am gonna destroy them, "
                + "Every last one of those animals that is on this Earth";
    }

    /**
     * Returns a message upon successful deletion of a {@code Task}
     *
     * @param t the {@code Task} instance that is being deleted
     * @param size the current size of the {@code List} of {@code Task}
     * @return the message which tells the task that is deleted and the number
     *         of Tasks in the List of Tasks
     */
    public String deleteMessage(Task t, int size) {
        String s1 = "Noted. I've removed this task:";
        String s2 = String.format("Now you have %d tasks in the list", size);
        return s1 + "\n" + " " + t.toString() + "\n" + s2;
    }

    /**
     * Returns a message upon successful addition of a {@code Task}
     *
     * @param t the {@code Task} instance that is being added
     * @param size the current size of the {@code List} of {@code Task}
     * @return the message which tells the task that is added and the number
     *         of Tasks in the List of Tasks
     */
    public String addedMessage(Task t, int size) {
        String str1 = "Got it. I've added this task:\n";
        String str2 = String.format("Now you have %d tasks in the list", size);
        return str1 + t.toString() + "\n" + str2;
    }

    /**
     * Returns a message upon successful marking of a {@code Task}
     *
     * @param t the {@code Task} instance that is being marked
     * @return the message that tells which {@code Task} is being marked
     */
    public String markedMessage(Task t) {
        return "Nice! I've marked this task as done:\n" + t.toString();
    }

    /**
     * Returns a message upon successful unmarking of a {@code Task}
     *
     * @param t the {@code Task} instance that is being unmarked
     * @return the message that tells which {@code Task} is being unmarks
     */
    public String unmarkedMessage(Task t) {
        return "OK, I've marked this task as not done yet:\n" + t.toString();
    }

    /**
     * Returns a successful message upon the find command
     *
     * @return a message indicating the search result are being displayed
     */
    public String findMessage() {
        return "Ok, these are your search results";
    }

    /**
     * Returns a goodbye message to the user when the application exits
     *
     * @return a message indicating goodbye
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    public String sortMessage() {
        return "I had sorted them for you.";
    }
}
