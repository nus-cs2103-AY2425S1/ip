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
            this.showLine();
            System.out.println(" Hello! I'm Lexi\n What can I do for you?");
            this.showLine();
    }
    public void showBye() {
        this.showLine();
        System.out.println(" Bye. Hope to see you again soon!");
        this.showLine();
    }
    public String readCommand() {
        Scanner userInput = new Scanner(System.in);
        String response = userInput.nextLine();
        return response;
    }

    public void showAddMessage(Task task, int taskSize) {
        this.showLine();
        System.out.println(" Got it. I've added this task:");
        System.out.printf("   %s%n", task);
        System.out.printf(" Now you have %d task%s in the list.%n", taskSize, taskSize == 1 ? "" : "s");
        this.showLine();
    }

    public void showDeleteMessage(Task task, int taskSize) {
        this.showLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.printf("Now you have %d tasks in the list.%n", taskSize);
        this.showLine();
    }

    public void showLoadingError() {
        System.out.println("Something went wrong with loading data!\n");
    }

    public void showMarkMessage(Task taskToBeMarked) {
        this.showLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + taskToBeMarked);
        this.showLine();
    }

    public void showUnmarkMessage(Task taskToBeMarked) {
        this.showLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + taskToBeMarked);
        this.showLine();
    }

    public void showListOfTasks(ArrayList<Task> tasks) {
        this.showLine();
        System.out.println(" Here are the tasks in your list:");
        for(int i = 0;i<tasks.size();i++) {
            Task currTask = tasks.get(i);
            System.out.printf("  %d. %s%n", i+1, currTask);
        }
        this.showLine();
    }
    public void showError(String message) {
        System.out.println(message);
    }
}
