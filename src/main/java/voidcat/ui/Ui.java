package voidcat.ui;

import voidcat.task.Task;

import java.util.Scanner;

public class Ui {
    private static final String FORMAT = "\t%s%n";
//    private final Scanner scanner;

    private static final String logo =
        "### ###   ## ##     ####   ### ##\n" +
        " ##  ##  ##   ##     ##     ##  ##\n" +
        " ##  ##  ##   ##     ##     ##  ##\n" +
        " ##  ##  ##   ##     ##     ##  ##\n" +
        " ### ##  ##   ##     ##     ##  ##\n" +
        "  ###    ##   ##     ##     ##  ##\n" +
        "   ##     ## ##     ####   ### ##\n";

//    public Ui() {
//        this.scanner = new Scanner(System.in);
//    }

//    public String readCommand() {
//        return scanner.nextLine();
//    }

    public static String welcome(String[] greetings, String[] assistGreetings) {
        // Display a random greeting
        return greetings[(int) (Math.random() * greetings.length)] + logo.indent(4) + assistGreetings[(int) (Math.random() * assistGreetings.length)];
//        System.out.println(logo.indent(4));
//        // Display a random assist greeting
//        System.out.printf(FORMAT, assistGreetings[(int) (Math.random() * assistGreetings.length)]);
//        showLine();
    }

    public static String goodbye(String[] exits) {
        //Display a random exit
        return exits[(int) (Math.random() * exits.length)];

    }

    public static String showMessage(String message) {
        return message;
    }

//    public static void showMessageAndLines(String message ) {
//        showLine();
//        System.out.printf(FORMAT, message);
//        showLine();
//    }

    public static String showDeleteTaskMessage(Task removedTask, int size) {
        return "Noted. I've removed this task:\n\t\t" + removedTask + "\nNow you have " + size + " tasks in the list";

    }

    public static String showMarkTaskMessage(Task markedTask) {
        return "Good job! I've marked this task as done:\n\t\t" + markedTask;
    }

    public static String showUnmarkTaskMessage(Task unmarkedTask) {
        return "OK, I've marked this task as not done yet:\n\t\t" + unmarkedTask;
    }

    public static String showAddTaskMessage(Task task, int size) {
        return "Got it. I've added this task:\n\t\t" + task + "\nNow you have " + size + " tasks in the list";
    }
//    public void close() {
//        scanner.close();
//    }
}
