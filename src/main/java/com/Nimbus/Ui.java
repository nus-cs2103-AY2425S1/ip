package com.nimbus;

import java.util.ArrayList;

public class Ui {
    static private String name;

    public Ui(String name) {
        Ui.name = name;
    }

    private void printDash() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Print welcome message onto console
     */
    public void showWelcomeMessage() {
        printDash();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        printDash();
    }

    /**
     * Print goodbye message onto console
     */
    public void showGoodbyeMessage() {
        printDash();
        System.out.println("Bye. Hope to see you again soon!");
        printDash();
    }

    /**
     * Show all task onto console
     * @param tasks tasks to be shown onto console
     */
    public void showAllTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Show a newly added task onto console
     * @param task task to be shown to console
     * @param newSize number of task after adding the task
     */
    public void showAddedTask(Task task, int newSize) {
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + newSize + " tasks in the list.");
    }

    /**
     * Show a removed task onto console
     * @param task task removed
     * @param newSize number of task after removing the task
     */
    public void showRemovedTask(Task task, int newSize) {
        System.out.println("Noted. I've removed this task: " + task);
        System.out.println("Now you have " + newSize + " tasks in the list.");
    }

    /**
     * Show a task has been marked as done
     * @param task task that has been marked as done
     */
    public void showDoneTask(Task task) {
        System.out.println("Nice! I've marked this task as done: " + task);
    }

    /**
     * Show a task has been marked as not done
     * @param task task that has been marked as not done
     */
    public void showNotDoneTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet: " + task);
    }

    /*
    public void notifyInvalidCommand(Task task) {
        System.out.println("Invalid Command");
    }
    */
}
