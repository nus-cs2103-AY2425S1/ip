package kobe.util;

import kobe.task.TaskList;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " K   K   OOOOO   BBBBB   EEEEE \n"
                + " K  K   O     O  B    B  E     \n"
                + " KKK    O     O  BBBBB   EEEE  \n"
                + " K  K   O     O  B    B  E     \n"
                + " K   K   OOOOO   BBBBB   EEEEE \n";
        System.out.println("____________________________________________________________");
        System.out.println("Greetings! I am Kobe Bryant. \n" + logo);
        System.out.println("How can I help you, my man?");
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("OOPS!!! There was an error loading your task list.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showGoodbye() {
        System.out.println("Goodbye! My man.");
    }

    public void showTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is currently empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.getTask(i));
            }
        }
    }

    public void showFindResults(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.getTask(i));
            }
        }
    }
}
