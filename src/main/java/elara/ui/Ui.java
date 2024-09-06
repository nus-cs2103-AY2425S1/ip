package elara.ui;

import elara.task.InvalidInputException;
import elara.task.Task;
import elara.task.TaskList;

import java.util.Scanner;

/**
 * Represents the user interface of the Elara chatbot.
 * Handles interactions with the user by displaying messages and reading input.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a new Ui instance and initializes the scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads input from the user.
     *
     * @return The input entered by the user as a string.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message when the chatbot starts.
     */
    public void showWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Elara\nWhat can I do for you?";
        System.out.println(LINE + "\n" + welcomeMessage + "\n" + LINE);
    }

    /**
     * Displays the welcome message when the chatbot starts.
     */
    public void showExitMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(LINE + "\n" + goodbyeMessage + "\n" + LINE);
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param task The task that was added.
     * @param taskList The current task list.
     */
    public void showAddTaskMessage(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list%n", taskList.getSize());
    }

    /**
     * Displays the current number of tasks in the task list.
     *
     * @param taskList The current task list.
     */
    public void showNumOfTasksMessage(TaskList taskList) {
        System.out.printf("Now you have %d tasks in the list%n", taskList.getSize());
    }

    /**
     * Displays a message indicating that a task has been marked as completed.
     *
     * @param task The task that was marked as completed.
     */
    public void showMarkedTaskMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Displays a message indicating that a task has been marked as not completed.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmarkedTaskMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Displays a message indicating that a task has been removed from the task list.
     *
     * @param task The task that was removed.
     */
    public void showRemoveTaskMessage(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    /**
     * Displays all the tasks in the task list.
     *
     * @param taskList The current task list.
     */
    public void listTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i) == null) {
                break;
            }
            System.out.println((i + 1) + ". " + taskList.getTask(i));
        }
    }

    /**
     * Displays an error message when an invalid command is entered by the user.
     *
     * @param e The InvalidInputException thrown due to the invalid command.
     */
    public void showInvalidCommandMessage(InvalidInputException e) {
        System.out.println(e);
    }

    /**
     * Displays an error message if the system encounters an issue loading the task file.
     */
    public void showLoadingErrorMessage() {
        System.out.println("Sorry, but we are having trouble loading file.");
    }

    /**
     * Closes the scanner used for reading input.
     */
    public void closeScanner() {
        scanner.close();
    }
}
