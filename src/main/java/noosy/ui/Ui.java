package noosy.ui;

import noosy.task.Task;
import noosy.task.TaskList;

import java.util.Scanner;

/**
 * Handles user interface interactions for the task management system.
 */
public class Ui {

    /**
     * Reads a command from the user input.
     *
     * @return The user's input as a String.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Heyo! This is Noosy! \n" +
                "Noosy is da best, tell me what you need! :>");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("Alright, hope that helped. \n" +
                "See ya!");
    }

    /**
     * Displays a message confirming that a task has been added to the list.
     *
     * @param tasks The updated TaskList.
     * @param task The Task that was added.
     */
    public void showTaskAdded(TaskList tasks, Task task) {
        System.out.println("I added it to the list! \n" + task);
        System.out.println("We've now got " + tasks.size() + " tasks in the bucket!");
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param done The Task that was completed.
     */
    public void showTaskDone(Task done) {
        System.out.println("Hooray you've done this: \n" + done);
    }

    /**
     * Displays a message confirming that a task has been marked as undone.
     *
     * @param undone The Task that was marked as undone.
     */
    public void showUndone(Task undone) {
        System.out.println("Ok don't worry, you can continue working on this: \n" + undone);
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param deleted The Task that was deleted.
     */
    public void showDeleted(Task deleted) {
        System.out.println("I've kicked it out for you: \n" + deleted);
    }

    /**
     * Displays the list of all tasks.
     *
     * @param tasks The TaskList to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Heyo here are the task(s) we have: \n");
        tasks.printTasks();
    }

    /**
     * Displays an error message for invalid date input.
     */
    public void showInvalidDate() {
        System.out.println("Please enter the date in the format yyyy-MM-dd:");
    }

    /**
     * Displays a general error message.
     *
     * @param errorMsg The error message to be displayed.
     */
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Displays an error message for task loading failure.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks");
    }
}
