package tudee.ui;

import tudee.task.Task;

import java.util.Scanner;
import java.util.List;

public class Ui {
    private final Scanner sc;
    private static final String STRAIGHTLINE = "____________________________________________________________ \n";

    public Ui() {
        sc = new Scanner(System.in);
    }
    public void showLine() {
        System.out.println(STRAIGHTLINE);
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Tudee, your chatbot bestie! \nHow can I help you today? :) \n");
        showLine();
    }

    public void showBye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon! Don't forget about me :( \n");
        showLine();
    }

    public void showTaskList(List<Task> tasks) {
        showLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
        showLine();
    }

    public void showMark(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done: \n" + task);
        showLine();
    }

    public void showUnMark(Task task) {
        showLine();
        System.out.println("Ok, I've marked this task as not done yet: \n" + task);
        showLine();
    }

    public void showAdd(Task task, int count) {
        showLine();
        System.out.println("Got it. I've added this task: \n  " + task);
        System.out.println("Now you have " + count + " tasks in the list. \n");
        showLine();
    }

    public void showDelete(Task task, int count) {
        showLine();
        System.out.println("Noted. I've removed this task: \n" + task);
        System.out.println("Now you have " + count + " tasks in the list. \n");
        showLine();
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    /**
     * Displays the task name to the user.
     * @param task The task we want to be displayed.
     */
    public void showTask(Task task) {
        System.out.println(task);
    }

    public void close() {
        if (sc != null) {
            sc.close();
        }
    }
}
