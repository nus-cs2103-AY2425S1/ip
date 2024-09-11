package kietwoforone.ui;

import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.tasks.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents what the chatbot displays depending on user input.
 */
public class UI {

    private static String separationLine = "_________________________________________";
    private static String chatBotName = "KieTwoForOne";
    private static Scanner scanner;

    /**
     * Constructor for UI Object.
     * Initialises a scanner to read keyboard input.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the string obtained from the input keyed in by the user.
     *
     * @return User input.
     */
    public static String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the separation line.
     */
    public static void showLine() {
        System.out.println(separationLine);
    }

    /**
     * Prints the welcome message.
     */
    public static void showWelcome() {
        showLine();
        System.out.println("Hello! I'm " + chatBotName + ".");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Prints the message displayed when a task is added to the ArrayList.
     *
     * @param tasks
     * @param newTask
     */
    public void showAddTasks(ArrayList<Task> tasks, Task newTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + newTask);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    /**
     * Prints the message displayed when a task is deleted from the ArrayList.
     *
     * @param tasks
     * @param removedTask
     */
    public void showDeleteTask(ArrayList<Task> tasks, Task removedTask) {
        System.out.println("Noted. I've removed the task");
        System.out.println("    " + removedTask);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    /**
     * Prints the task list.
     *
     * @param tasks
     */
    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks.get(i).toString()));
        }
    }

    /**
     * Prints the message when a task is marked.
     *
     * @param task
     */
    public void showMarkTask(String task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + task);
    }

    /**
     * Prints the message when a task is unmarked.
     *
     * @param task
     */
    public void showUnmarkTask(String task) {
        System.out.println("OK. I've marked this task as incomplete:");
        System.out.println("    " + task);
    }

    /**
     * Prints all the tasks where the inputted date lies within.
     *
     * @param tasks
     * @param date
     * @throws KieTwoForOneException
     */
    public void showSameDate(ArrayList<Task> tasks, String date) throws KieTwoForOneException {
        ArrayList<Task> taskList = new ArrayList<>(100);
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.compareDate(date)) {
                taskList.add(currTask);
            }
        }
        System.out.println("Here are the tasks occurring on this date:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, taskList.get(i).toString()));
        }
    }

    /**
     * Prints the error message when an exception is thrown.
     *
     * @param e
     */
    public void showErrorMessage(KieTwoForOneException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints the message when the user exits the chatbot.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
