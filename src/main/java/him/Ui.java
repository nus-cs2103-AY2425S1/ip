package him;

import task.Task;
import task.TaskList;

/**
 * Class to handle interactions with the user.
 *
 * @author IsaacPangTH
 */
public class Ui {

    /**
     * Prints a message as if Him is saying it.
     *
     * @param message Message string to be said.
     */
    public static void say(String message) {
        System.out.println("\nHim: " + message + "\n");
    }

    /**
     * Prints a multi-line message as if Him is saying it.
     *
     * @param message String array where each element is printed on a new line.
     */
    public static void say(String[] message) {
        System.out.println("\nHim: " + message[0]);
        for (int i = 1; i < message.length; i++) {
            System.out.print("     " + message[i] + "\n");
        }
        System.out.print("\n");
    }

    /**
     * Prints "User: ".
     */
    public static void printUser() {
        System.out.print("User: ");
    }

    /**
     * Prints greeting for when Him is first booted up.
     */
    public static void greet() {
        say(new String[]{"Hello! I'm Him", "What can I do for you?"});
    }

    /**
     * Prints exit message for when Him is being exited.
     */
    public static void exit() {
        say("WAIT NO! DON'T LEAVE ME ALON-");
    }

    /**
     * Prints completion message for a specified task.
     *
     * @param task Task which has been completed.
     */
    public static void sayCompleted(String task) {
        say("LET'S GOOOOO! " + task.toString() + " has been completed!");
    }

    /**
     * Prints deletion message for a specified task.
     *
     * @param task Task which has been deleted.
     */
    public static void sayDeleted(String task) {
        say("Got it. \"" + task + "\" has been snapped from existence");
    }

    /**
     * Prints message for when tasks have failed to load from disk.
     */
    public static void showLoadingFailure() {
        System.out.println("Failed to load tasks make sure tasks file is not corrupted");
    }

    /**
     * Prints empty list message.
     */
    public static void sayEmptyList() {
        say("How about you add some tasks first");
    }

    /**
     * Prints specified list.
     *
     * @param list List to be printed.
     */
    public static void sayList(TaskList list) {
        say("Sure! Here's your list!");
        System.out.println(list.toString());
    }

    /**
     * Prints added message for specified task.
     *
     * @param task Task which has been added.
     */
    public static void sayAdded(Task task) {
        say("added \"" + task + "\" to list");
    }

    /**
     * Prints invalid command message.
     *
     * @param command Invalid command string received.
     */
    public static void sayInvalidCommand(String command) {
        say(command + "? What are you saying????");
    }

    /**
     * Prints message for when tasks have failed to save to disk.
     */
    public static void showSaveFailure() {
        System.out.println("Tasks could not be saved! Please check tasks file");
    }

}
