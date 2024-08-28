package pikappi;

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
}
