package ui;

import exceptions.AliceException;
import parser.Parser;
import storage.TaskList;
import tasks.Task;

import java.util.Scanner;

/**
 * Responsible for printing output to the terminal, and reading input from the terminal.
 * Encapsulates a parser and a scanner for reading user input.
 */
public class Ui {
    private static final String DIVIDER = "------------------------------------------";
    private final Parser parser;
    private final Scanner scanner;

    public Ui(TaskList list) {
        parser = new Parser(list, this);
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message to the terminal.
     */
    public void showWelcome() {
        System.out.println("Hello! I am Alice!");
        System.out.println("What can I do for you?");
        showDivider();
    }

    /**
     * Prints the exit message to the terminal.
     */
    public void exitMessage() {
        showDivider();
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    /**
     * Gets user inputs from the terminal.
     */
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

    /**
     * Prints message to terminal when task is marked or unmarked.
     *
     * @param task the task which was handled.
     * @param command "mark" or "unmark".
     */
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

    /**
     * Prints message to the terminal when task is deleted or added.
     *
     * @param task the task that was handled.
     * @param command "delete" or "todo" or "deadline" or "event".
     * @param size number of tasks in the list.
     */
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

    /**
     * Prints all the tasks in the list.
     * 
     * @param tasks the string representation of all the tasks.
     * @param size number of tasks in the list.
     */
    public void printListedTasks(String tasks, int size) {
        showDivider();
        System.out.println("Here are the tasks in your list: ");
        System.out.println(tasks);
        System.out.printf("Now you have %d tasks in the list%n", size);
        showDivider();
    }

    /**
     * Prints the divider to the terminal.
     */
    public void showDivider() {
        System.out.println(Ui.DIVIDER);
    }
}
