package ui;

import task.Task;
import task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void displayWelcome() {
        System.out.println("    ---------------------------------------------------------");
        System.out.println("    Hey there! I'm Buddy\n    What do ya need help with?");
        System.out.println("    ---------------------------------------------------------\n");
    }

    public void displayGoodbye() {
        System.out.println("    Bye! See ya soon!");
    }

    public void displayUpperLineBreak() {
        System.out.println("\n    ---------------------------------------------------------");
    }

    public void displayLowerLineBreak() {
        System.out.println("    ---------------------------------------------------------\n");
    }


    public void displayAddTask(Task task, TaskList list) {
        displayUpperLineBreak();
        System.out.println("    Gotcha! I've added this task: ");
        System.out.printf("         [%s][%s] %s%n", task.getTaskType(), task.getStatusIcon(), task.description);
        System.out.printf("    Now, you have %d tasks in the list!%n", list.getTasks().size());
        displayLowerLineBreak();
    }

    public void displayDeleteTask(Task task, TaskList list) {
        displayUpperLineBreak();
        System.out.println("    Noted. I've removed this task:");
        System.out.printf("      [%s][%s] %s%n", task.getTaskType(), task.getStatusIcon(), task.description);
        System.out.printf("    Now you have %d tasks in the list.%n", list.getTasks().size());
        displayLowerLineBreak();
    }

    public void displayAlreadyMarked() {
        displayUpperLineBreak();
        System.out.println("    Uhh, its already been marked buddy!");
        displayLowerLineBreak();
    }

    public void displayAlreadyUnmarked() {
        displayUpperLineBreak();
        System.out.println("    Uhh, its already been unmarked buddy!");
        displayLowerLineBreak();
    }

    public void displayUnmarkedTask(int index, TaskList list) {
        displayUpperLineBreak();
        System.out.println("    Alright buddy, let's give that task another shot!");
        System.out.printf("    [%s][%s] %s%n", list.getTasks().get(index).getTaskType(), list.getTasks().get(index).getStatusIcon(), list.getTasks().get(index).description);
        displayLowerLineBreak();
    }

    public void displayMarkedTask(int index, TaskList list) {
        displayUpperLineBreak();
        System.out.println("    Nice one buddy! Marked this as done...");
        System.out.printf("    [%s][%s] %s%n", list.getTasks().get(index).getTaskType(), list.getTasks().get(index).getStatusIcon(), list.getTasks().get(index).description);
        displayLowerLineBreak();
    }

    public void displayError(String message) {
        displayUpperLineBreak();
        System.out.println("    OOPS!!! " + message);
        displayLowerLineBreak();
    }

    public void displayTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            displayUpperLineBreak();
            System.out.println("    List is empty!!");
            displayLowerLineBreak();
        } else {
            displayUpperLineBreak();
            System.out.println("    Here are the tasks in your list: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("    %d. [%s][%s] %s%n", i + 1, tasks.get(i).getTaskType(), tasks.get(i).getStatusIcon(), tasks.get(i).description);
            }
            displayLowerLineBreak();
        }
    }

}
