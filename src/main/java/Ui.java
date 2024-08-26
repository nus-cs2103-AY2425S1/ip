package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private String name;

    public Ui(String name) {
        this.scanner = new Scanner(System.in);
        this.name = name;
    }

    public void greet() {
        System.out.println("Hello! I'm " + this.name + "\nWhat can I do for you?");
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        int num = 1;
        for (Task task : tasks) {
            System.out.println(num + ". " + task.getDesc());
            num++;
        }
    }

    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.getDesc());
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.getDesc());
    }

    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.getDesc());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:" + "\n" + task.getDesc());
        if (taskCount == 1) {
            System.out.println("Now you have " + taskCount + " task in the list.");
        } else {
            System.out.println("Now you have " + taskCount + " tasks in the list.");
        }
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
