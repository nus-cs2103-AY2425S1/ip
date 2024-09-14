package beechat.util;

import beechat.task.TaskList;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Beechat!\nWhat can I do for you?\n");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        System.out.println("There was an error loading your task list.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks so far");
        } else {
            System.out.println("Here are all the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(String.format("%d. %s", i + 1, tasks.getTask(i)));
            }
        }
    }
}
