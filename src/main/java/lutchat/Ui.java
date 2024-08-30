package lutchat;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void greet() {
        showLine();
        System.out.println("Hello! I'm lutchat.Lutchat!");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void exit() {
        System.out.println("Bye! Hope to see you again soon!");
        showLine();
    }

    public void showLine() {
        System.out.println("______________________________________________");
    }

    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
        showLine();
    }

    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the task(s) in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        showLine();
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " task(s) in the list.");
        showLine();
    }

    public void showTaskRemoved(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " task(s) in the list.");
        showLine();
    }

    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        showLine();
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        showLine();
    }

    public void showResponse(String message) {
        System.out.println(message);
        showLine();
    }
}
