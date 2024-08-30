package dudu.utils;

import dudu.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private static String welcomeMessage = LineWrapper.wrap("Hello! I'm dudu\n"
            + "What can I do for you?");
    private static String goodbyeMessage = LineWrapper.wrap("Bye. Hope to see you again soon!");

    private Scanner sc;

    public UI() {
        this.sc = new Scanner(System.in);
    }

    public void welcomeMessage() {
        System.out.println(UI.welcomeMessage);
    }

    public void goodbyeMessage() {
        System.out.println(goodbyeMessage);
        this.sc.close();
    }

    public void helpMessage() {
        System.out.println("Please use help to get the list of commands");
    }

    public void showError(Exception e) {
        System.out.println(LineWrapper.wrap(e.toString()));
    }

    public void addTask(Task task, int size) {
        String output = LineWrapper.wrap(String.format("Got it. I've added this task:\n    %s\nNow you have %d tasks in the list.", task, size));
        System.out.println(output);
    }

    public void markTask(Task task) {
        String output = LineWrapper.wrap(String.format("Nice! I've marked this task as done:\n    %s", task));
        System.out.println(output);
    }

    public void unmarkTask(Task task) {
        String output = LineWrapper.wrap(String.format("OK, I've marked this task as not done yet:\n    %s", task));
        System.out.println(output);
    }

    public void deleteTask(Task task) {
        String output = LineWrapper.wrap(String.format("Noted. I've removed this task:\n    %s", task));
        System.out.println(output);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void printTasks(TaskList tasklist) {
        ArrayList<Task> tasks = tasklist.getTasks();
        if (tasks.isEmpty()) {
            System.out.println(LineWrapper.wrap("No tasks"));
        } else {
            StringBuilder output = new StringBuilder("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                output.append("\n" + (i + 1) + ". " + tasks.get(i));
            }
            System.out.println(LineWrapper.wrap(output.toString()));
        }
    }

    public void findTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(LineWrapper.wrap("No matching tasks in your list"));
        } else {
            StringBuilder output = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                output.append("\n" + (i + 1) + ". " + tasks.get(i));
            }
            System.out.println(LineWrapper.wrap(output.toString()));
        }
    }
}
