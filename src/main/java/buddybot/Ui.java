package buddybot;

import java.util.Scanner;

/**
 * Class for UI
 */
public class Ui {
    private Scanner myObj = new Scanner(System.in);

    /**
     * Allow the user to input
     * @return
     */
    public String readInput() {
        return this.myObj.nextLine();
    }

    /**
     * Closes the Scanner
     */
    public void closeInput() {
        this.myObj.close();
    }

    /**
     * Print a welcome message
     */
    public void welcomeMsg() {
        System.out.println(" Hello! I'm BuddyBot" + "\n" +
                " What can I do for you?");
    }

    /**
     * Print a goodbye message
     */
    public void goodbyeMsg() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public void showLoadingError(String msg) {
        System.out.println(msg);
    }

    /**
     * Print a message when task addition is successful
     * @param task
     * @param i
     */
    public void addTask(Task task, int i) {
        System.out.println("Got it. I've added this task: \n" + task);
        System.out.println("Now you have " + i + " tasks in the list.");
    }

    /**
     * Print a message when deletion is successful
     * @param task
     * @param i
     */
    public void showDelete(Task task, int i) {
        System.out.println("Got it. I've removed this task: \n" + task);
        System.out.printf("Now you have " + i + "\" tasks in the list.\"" );
    }

    /**
     * Print a message when Task is marked as done
     * @param task
     */
    public void showDone(Task task) {
        System.out.println("I've marked this task as done!");
        System.out.printf(task.toString());
    }

    /**
     * Display the list of tasks the user has
     * @param taskList
     */
    public void showList(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("There are no tasks in your list.");
            return;
        }

        System.out.println("These are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + ". " + taskList.get(i));
        }
        System.out.println();
    }

    /**
     * Print an error message from BuddyBotException
     * @param e
     */
    public void showBuddyBotException(BuddyBotException e) {
        System.out.printf(e.getMessage());
    }

    /**
     * Print a message when the end date is before the start date
     */
    public void showInvalidDateRange() {
        System.out.println("\tEnd date must be after the start date!\n");
    }

    /**
     * Print message when date is in the wrong format
     */
    public void showInvalidDateFormat() {
        System.out.println("\tPlease enter the start/end time in the format of <YYYY/MM/DD>!\n");
    }

}
