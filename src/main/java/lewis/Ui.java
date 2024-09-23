package lewis;

import java.util.ArrayList;
import java.util.List;
/**
 * Implements a user interface. This handles user input through the dialogue box
 * and propagates a string response through to the GUI.
 */
public class Ui {
    private static final List<String> OUTPUT_BUFFER = new ArrayList<>();
    private Ui() {
    }

    /**
     * Prints a string representation of a given task to the terminal
     * This usage should be used when creating a new task
     * @param task the task to be printed
     */
    public static void printTask(Task task) {
        OUTPUT_BUFFER.add("I've got it, I've added this task to your tasklist.");
        OUTPUT_BUFFER.add(task.toString());
    }

    /**
     * Prints a string representation of a given task to be deleted.
     * This usage should be used when deleting a task
     * @param task the task to be deleted
     */
    public static void printDeletedTask(Task task) {
        OUTPUT_BUFFER.add("I've obliterated this task from the list. You're welcome");
        OUTPUT_BUFFER.add(task.toString());
    }

    /**
     * Prints a given string to the terminal
     * @param string the string to be printed
     */
    public static void printString(String string) {
        OUTPUT_BUFFER.add(string);
    }

    /**
     * Prints the list of tasks as strings separated by a new line
     * @param listToPrint an array of strings representing tasks.
     */
    public static void printList(ArrayList<String> listToPrint) {
        if (listToPrint.isEmpty()) {
            OUTPUT_BUFFER.add("Hey, this list is empty. Try adding some new tasks!");
        }
        for (String string : listToPrint) {
            OUTPUT_BUFFER.add(string);
        }
    }

    /**
     * Flushes the output buffer
     */
    public static List<String> flush() {
        List<String> output = new ArrayList<>(OUTPUT_BUFFER);
        OUTPUT_BUFFER.clear();
        return output;
    }



}
