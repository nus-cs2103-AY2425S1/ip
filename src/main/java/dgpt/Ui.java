package dgpt;

import dgpt.task.Task;
import dgpt.task.TaskList;

import java.util.List;

/**
 * The Ui class is responsible for handling the user interface output for the DGPT application.
 * It contains methods to display various messages and information to the user.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("-----------------------");
        System.out.println("DGPT> Hello! I'm DGPT");
        System.out.println("-----------------------");
    }

    /**
     * Displays a line separator in the output.
     */
    public void showLine() {
        System.out.println("-----------------------");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showBye() {
        System.out.println("-----------------------");
        System.out.println("DGPT> Bye. Hope to see you again soon!");
        System.out.println("-----------------------");
    }

    /**
     * Displays an error message indicating that there was an issue loading a file.
     */
    public void showLoadingError() {
        System.out.println("-----------------------");
        System.out.println("Error loading file");
        System.out.println("-----------------------");
    }

    /**
     * Displays an error message indicating that there was an issue reading a file.
     */
    public void showReadingError() {
        System.out.println("-----------------------");
        System.out.println("Error reading file");
        System.out.println("-----------------------");
    }

    /**
     * Displays an error message with the details of an exception.
     *
     * @param e The exception whose message will be displayed.
     */
    public void showError(Exception e) {
        System.out.println("-----------------------");
        System.out.println("ERROR!");
        System.out.println(e.getMessage());
        System.out.println("-----------------------");
    }

    /**
     * Displays a generic error message when an unknown error occurs.
     */
    public void showUnknownError() {
        System.out.println("-----------------------");
        System.out.println("Unknown Error Occurred!");
        System.out.println("-----------------------");
    }

    /**
     * Displays a list of tasks from the provided TaskList.
     *
     * @param taskList The TaskList object containing tasks to be displayed.
     */
    public void showList(TaskList taskList) {
        System.out.println("-----------------------");
        System.out.println("Here are the following items in your list:");
        int index = 1;
        for (Task t : taskList.getTaskList()) {
            System.out.println(index + "." + t.toString());
            index++;
        }
        System.out.println("-----------------------");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param t The Task that has been marked as done.
     */
    public void showMark(Task t) {
        System.out.println("-----------------------");
        System.out.println("DGPT> Nice! I've marked this task as done: ");
        System.out.println(t.toString());
        System.out.println("-----------------------");
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param t The Task that has been marked as not done.
     */
    public void showUnmark(Task t) {
        System.out.println("-----------------------");
        System.out.println("DGPT> OK, I've marked this task as not done yet:");
        System.out.println(t.toString());
        System.out.println("-----------------------");
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param t The Task that has been removed from the list.
     * @param size The updated size of the task list after removal.
     */
    public void showDelete(Task t, int size) {
        System.out.println("-----------------------");
        System.out.println("DGPT> OK, I've removed this task from the list:");
        System.out.println(t.toString());
        System.out.printf("Now you have %d tasks in the list.%n", size);
        System.out.println("-----------------------");
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param t The Task that has been added to the list.
     * @param size The updated size of the task list after addition.
     */
    public void showTask(Task t, int size) {
        System.out.println("-----------------------");
        System.out.println("DGPT> Got it. I've added this task:");
        System.out.println(t.toString());
        System.out.printf("Now you have %d tasks in the list.%n", size);
        System.out.println("-----------------------");
    }

    /**
     * Displays a prompt indicating that the user should provide input.
     */
    public void showUser() {
        System.out.print("User> ");
    }
}
