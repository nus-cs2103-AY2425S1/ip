package ui;

import storage.TaskList;
import task.Task;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    private static final String LINE = "____________________________________________________________";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    private void printLine() {
        System.out.println(LINE);
    }

    public void showGreeting() {
        printLine();
        System.out.println("Hello! I'm Dude!\nWhat can I do for you?");
        printLine();
    }

    public void showGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again!");
        printLine();
    }

    public void showList(TaskList tasks) {
        printLine();
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.getSize(); i++) {
                System.out.println(i + 1 + "." + tasks.getTask(i));
            }
        }
        printLine();
    }

    public void showDeleted(Task task, TaskList tasks) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        printLine();
    }

    public void showAdded(Task task, TaskList tasks) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        printLine();
    }

    public void showMarked(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        printLine();
    }

    public void showUnmarked(Task task) {
        printLine();
        System.out.println(("OK, I've marked this task as not done yet:"));
        System.out.println(task);
        printLine();
    }

    public void invalidCommand() {
        printLine();
        System.out.println("Sorry! I don't know that command!");
        printLine();
    }

    public void showError(String e) {
        printLine();
        System.out.println("Error! " + e);
        printLine();
    }

    public void showLoadingError() {
        printLine();
        System.out.println("Error! An error occurred while loading data from your file!");
        printLine();
    }
}
