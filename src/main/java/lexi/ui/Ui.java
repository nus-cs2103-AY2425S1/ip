package lexi.ui;

import lexi.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static String LINE_BREAK = "____________________________________________________________\n";
    public void showLine() {
        System.out.println(LINE_BREAK);
    }

    public void showWelcome() {
            System.out.println(" Hello! I'm Lexi\n What can I do for you?");
    }
    public void showBye() {
        System.out.println(" Bye. Hope to see you again soon!");

    }
    public String readCommand() {
        Scanner userInput = new Scanner(System.in);
        String response = userInput.nextLine();
        return response;
    }

    public void showAddMessage(Task task, int taskSize) {

        System.out.println(" Got it. I've added this task:");
        System.out.printf("   %s%n", task);
        System.out.printf(" Now you have %d task%s in the list.%n", taskSize, taskSize == 1 ? "" : "s");

    }

    public void showDeleteMessage(Task task, int taskSize) {

        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.printf("Now you have %d tasks in the list.%n", taskSize);

    }

    public void showLoadingError() {
        System.out.println("Something went wrong with loading data!\n");
    }

    public void showMarkMessage(Task taskToBeMarked) {

        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + taskToBeMarked);

    }

    public void showUnmarkMessage(Task taskToBeMarked) {

        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + taskToBeMarked);

    }

    public void showListOfTasks(ArrayList<Task> tasks) {

        if (tasks.size() == 0) {
            System.out.println("You have no tasks in your list!");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.get(i);
                System.out.printf("  %d. %s%n", i + 1, currTask);
            }
        }

    }
    public void showError(String message) {
        System.out.println(message);
    }
}
