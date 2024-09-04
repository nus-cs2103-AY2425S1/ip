package genji;

import genji.task.Task;
import genji.task.TaskList;
import java.util.Scanner;

public class Ui {
    private final String LINE = "________________________________________________________________";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void welcome() {
        showLine();
        System.out.println("Hello! I'm Genji\nWhat can I do for you?  O.o?");
        showLine();
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void mark(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }

    public void unmark(Task t) {
        System.out.println("OK! I've marked this task as not done yet:");
        System.out.println(t);
    }

    public void add(Task t, TaskList list) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + list.size() + " tasks in the list");
    }

    public void delete(Task t, TaskList list) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + list.size() + " tasks in the list");
    }

    public void find(TaskList list) {
        if (list.size() == 0){
            System.out.println("No matching tasks in your list");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (Task t : list.getList()) {
                System.out.println(t);
            }
        }
    }

    public void showError(String s) {
        System.out.println(s);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

}
