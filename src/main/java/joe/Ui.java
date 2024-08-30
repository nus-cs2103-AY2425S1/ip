package joe;

import joe.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    //deals with interactions with the user
    public Ui() {

    }

    public void printWelcome() {
        System.out.println("\t" + "Hello! I'm Joe");
        System.out.println("\t" + "What can I do for you?");
    }

    public void printDivider() {
        System.out.println("\t____________________________________________________________");
    }

    public void printExit() {
        System.out.println("\t" + "Bye. Hope to see you again soon!");
    }

    public void printError(String msg) {
        System.out.println("\t" + msg);
    }

    public void printLoadingError() {
        System.out.println("An error occurred while loading data file");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void printUnknownCommand() {
        System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints the array of tasks in order after the given message.
     * @param header The text to be printed before the tasks.
     * @param tasks The array of tasks to be printed
     */
    public void printResponse(String header, ArrayList<Task> tasks) {
        System.out.println("\t" + header);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String msg = String.format("%d. %s", i + 1, task.toString());
            System.out.println("\t" + msg);
        }
    }
}
