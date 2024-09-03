package duck.ui;

import java.util.Scanner;

import duck.task.Task;
import duck.task.TaskList;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs an Ui instance and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Show welcome message when Duck starts running.
     */
    public void showWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Duck\nWhat can I do for you?";
        System.out.println(LINE + "\n" + welcomeMessage + "\n" + LINE);
    }

    /**
     * Show goodbye message when user inputs bye.
     */
    public void showGoodbyeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(LINE + "\n" + goodbyeMessage + "\n" + LINE);
    }

    /**
     * Show message when task is added.
     */
    public void showAddTaskMessage(Task task, TaskList list) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
    }

    /**
     * Show the number of tasks message.
     */
    public void showNumOfTasksMessage(TaskList list) {
        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
    }

    /**
     * Show message when task is marked.
     */
    public void showMarkedTaskMessage(Task task) {
        System.out.println("Nice! I've marked this task as done: " + task);
    }

    /**
     * Show message when task is unmarked.
     */
    public void showUnmarkedTaskMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet: " + task);
    }

    /**
     * Displays all tasks in the current task list.
     *
     * @param list the current task list
     */
    public void showTasks(TaskList list) {
        System.out.println(LINE + "\nHere are the tasks in your list:");
        for (int i = 0; i < list.getSize(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + list.getTask(i));
        }
        System.out.println(LINE);
    }

    /**
     * Closes the scanner used for reading user input.
     */
    public void closeScanner() {
        scanner.close();
    }

    public void showInvalidCommand() {
        System.out.println("Invalid command. Please try again.");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }
}
