package pikappi;

import pikappi.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    static Scanner reader = new Scanner(System.in);

    public Ui() {
    }

    public void greet() {
        System.out.println("Pika! I'm Pikappi!\nWhat can I do for you?\n");
    }

    public void goodbye() {
        System.out.println("Pi-kapi! See you again~\n");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return reader.nextLine();
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    public void showAddedTask(Task task, int taskCount) {
        System.out.println("Pi-ka-pipi! I've added this task:\n " + task.toString() +
                "\nNow you have " + taskCount + " tasks in the list.");
    }

    public void showDeletedTask(Task task, int taskCount) {
        System.out.println("Pipi-ka-pi! I've removed this task:\n " + task.toString() +
                "\nNow you have " + taskCount + " tasks in the list.");
    }

    public void showMatchingTasks(TaskList matchingTasks) {
        if (matchingTasks.getTasks().isEmpty()) {
            System.out.println("Pika..? No matching tasks found..");
        } else {
            System.out.println("Pika! Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.getTasks().size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.getTasks().get(i));
            }
        }
    }

    public void showNoTasks() {
        System.out.println("Pika-ka! You have no tasks!");
    }

    public void showAllTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                System.out.println((i + 1) + "." + tasks.get(i));
            } else {
                break;
            }
        }
    }

    public void showMarkedTask(Task task) {
        System.out.println("Pika! I've marked this task as done:\n" + task);
    }

    public void showUnmarkedTask(Task task) {
        System.out.println("kaPi! I've unmarked this task as not done yet:\n" + task);
    }
}
