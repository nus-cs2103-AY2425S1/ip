package nugget;

import java.util.ArrayList;

import nugget.gui.ChatUiController;

/**
 * The Ui class handles the interaction between the user and the application by updating the GUI
 * with messages, error reports, and task-related updates.
 */
public class Ui {
    private ChatUiController gui;

    public Ui(ChatUiController gui) {
        this.gui = gui;
    }

    public void showMessage(String message) {
        gui.updateOutput(message);
    }

    /**
     * Prints the introductory message when the application starts.
     */
    public void printIntro() {
        gui.updateOutput("Hello! I'm Nugget\n"
                + "What can I do for you?\n");
    }

    public void printEnd() {
        gui.updateOutput("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        gui.updateOutput("Error: " + message);
    }

    /**
     * Displays a message indicating a task has been added to task list.
     * @param task The task that has been added.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        String output = "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + taskCount + " tasks in the list.";
        gui.updateOutput(output);
    }

    /**
     * Displays a message indicating a task has been removed from the list.
     *
     * @param task The task that has been removed.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskRemoved(Task task, int taskCount) {
        String output = "Noted. I've removed this task:\n"
                + task + "\n"
                + "Now you have " + taskCount + " tasks in the list.";
        gui.updateOutput(output);
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showMarkedTask(Task task) {
        String output = "Nice! I've marked this task as done:\n"
                + task;
        gui.updateOutput(output);
    }

    /**
     * Displays a message indicating a task has been unmarked (marked as not done).
     *
     * @param task The task that has been unmarked.
     */
    public void showUnmarkedTask(Task task) {
        String output = "OK, I've marked this task as not done yet:\n"
                + task;
        gui.updateOutput(output);
    }

    /**
     * Displays the search results for tasks matching a given query.
     *
     * @param matchingTasks A list of tasks that match the search criteria.
     */
    public void showFindResults(ArrayList<Task> matchingTasks) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            output.append(i + 1).append(".").append(matchingTasks.get(i)).append("\n");
        }
        gui.updateOutput(output.toString().trim()); // Use trim() to remove any trailing newline
    }
}
