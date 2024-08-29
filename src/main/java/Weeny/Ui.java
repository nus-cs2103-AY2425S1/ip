package main.java.weeny;
import java.util.List;
import java.util.Scanner;
public class Ui {
    public void showWelcomeMessage() {
        printLine();
        System.out.println("Hello! I'm Weeny\nWhat can I do for you?");
        printLine();
    }

    public void showTaskList(List<Task> tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        printLine();
    }

    public void printTaskAddedMessage(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showTaskDeletedMessage(Task task, int size) {
        printLine();
        System.out.println("Spooof! The task magically disappeared:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        printLine();
    }

    public void showUnmarkMessage(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        printLine();
    }

    public void showMarkMessage(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        printLine();
    }

    public void showError(String message) {
        printLine();
        System.out.println("Error: " + message);
        printLine();
    }

    public void showGoodbyeMessage() {
        printLine();
        System.out.println("Bye. Hope to see you soon!");
        printLine();
    }

    public String readUserInput() {
        Scanner userInput = new Scanner(System.in);
        return userInput.nextLine();
    }

    private void printLine() {
        System.out.println("______________________________________________");
    }
}
