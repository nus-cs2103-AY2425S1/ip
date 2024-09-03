package axel;

import java.util.Scanner;
import java.util.List;

public class Ui {
    protected Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("YO! YO! It's axel.Axel, at your service.");
        System.out.println("Hit me up with anything that I can help with!");
        System.out.println("____________________________________________________________");
    }
    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println("Sad to see you go... goodbye!!");
        System.out.println("____________________________________________________________");
    }
    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println("HOLD YOUR HORSES!! " + message);
        System.out.println("____________________________________________________________");
    }
    public String readCommand() {
        return scanner.nextLine();
    }
    public void printTaskAdded(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
    public void printTaskRemoved(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
    public void printTaskDone(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        System.out.println("____________________________________________________________");
    }
    public void printTaskNotDone(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        System.out.println("____________________________________________________________");
    }
    public void printTaskList(List<Task> tasks) {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    public void printMatchingTasks(List<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }
}

