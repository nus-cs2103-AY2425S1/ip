package Ui;

import Task.Task;
import Task.TaskList;

import java.util.Scanner;

public class Ui {

    private static final String LINE_BREAK = "____________________________________";
    private boolean isTerminated;
    private static Scanner scanner = new Scanner(System.in);

    public Ui() {
        this.isTerminated = false;
    }

    public static void printText(String text) {
        System.out.println(LINE_BREAK);
        System.out.println(text);
        System.out.println(LINE_BREAK + "\n");
    }

    public static void printList(TaskList tasks) {
        printText(tasks.toString());
    }

    public static void printAddTask(Task task, TaskList tasks) {
        String output = String.format("Added %s task:\n%s", task.getType(), task);
        output += "\nYou currently have " + tasks.size() + " task/s in your list.";
        printText(output);
    }

    public static void printGreeting() {
        printText("Hello, I'm Rasputin!\nWhat can I do for you?");
    }

    public static void printFarewell() {
        printText("Bye. See you later!");
    }

    public static void printError(String error) {
        printText(error);
    }

    public static String readInput() {
        return scanner.nextLine().trim();
    }
}

