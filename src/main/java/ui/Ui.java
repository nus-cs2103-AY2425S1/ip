package ui;

import exceptions.AliceException;
import parser.Parser;
import storage.TaskList;
import tasks.Task;

import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "------------------------------------------";
    private final Parser parser;
    private final Scanner scanner;

    public Ui(TaskList list) {
        parser = new Parser(list, this);
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I am Alice!");
        System.out.println("What can I do for you?");
        showDivider();
    }

    public void exitMessage() {
        showDivider();
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public void getInput() {
        while (!parser.isBye()) {
            if (!scanner.hasNext()) {
                continue;
            }
            try {
                parser.parse(scanner.nextLine());
            } catch (AliceException e) {
                System.out.println(e);
                showDivider();
            }
        }
    }

    public void printHandleTaskMessage(Task task, String command) {
        if (command.equals("mark")) {
            showDivider();
            System.out.println("Noted! I've marked this task as done: ");
            System.out.println(task);
            showDivider();
        } else {
            showDivider();
            System.out.println("Ok, I've marked this task as not done yet: ");
            System.out.println(task);
            showDivider();
        }
    }

    public void printHandleTaskMessage(Task task, String command, int size) {
        if (command.equals("delete")) {
            showDivider();
            System.out.println("Noted. I've removed this task: ");
            System.out.println(task);
            System.out.printf("Now you have %d tasks in the list%n", size);
            showDivider();
        } else {
            showDivider();
            System.out.println("Got it. I've added this task: ");
            System.out.println(task);
            System.out.printf("Now you have %d tasks in the list%n", size);
            showDivider();
        }
    }

    public void printListedTasks(String tasks, int size) {
        showDivider();
        System.out.println("Here are the tasks in your list: ");
        System.out.println(tasks);
        System.out.printf("Now you have %d tasks in the list%n", size);
        showDivider();
    }

    /**
     * Prints filtered tasks to the terminal.
     *
     * @param tasks String of tasks to be displayed.
     */
    public void printFilteredTasks(String tasks) {
        showDivider();
        System.out.println("Here are the matching tasks in your list: ");
        System.out.println(tasks);
        showDivider();
    }

    public void showDivider() {
        System.out.println(Ui.DIVIDER);
    }
}
