package Majima.ui;

import Majima.task.Task;
import Majima.task.TaskList;

import java.util.List;
import java.util.Scanner;

public class Ui {

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Runs at the start to greet the user.
     */
    public void userGreet() {
        printLine();
        System.out.println("KIIIIIRYU-CHAN! It's ya old pal, Majima!");
        System.out.println("What can I do fer ya?");
        printLine();
    }

    /**
     * Prints itself as a prefix to an error.
     */
    public void showError(String message) {
        printLine();
        System.out.println("Error: " + message);
        printLine();
    }

    /**
     * Prints out congratulations if there are no tasks, or the list of tasks otherwise.
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            printLine();
            System.out.println("おめでとう, Kiryu-chan! There ain't nothing to do left!");
            printLine();
            return;
        }
        printLine();
        System.out.println("Here's whatcha gotta do, Kiryu-chan!");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        printLine();
    }

    /**
     * Prints to show the user that a task has been successfully added.
     */
    public void showTaskAdded(Task task) {
        printLine();
        System.out.println("Understood, Kiryu-chan! This is goin' into the " +
                "list: " + task);
        printLine();
    }

    /**
     * Prints to show the user that a task has been successfully deleted.
     */
    public void showTaskDeleted(Task task) {
        printLine();
        System.out.println("Gotcha, Kiryu. Axin' this task off the list: "
        + task);
        printLine();
    }

    /**
     * Prints when the 'bye' command is called by the user.
     */
    public void showGoodbye() {
        printLine();
        System.out.println("Bye bye! Don't keep me waiting fer too long now, ya hear?");
        printLine();
    }

    /**
     * Prints to show the user that a task has been successfully marked.
     */
    public void showTaskMarked(Task task) {
        printLine();
        System.out.println("Okay, I've gone ahead and marked that one fer ya.");
        System.out.println("  " + task.toString());
        printLine();
    }

    /**
     * Prints to show the user that a task has been successfully unmarked.
     */
    public void showTaskUnmarked(Task task) {
        printLine();
        System.out.println("Okay, I've gone ahead and unmarked that one fer ya.");
        System.out.println("  " + task.toString());
        printLine();
    }

    public void showFoundTasks(List<Task> tasks, String keyword) {
        if (tasks.isEmpty()) {
            printLine();
            System.out.println("Kiryu! There ain't no tasks matching that description!");
            printLine();
        } else {
            printLine();
            System.out.println("I found these tasks matching yer description:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
            printLine();
        }
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}