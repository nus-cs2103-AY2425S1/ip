package duck.ui;

import java.util.Scanner;

import duck.task.Task;
import duck.task.TaskList;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {
    private Scanner scanner;
    private String lastResponse;

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
        this.lastResponse = "\n" + welcomeMessage + "\n";
        System.out.println(this.lastResponse);
    }

    /**
     * Show goodbye message when user inputs bye.
     */
    public void showGoodbyeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        this.lastResponse = "\n" + goodbyeMessage + "\n";
        System.out.println(this.lastResponse);
    }

    /**
     * Show message when task is added.
     */
    public void showAddTaskMessage(Task task, TaskList list) {
        this.lastResponse = "Got it. I've added this task:" + task;
        this.lastResponse += "\nNow you have " + list.getSize() + " tasks in the list.";
        System.out.println(this.lastResponse);
    }

    /**
     * Show the number of tasks message.
     */
    public void showNumOfTasksMessage(TaskList list) {
        this.lastResponse = "Now you have " + list.getSize() + " tasks in the list.";
        System.out.println(this.lastResponse);
    }

    /**
     * Show message when task is marked.
     */
    public void showMarkedTaskMessage(Task task) {
        this.lastResponse = "Nice! I've marked this task as done: " + task;
        System.out.println(this.lastResponse);
    }

    /**
     * Show message when task is unmarked.
     */
    public void showUnmarkedTaskMessage(Task task) {
        this.lastResponse = "OK, I've marked this task as not done yet: " + task;
        System.out.println(this.lastResponse);
    }

    /**
     * Displays all tasks in the current task list.
     *
     * @param list the current task list
     */
    public void showTasks(TaskList list) {
        this.lastResponse = "Here are the tasks in your list: ";
        for (int i = 0; i < list.getSize(); i++) {
            int num = i + 1;
            this.lastResponse += "\n" + num + ". " + list.getTask(i);
        }
        System.out.println(this.lastResponse);
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

    /**
     * Returns the last response from Duck.
     *
     * @return The last response.
     */
    public String getLastResponse() {
        assert this.lastResponse != null;
        return this.lastResponse;
    }
}
