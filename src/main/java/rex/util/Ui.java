package rex.util;

import rex.exception.InvalidInputException;
import rex.task.Task;
import rex.task.TaskList;

import java.util.Scanner;

public class Ui {
    // Horizontal line divider
    private static String separation = "____________________________________________________________";

    // "rawr" string added to end of each print statement
    private static String rawr = "rawr";

    // "RAWRRRR" that comes with each error message
    private static String errorPrefix = "RAWRRRR!!!";

    // Scanner placeholder
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readInput() {
        divider();
        return scanner.nextLine();
    }

    public void greeting() {
        divider();
        System.out.println("Hello! I'm rex.Rex! " + rawr);
        System.out.println("What can I do for you? " + rawr);
    }

    public void help() {
        divider();
        System.out.println(Command.getCommandList());
    }

    public void displayList(TaskList list) {
        divider();
        System.out.print(list.getListDisplay());
    }

    public void addTask(Task task) {
        divider();
        System.out.println("Got it. I've added this rex.task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
    }

    public void markTask(Task task) {
        divider();
        System.out.println("Nice! I've marked this rex.task as done:");
        System.out.println("  " + task);
    }

    public void unmarkTask(Task task) {
        divider();
        System.out.println("OK, I've marked this rex.task as not done yet:");
        System.out.println("  " + task);
    }

    public void deleteTask(Task task) {
        divider();
        System.out.println("Noted. I've removed this rex.task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + Task.getNumberOfTasks() + " tasks in the list.");
    }

    public void rawr() {
        divider();
        System.out.println(rawr + "!");
    }

    public void goodbye() {
        divider();
        System.out.println("Bye. Hope to see you again soon! " + rawr);
        divider();
        scanner.close();
    }

    public void errorMessage(InvalidInputException e) {
        errorMessage(e.getMessage());
    }

    public void errorMessage(String message) {
        divider();
        System.out.println(errorPrefix + " " + message);
    }

    private void divider() {
        System.out.println(separation);
    }
}
