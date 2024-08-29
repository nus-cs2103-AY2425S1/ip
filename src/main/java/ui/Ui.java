package ui;

import task.*;
import storage.Storage;
import parser.Parser;

import java.util.Scanner;

public class Ui {

    private static Scanner input = new Scanner(System.in);
    public static void greet() {

        System.out.println("Hello! I'm Tako! What can I do for you?\n");

        promptInput();
    }

    public static void exit() {
        System.out.println("Bye! Have a nice day!");
    }

    public static void printList() {
        System.out.println("Here are your list of tasks:");
        for (int i = 0; i < TaskList.length(); i++) {
            System.out.println(TaskList.listTask(i));
        }

        promptInput();
    }

    public static void addTaskMessage(Task task) {
        System.out.println("Task Received! I've added this task:\n" +
                          task.toString() + "\n" +
                          "Now, you have " + TaskList.length() + " tasks in your list!");
        Storage.store(TaskList.getAllTask());
        promptInput();
    }

    public static void markTaskMessage(Task task) {
        System.out.println("Task has been marked as complete! \n" +
                           task.toString());
        Storage.store(TaskList.getAllTask());
        promptInput();
    }

    public static void unmarkTaskMessage(Task task) {
        System.out.println("Task has been marked as incomplete! \n" +
                           task.toString());
        Storage.store(TaskList.getAllTask());
        promptInput();
    }

    public static void deleteTaskMessage(Task task) {
        System.out.println("Task has been deleted!\n" +
                           task.toString() + "\n" +
                           "Now, you have " + TaskList.length() + " tasks in your list!");
        Storage.store(TaskList.getAllTask());
        promptInput();
    }

    public static void promptInput() {
        String command = input.nextLine();

        Parser.parse(command);
    }
}
