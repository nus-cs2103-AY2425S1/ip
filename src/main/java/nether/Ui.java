package nether;

import nether.task.Task;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the Nether logo and welcome message.
     */
    public void printWelcome() {
        String logo = " _   _      _   _        \n"
                + "| \\ | | ___| |_| |__  ___ _ __ \n"
                + "|  \\| |/ _ \\ __| '_ \\/ _ \\ '__|\n"
                + "| |\\  |  __/ |_| | | ||__/ |  \n"
                + "|_| \\_|\\___|\\__|_| |_\\___|_|\n";

        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println("Hello sir! I'm Nether");
        System.out.println("What can I do for you today?");
        printHorizontalLine();
    }

    /**
     * Returns the user input line without leading or trailing whitespaces
     *
     * @return Input string provided by the user without leading or trailing whitespaces
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints the exit/goodbye message to be shown when program is closed.
     */
    public void printExitMessage() {
        System.out.println("Bye. If you need any more help in the future, feel free to ask me. Enjoy your day!");
    }

    /**
     * Prints a message for the user when an invalid/unregistered command is used.
     * @param message Whatever input string provided by the user.
     */
    public void printError(String message) {
        System.out.println("Sir, " + message);
        printHorizontalLine();
    }

    /**
     * Prints out a long horizontal line to act as separator in the chat.
     */
    public void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void printTaskAdded(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
    }

    public void printTaskDeleted(Task task, int size) {
        System.out.println("Noted, I've removed this task from the list:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " task" + (size > 1 ? "s" : "") + " in the list.");
    }

    public void printMarkMessage(Task taskToMark, String markMessage) {
        System.out.println(markMessage);
        System.out.println("  " + taskToMark.toString());
    }
}
