package rex.util;

import rex.command.Command;
import rex.exception.InvalidInputException;
import rex.task.Task;
import rex.task.TaskList;

import java.util.Scanner;

public class Ui {
    // Horizontal line divider;
    private static final String DIVIDER = "____________________________________________________________";

    // "rawr" string added to end of each print statement
    private static final String RAWR = "rawr";

    // "RAWRRRR" that comes with each error message
    private static final String ERROR_PREFIX = "RAWRRRR!!!";

    // Scanner placeholder
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readInput() {
        printDivider();
        return scanner.nextLine();
    }

    public void greeting() {
        printDivider();
        System.out.println("Hello! I'm Rex! " + RAWR);
        System.out.println("What can I do for you? " + RAWR);
    }

    public void help() {
        printDivider();
        System.out.println(Command.getCommandList());
    }

    public void displayList(TaskList list) {
        printDivider();
        System.out.print(list.getListDisplay());
    }

    public void addTask(Task task) {
        printDivider();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
    }

    public void markTask(Task task) {
        printDivider();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void unmarkTask(Task task) {
        printDivider();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    public void deleteTask(Task task) {
        printDivider();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
    }

    public void rawr() {
        printDivider();
        System.out.println(RAWR + "!");
    }

    public void goodbye() {
        printDivider();
        System.out.println("Bye. Hope to see you again soon! " + RAWR);
        printDivider();
        scanner.close();
    }

    public void errorMessage(InvalidInputException e) {
        errorMessage(e.getMessage());
    }

    public void errorMessage(String message) {
        printDivider();
        System.out.println(ERROR_PREFIX + " " + message);
    }

    private void printDivider() {
        System.out.println(DIVIDER);
    }
}
