package struggling;

import struggling.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final String name = "struggling";
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void show(String str) {
        String line = "____________________________________________________________";
        StringBuilder indent = new StringBuilder();
        for (String s : str.split("\\R")) {
            indent.append(" ").append(s).append("\n");
        }
        String box = String.format("%s\n%s%s", line, indent, line);
        for (String s : box.split("\\R")) {
            System.out.printf("\t%s\n", s);
        }
        System.out.println();
    }

    public void showWelcome() {
        show(String.format("Hello! I'm %s\nWhat can I do for you?", this.name));
    }

    public void showGoodBye() {
        show("Bye. Hope to see you again soon!");
    }

    public void showError(String errorMsg) {
        show(errorMsg);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void list(ArrayList<String> arr) {
        StringBuilder ans = new StringBuilder("Here are the tasks in your list:\n");
        int count = 0;
        for (String s : arr) {
            ans.append(String.format("%d. %s\n", ++count, s));
        }

        if (!ans.isEmpty()) {
            ans.deleteCharAt(ans.length() - 1);
        }

        show(ans.toString());
    }

    public void find(ArrayList<String> arr) {
        StringBuilder ans = new StringBuilder("Here are the matching tasks in your list:\n");
        int count = 0;
        for (String s : arr) {
            ans.append(String.format("%d. %s\n", ++count, s));
        }

        if (!ans.isEmpty()) {
            ans.deleteCharAt(ans.length() - 1);
        }

        show(ans.toString());
    }

    public void showAddTask(Task task, int size) {
        show(String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.", task,
                           size));
    }

    public void showDeleteTask(Task task, int size) {
        show(String.format("Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.", task,
                           size));
    }

    public void showMarkTask(Task task) {
        show(String.format("Nice! I've marked this task as done:\n\t%s", task));
    }

    public void showUnmarkTask(Task task) {
        show(String.format("OK, I've marked this task as not done yet:\n\t%s", task));
    }
}
