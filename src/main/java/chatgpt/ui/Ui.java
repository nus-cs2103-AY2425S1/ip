package chatgpt.ui;

import java.util.Scanner;

import chatgpt.task.Task;
import chatgpt.task.TaskList;

/**
 *  The Ui class handles receiving inputs and displaying of messages.
 */
public class Ui {
    /** Represents Name of the chatbot **/
    private static final String NAME = "ChatGPT";
    /** Represents the line to display between each message/input **/
    private static final String LINE = "________________________________________________";
    /** The Scanner that reads the input from the users **/
    private Scanner inputReader;

    /**
     * Default constructor that sets the scanner to be System.in.
     */
    public Ui() {
        this.inputReader = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("\tHello! I'm " + NAME);
        System.out.println("\tWhat can I do for you?");
        showLine();
    }

    /**
     * Displays the exit message.
     */
    public void showExit() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Displays the line that should be between messages.
     */
    public void showLine() {
        System.out.println("\t" + LINE);
    }

    /**
     * Returns the input from the users as a String.
     *
     * @return Input from user until a new line is captured
     */
    public String readCommand() {
        return inputReader.nextLine();
    }

    /**
     * Displays the error message from any exceptions caught.
     *
     * @param errorMessage of the exception that was caught
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays the message for adding of a task and
     * how many task there are left in the list.
     *
     * @param task that is being added
     * @param taskNum is the number of task in the list after addition
     */
    public void showAddTask(Task task, int taskNum) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task.toString());
        System.out.println("\tNow you have " + taskNum + " tasks in your list.");
    }

    /**
     * Displays the message for deleting of a task and
     * how many task there are left in the list.
     *
     * @param task that is being deleted
     * @param taskNum is the number of task in the list after deletion
     */
    public void showDeleteTask(Task task, int taskNum) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task.toString());
        System.out.println("\tNow you have " + taskNum + " tasks in your list.");
    }

    /**
     * Displays the message for marking a task as complete.
     *
     * @param task that is completed
     */
    public void showCompleteTask(Task task) {
        System.out.println("\t Nice! I've marked this task as done: \n\t  "
                + task.toString());
    }

    /**
     * Displays the message for marking a task as not complete.
     *
     * @param task that is not completed
     */
    public void showUncompleteTask(Task task) {
        System.out.println("\t OK, I've marked this task as not done yet: \n\t  "
                + task.toString());
    }

    /**
     * Displays the task within the list.
     * Displays an empty list message when the given list is empty
     *
     * @param tasks is the list of Task to be displayed
     */
    public void showList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("\tNothing has been added");
        } else {
            System.out.println("\tHere are the tasks in your list:");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Displays the tasks that contain the keyword.
     * Displays a nothing found message when the given list is empty
     *
     * @param tasks is the list of Task that contains the keyword to be displayed
     */
    public void showFound(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("\tNothing with that keyword was found");
        } else {
            System.out.println("\tHere are the matching tasks in your list:");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Displays an error message when there is a problem loading data from the save file.
     */
    public void showLoadingError() {
        showLine();
        System.out.println("\tThere was a problem with the save file");
        System.out.println("\tYou can either: ");
        System.out.println("\t(1) Fix the save file manually and restart the program");
        System.out.println("\t(2) Start from scratch");
    }
}
