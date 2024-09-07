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
     * Returns a message as if Him is saying it.
     *
     * @param message Message string to be said.
     * @return Formatted message.
     */
    public static String say(String message) {
        return say(message.split("\n"));
    }

    /**
     * Returns a multi-line message as if Him is saying it.
     *
     * @param message String array where each element is printed on a new line.
     * @return Formatted message.
     */
    public static String say(String[] message) {
        StringBuilder sb = new StringBuilder();
        sb.append("Him: ").append(message[0]).append("\n");
        for (int i = 1; i < message.length; i++) {
            sb.append("        ").append(message[i]).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns greeting for when Him is first booted up.
     *
     * @return Greeting.
     */
    public static String greet() {
        return say(new String[]{"Hello! I'm Him", "What can I do for you?"});
    }

    /**
     * Returns exit message for when Him is being exited.
     *
     * @return Exit message.
     */
    public static String exit() {
        return say("WAIT NO! DON'T LEAVE ME ALON-");
    }

    /**
     * Returns completion message for a specified task.
     *
     * @param task Task which has been completed.
     * @return Completion message.
     */
    public static String sayCompleted(String task) {
        return say("LET'S GOOOOO! " + task.toString() + " has been completed!");
    }

    /**
     * Returns deletion message for a specified task.
     *
     * @param task Task which has been deleted.
     * @return Deletion message.
     */
    public static String sayDeleted(String task) {
        return say("Got it. \"" + task + "\" has been snapped from existence");
    }

    /**
     * Returns message for when tasks have failed to load from disk.
     *
     * @return Failed to load message.
     */
    public static String sayLoadingFailure() {
        return say("Failed to load tasks make sure tasks file is not corrupted");
    }

    /**
     * Returns empty list message.
     *
     * @return Empty list message.
     */
    public static String sayEmptyList() {
        return say("How about you add some tasks first");
    }

    /**
     * Returns specified list formatted as a message.
     *
     * @param list List to be returned.
     * @return Formatted list.
     */
    public static String sayList(TaskList list) {
        return say("Sure! Here's your list!\n" + list.toString());
    }

    /**
     * Returns added message for specified task.
     *
     * @param task Task which has been added.
     * @return Added message.
     */
    public static String sayAdded(Task task) {
        return say("added \"" + task + "\" to list");
    }

    /**
     * Returns invalid command message.
     *
     * @param command Invalid command string received.
     * @return Invalid command message.
     */
    public static String sayInvalidCommand(String command) {
        return say(command + "? What are you saying????");
    }

    /**
     * Returns message for when tasks have failed to save to disk.
     *
     * @returns Failed save failure message.
     */
    public static String showSaveFailure() {
        return say("Tasks could not be saved! Please check tasks file");
    }

}
