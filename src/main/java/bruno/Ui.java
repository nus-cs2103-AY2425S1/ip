package bruno;

import bruno.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    static Scanner input = new Scanner(System.in);
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void printGreetingMessage() {
        printLine();
        System.out.println("Hello! I'm Bruno\nWhat can I do for you?");
        printLine();
    }
    public static void printByeMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
    public static void printList(ArrayList<Task> taskList) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println((i + 1) + "." + task);
        }
        printLine();
    }
    public static void printAddedTask(Task task, int taskCount) {
        printLine();
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();
    }
    public static void printMarkedTask(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:\n" + task);
        printLine();
    }
    public static void printUnmarkedTask(Task task) {
        printLine();
        System.out.println("Nice! I've unmarked this task as done:\n" + task);
        printLine();
    }
    public static void printDeletedTask(Task task, int taskCount) {
        printLine();
        System.out.println("Noted! I've removed this task:\n" + task);
        System.out.println("Now you have " + taskCount + " tasks in the list");
        printLine();
    }
    public static void showLoadingError() {
        printLine();
        System.out.println("There was an error loading data file. Created new task list.");
        printLine();
    }
    public static void printErrorMessage(Exception e) {
        printLine();
        System.out.println(e.getMessage());
        printLine();
    }
    public static String readCommand() {
        return input.nextLine();
    }
}
