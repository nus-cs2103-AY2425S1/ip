package bean.ui;

import bean.task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine().trim();
    }
    public void showGreeting() {
        System.out.println("______________________________");
        System.out.println("Hello! I'm bean.Bean");
        System.out.println("What can I do for you?");
        System.out.println("______________________________");
    }

    public void showGoodbye() {
        System.out.println("______________________________");
        System.out.println("Bye. Hope to see you again.");
        System.out.println("______________________________");
    }

    public void showTasks(List<Task> tasks) {
        System.out.println("______________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i - 1));
        }
        System.out.println("______________________________");
    }

    public void showMatchingTasks(List<Task> tasks) {
        System.out.println("______________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i - 1));
        }
        System.out.println("______________________________");
    }

    public void showTaskAdded(Task task, int numOfTasks) {
        System.out.println("______________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        System.out.println("______________________________");
    }

    public void showTaskMarked(Task task) {
        System.out.println("______________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println("______________________________");
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("______________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println("______________________________");
    }

    public void showTaskDeleted(Task task, int numOfTasks) {
        System.out.println("______________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        System.out.println("______________________________");
    }

    public void showError(String message) {
        System.out.println("______________________________");
        System.out.println(message);
        System.out.println("______________________________");
    }

    public void showLoadingError() {
        showError("Error loading tasks.");
    }
}
