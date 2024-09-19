package pebble;

import java.util.ArrayList;
public class Ui {

    /**
     * Shows a message in both CLI and GUI.
     *
     * @param message The message to be displayed.
     * @return The message to be displayed on GUI.
     */
    public String showMessage(String message) {
        // Print the message to the command line
        System.out.println(message);
        // Return the message to be displayed in GUI
        return message;
    }

    public String showWelcome() {
        return showMessage("HELLO MORTAL im Pebble!");
    }

    public String showGoodbye() {
        return showMessage("Goodbye! Hope to see you again soon!");
    }

    public String showTasksList(ArrayList<Task> tasksList) {
        int tasksListSize = tasksList.size();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasksListSize; i++) {
            stringBuilder.append(tasksList.get(i)).append("\n");
        }
        return showMessage(stringBuilder.toString());
    }

    public String showMarkTask(Task task) {
        return showMessage("Nice! I've marked this task as done:\n" + task);
    }

    public String showUnmarkTask(Task task) {
        return showMessage("OK, I've marked this task as not done yet:\n" + task);
    }

    public String showAddTask(Task task, int size) {
        return showMessage("Got it. I've added this task:\n" + task
                + "\nNow you have " + size + " tasks in the list.");
    }

    public String showDeleteTask(Task task, int size) {
        return showMessage("Noted. I've removed this task:\n"
                + task + "\nNow you have " + size + " tasks in the list.");
    }

    public String showError(String errorMessage) {
        return showMessage("Error: " + errorMessage);
    }
}
