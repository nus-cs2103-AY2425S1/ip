package Majima.ui;

import Majima.task.Task;
import Majima.task.TaskList;

import java.util.Scanner;

public class Ui {

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void userGreet() {
        printLine();
        System.out.println("KIIIIIRYU-CHAN! It's ya old pal, Majima!");
        System.out.println("What can I do fer ya?");
        printLine();
    }

    public void showError(String message) {
        printLine();
        System.out.println("Error: " + message);
        printLine();
    }

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

    public void showTaskAdded(Task task) {
        printLine();
        System.out.println("Understood, Kiryu-chan! This is goin' into the " +
                "list: " + task);
        printLine();
    }

    public void showTaskDeleted(Task task) {
        printLine();
        System.out.println("Gotcha, Kiryu. Axin' this task off the list: "
        + task);
        printLine();
    }

    public void showGoodbye() {
        printLine();
        System.out.println("Bye bye! Don't keep me waiting fer too long now, ya hear?");
        printLine();
    }

    public void showTaskMarked(Task task) {
        printLine();
        System.out.println("Okay, I've gone ahead and marked that one fer ya.");
        System.out.println("  " + task.toString());
        printLine();
    }

    public void showTaskUnmarked(Task task) {
        printLine();
        System.out.println("Okay, I've gone ahead and unmarked that one fer ya.");
        System.out.println("  " + task.toString());
        printLine();
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}