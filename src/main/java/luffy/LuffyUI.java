package luffy;
import java.util.Scanner;

import static java.lang.String.format;


/**
 * Represents a UI for the chatbot that deals with
 * user interaction
 */
public class LuffyUI {

    private final Scanner commandScanner;

    public LuffyUI() {
        this.commandScanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * This method prints out a welcome message when the chatbot is started
     */
    public void showWelcomeMessage() {
        showLine();
        System.out.println(" Hello! I'm luffy.Luffy");
        System.out.println(" What can I do for you?");
        showLine();
    }

    /**
     * This method prints out the marked task when a task is marked
     */
    public void showMarkedTask(Task task) {
        showLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("  " + task.stringIsDone());
        showLine();
    }

    /**
     * This method prints out the unmarked task when a task is unmarked
     */
    public void showUnmarkedTask(Task task) {
        showLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("  " + task.stringIsDone());
        showLine();
    }

    /**
     * This method prints out the added task when a task is added
     *
     * @param task the task that was added to the list
     * @param taskList the task list that the task will be added to
     */
    public void showAddedTask(Task task, TaskList taskList) {
        showLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + task.stringIsDone());
        System.out.println(String.format(" Now you have %s tasks in the list.", taskList.size()));
        showLine();
    }

    /**
     * This method prints out the added task when a task is deleted
     *
     * @param task the task that will be deleted from the list
     * @param taskList the task list that the task will be deleted from
     */
    public void showDeletedTask(Task task, TaskList taskList) {
        showLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("  " + task.stringIsDone());
        System.out.println(String.format(" Now you have %s tasks in the list.", taskList.size()));
        showLine();
    }

    /**
     * This method prints out a goodbye message when the chatbot is ended
     */
    public void showExitMessage() {
        showLine();
        System.out.println(" Bye. Hope to see you again!");
        showLine();
    }

    /**
     * This method prints out a match message when the chatbot finds tasks with
     * the matching keywords by user
     */
    public void showMatchingMessage() {
        showLine();
        System.out.println(" Here are the matching tasks in your list:");
    }

    /**
     * This method prints out an error message when the user inputs an
     * invalid command
     *
     * @param errorMsg the error message to be displayed
     */
    public void showErrorMessage(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Returns a string of the next user command
     *
     * @return string of the next user command
     */
    public String readNextCommand() {
        return commandScanner.nextLine();
    }

    /**
     * This method prints out the list of the users' task
     *
     * @param taskList the target list to be printed over
     */
    public void displayTasks(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(format(" %d.%s", i + 1, taskList.getTask(i).stringIsDone()));
        }
    }
}
