package voidcat.ui;

import voidcat.task.Task;

import java.util.Scanner;

public class Ui {
    private static final String FORMAT = "\t%s%n";
    private final Scanner scanner;

    private static final String logo =
        "### ###   ## ##     ####   ### ##\n" +
        " ##  ##  ##   ##     ##     ##  ##\n" +
        " ##  ##  ##   ##     ##     ##  ##\n" +
        " ##  ##  ##   ##     ##     ##  ##\n" +
        " ### ##  ##   ##     ##     ##  ##\n" +
        "  ###    ##   ##     ##     ##  ##\n" +
        "   ##     ## ##     ####   ### ##\n";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void welcome(String[] greetings, String[] assistGreetings) {
        showLine();
        // Display a random greeting
        System.out.printf(FORMAT, greetings[(int) (Math.random() * greetings.length)]);
        System.out.println(logo.indent(4));
        // Display a random assist greeting
        System.out.printf(FORMAT, assistGreetings[(int) (Math.random() * assistGreetings.length)]);
        showLine();
    }

    public void goodbye(String[] exits) {
        showLine();
        //Display a random exit
        System.out.printf(FORMAT, exits[(int) (Math.random() * exits.length)]);

    }
    public static void showLine() {
        System.out.printf(FORMAT, "------------------------------------------------------------------");
    }

    public static void showMessage(String message ) {
        System.out.printf(FORMAT, message);
    }

    public static void showMessageAndLines(String message ) {
        showLine();
        System.out.printf(FORMAT, message);
        showLine();
    }

    public void showDeleteTaskMessage(Task removedTask, int size) {
        showLine();
        showMessage("Noted. I've removed this task:");
        System.out.printf("\t\t%s%n", removedTask);
        showMessage("Now you have " + size + " tasks in the list.");
        showLine();
    }

    public void showMarkTaskMessage(Task markedTask) {
        showLine();
        showMessage("Good job! I've marked this task as done:");
        System.out.printf("\t\t%s%n", markedTask);
        showLine();
    }

    public void showUnmarkTaskMessage(Task unmarkedTask) {
        showLine();
        showMessage("OK, I've marked this task as not done yet:");
        System.out.printf("\t\t%s%n", unmarkedTask);
        showLine();
    }

    public void showAddTaskMessage(Task task, int size) {
        showLine();
        showMessage( "Got it. I've added this task:");
        System.out.printf("\t\t%s%n", task);
        showMessage("Now you have " + size + " tasks in the list.");
        showLine();
    }
    public void close() {
        scanner.close();
    }
}
