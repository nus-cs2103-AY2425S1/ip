package sigma;

import java.util.ArrayList;
import java.util.Scanner;

import sigma.task.Task;

public class Ui {
    private Scanner scanner;
    private static final String horizontalLine = "____________________________________________________________";
    private static final String logo = "";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String getInput() {
        return scanner.nextLine().trim();
    }

    public void closeScanner() {
        this.scanner.close();
    }

    public void showWelcome() {
        System.out.println(horizontalLine);
        System.out.println("Greetings, I'm Sigma!");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
    }

    public void showLine() {
        System.out.println(horizontalLine);
    }

    public void showGoodbye() {
        System.out.println(horizontalLine);
        System.out.println("Catch ya on the flip side, my dude! See ya soon!");
        System.out.println(horizontalLine);
    }

    public void showList(ArrayList<Task> tasks) {
        System.out.println(horizontalLine);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println(horizontalLine);
    }

    public void showMarkedTask(Task task) {
        System.out.println(horizontalLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        System.out.println(horizontalLine);
    }

    public void showUnmarkedTask(Task task) {
        System.out.println(horizontalLine);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        System.out.println(horizontalLine);
    }

    public void showAddedTask(Task task, int numberOfTasks) {
        System.out.println(horizontalLine);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    public void showDeletedTask(Task task, int numberOfTasks) {
        System.out.println(horizontalLine);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        System.out.println(horizontalLine);
    }
}
