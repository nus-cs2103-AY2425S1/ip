package susan.ui;

import susan.task.Task;
import susan.task.TaskList;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("~~~");
    }

    /**
     * Greets the user.
     */
    public void showWelcome() {
        System.out.println("Hi there, I'm Susan!");
        System.out.println("How may I help you be productive today?");
        showLine();
    }

    /**
     * Prints farewell message when the user says 'bye'.
     */
    public void showGoodbye() {
        System.out.println("Good bye, slay the day!");
    }

    public void showError(String message) {
        System.out.println("NOT OK. " + message);
    }

    /**
     * Displays the current task list to the user.
     * @param tasks updated TaskList.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(" Here are the tasks in your list:");
        System.out.println(tasks.printList());
    }

    public void showAddTask(Task task, int size) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" You have " + size + " task(s) in the list.");
    }

    /**
     * Informs the user that the task has been deleted.
     * @param task Task that user wants to delete.
     * @param size size of current TaskList.
     */
    public void showDeleteTask(Task task, int size) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" You have " + size + " task(s) in the list.");
    }

    /**
     * Informs the user that the task has been marked as done or undone.
     * @param task Task that user wants to mark/unmark.
     * @param size size of current TaskList.
     */
    public void showMarkTask(Task task, int size) {
        System.out.println(" Alright! I've un/marked this task:");
        System.out.println("   " + task);
        System.out.println(" You have " + size + " task(s) in the list.");
    }

    public void showMatchingTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("Sorry! No tasks found matching your keyword.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            System.out.println(tasks.printList());
        }
    }
}
