package FRIDAY;

import java.util.ArrayList;

/**
 * User interface of the app
 */
public class Ui {
    /**
     * Prints out message upon adding of task to the task list
     * @param task Task object
     * @param numTasks  number of tasks in list
     */
    public String printAdd(Task task, int numTasks) {
        return "Alright! Your list has been updated :)\n"
                + task.getTaskDescription() + "\nThere are now " + numTasks + " tasks in your list\n";
    }

    /**
     * Prints out message on deletion of task from task list
     * @param task Task object
     * @param numTasks size of task list
     */
    public String printRemove(Task task, int numTasks) {
        return "Gotchu. I've deleted the task like you asked:\n"
                + task.getTaskDescription() + "\nThere are now " + numTasks + " tasks in your list\n";
    }

    public String printCheck() {
        return "Noted. I've marked this task as complete\n";
    }

    public String printUncheck() {
        return "Noted. I've marked this task as incomplete\n";
    }

    public String printArchive() {
        return "I've archived all your tasks. Please check the archives file to find them";
    }
    public String emptyInput() {
        return "Please input a command\n";
    }
    public String printText(String input) {
        return input;
    }
    /**
     * method displays the list of tasks in a specific format
     * @param searchResults array list of tasks
     */
    public String displaySearchResults(ArrayList<Task> searchResults) {
        StringBuilder output = new StringBuilder("Here are the matching tasks from your list\n:");
        searchResults.forEach((task) -> {
            output.append(task + "\n");
        });
        return output.toString();
    }
}
