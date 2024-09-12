package tira;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import tira.task.Task;
import tira.task.TaskList;

/**
 * The Ui class handles the user's commands and interacts with the user.
 * It is responsible for reading user input, printing information, and displaying errors.
 * This class serves as an input for TaskList, Storage, and Tira Main.
 */
public class Ui {
    private final Scanner scanner;
    private final PrintWriter printer = new PrintWriter(System.out);

    /**
     * Constructs a Ui instance with a Scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The user's input as a String.
     */
    public String read() {
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void welcome() {
        System.out.println("MIAO! I'm TIRAMISU THE CAT (TIRA)\n"
                + "What can I do for you today, miao?\n");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void bye() {
        System.out.println("Bye. Come back with treats, MIAO!");
    }

    /*
    Solution below inspired by https://github.com/hansneddyanto/ip/blob/master/src/main/java/hana/Ui.java
     */
    /**
     * Shows the current list of tasks to the user.
     *
     * @param taskList The TaskList containing the tasks to display.
     */
    public void showTaskList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        printer.println("HERE ARE THE CURRENT TASKS:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            printer.print((i + 1)
                    + ". "
                    + currTask
                    + "\n");
        }
        printer.flush();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The Task that has been marked as done.
     */
    public void showMarkTask(Task task) {
        printer.println("NYA! Good job on this task:"
                + "\n"
                + task
                + "\n");
        printer.flush();
    }

    /**
     * Displays a message indicating that a task has been unmarked.
     *
     * @param task The Task that has been unmarked.
     */
    public void showUnmarkTask(Task task) {
        printer.println("MRAWWW! Don't forget to return to this task:"
                + "\n"
                + task
                + "\n");
        printer.flush();
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task The Task that has been added.
     * @param taskSize The current size of the task list.
     */
    public void showAddTask(Task task, int taskSize) {
        printer.println("Miao! Got it. I've added this task to my cat brain:\n"
                + task
                + "\nNow you have "
                + taskSize
                + " task(s) in the list!");
        printer.flush();
    }


    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param task The Task that has been removed.
     * @param taskSize The current size of the task list.
     */
    public void showDelete(Task task, int taskSize) {
        printer.println("Noted, Miao! I've removed this task:\n"
                + task
                + "\nNow you have "
                + taskSize
                + " task(s) in the list!");
        printer.flush();
    }

    /**
     * Displays an error message when there is an issue loading the file.
     */
    public void showLoadingError() {
        System.out.println("Oh no... There is an error while loading the file! ");
    }


    /**
     * Displays a message indicating that no matching task was found.
     */
    public void showNoMatchingTask() {
        printer.println("Miao... No such task... Sorry!");
        printer.flush();
    }

    /**
     * Displays a list of tasks that match a search query.
     *
     * @param tasks An ArrayList of matching tasks.
     */
    public void showMatchingTasks(ArrayList<Task> tasks) {
        printer.println("Miao!!!! I found the tasks in my cat brain! They are:");
        for (Task task: tasks) {
            printer.println(task);
        }
        printer.flush();
    }
}
