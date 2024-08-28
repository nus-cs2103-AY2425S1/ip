package com.example.YourHelperBuddy;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm YourHelperBuddy.");
        System.out.println("How may I assist you today?");
        System.out.println("________________________________________________");
    }

    public void showGoodbyeMessage() {
        System.out.println("________________________________________________");
        System.out.println("Goodbye. Take care and see you again!");
        System.out.println("________________________________________________");
    }

    public void showTaskList(TaskList taskList) {
        int index = 0;
        System.out.println("________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskList.getTasks()) {
            System.out.println(++index + ". " + task);
        }
        System.out.println("________________________________________________");
    }

    public void showTaskAdded(Task task, int taskListsize) {
        System.out.println("________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskListsize + " tasks in the list.");
        System.out.println("________________________________________________");
    }

    public void showTaskRemoved(Task task, TaskList taskList) {
        System.out.println("________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("________________________________________________");
    }

    public void showTaskMarked(Task task) {
        try {
            System.out.println("________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            task.markDone();
            System.out.println("  " + task);
            System.out.println("________________________________________________");
        }
        catch (Exception e) {
            System.out.println("________________________________________________");
            System.out.println("Oops! There seems to be an issue with the task number you provided.");
            System.out.println("________________________________________________");
        }
    }

    public void showTaskUnmarked(Task task) {
        try {
            System.out.println("________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            task.markUndone();
            System.out.println("  [" + task);
            System.out.println("________________________________________________");
        }
        catch (Exception e) {
            System.out.println("________________________________________________");
            System.out.println("Oops! There seems to be an issue with the task number you provided.");
            System.out.println("________________________________________________");
        }
    }

    public String readUserInput() {
        System.out.println("Enter your task: ");
        return scanner.hasNextLine() ? scanner.nextLine() : "";
    }

    public void close() {
        scanner.close();
    }
}
