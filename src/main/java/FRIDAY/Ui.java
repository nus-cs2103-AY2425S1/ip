package FRIDAY;

import java.util.ArrayList;

/**
 * Represents user interface of the app
 */
public class Ui {
    /**
     * Prints out message upon adding of task to the task list.
     *
     * @param task Task object.
     * @param numTasks Integer representing the number of tasks in list.
     */
    public String printAdd(Task task, int numTasks) {
        return "Alright! Your list has been updated :)\n"
                + task.getTaskDescription() + "\nThere are now " + numTasks + " tasks in your list\n";
    }

    /**
     * Returns a message to be displayed on deletion of task from task list.
     *
     * @param task Task object
     * @param numTasks Integer representing the size of task list.
     */
    public String printRemove(Task task, int numTasks) {
        return "Gotchu. I've deleted the task like you asked:\n"
                + task.getTaskDescription() + "\nThere are now " + numTasks + " tasks in your list\n";
    }

    /**
     * Returns a message to be displayed when a user marks a task as complete.
     *
     * @return String representing the response to be displayed when a user marks a task as complete.
     */
    public String printCheck() {
        return "Nice! That task has now been marked as complete\n";
    }

    /**
     * Returns a message to be displayed when a user marks a task as incomplete.
     *
     * @return String representing the response to be displayed when a user marks a task as complete.
     */
    public String printUncheck() {
        return "Okay, that task has been marked as incomplete\n";
    }

    /**
     * Returns a message to be displayed when the user archives all current tasks.
     *
     * @return String representing the response to be displayed when a user archives his/her tasks.
     */
    public String printArchive() {
        return "I've archived all your tasks. Please check the archives file to find them. This file can be found in the storage folder.";
    }

    /**
     * Returns a message to be displayed in the event that the user enters an empty input.
     *
     * @return String representing the message to be displayed in the event that a user enters an empty input.
     */
    public String emptyInput() {
        return "Please input a command\n";
    }
    public String printText(String input) {
        return input;
    }

    /**
     * This method returns a String representing the list of tasks in a specific format.
     *
     * @param searchResults ArrayList representing the list of tasks.
     * @return String representing the list of tasks in a specific format.
     */
    public String displaySearchResults(ArrayList<Task> searchResults) {
        StringBuilder output = new StringBuilder("I've listed all the matching tasks in your list\n:");
        searchResults.forEach((task) -> {
            output.append(task + "\n");
        });
        return output.toString();
    }
}
