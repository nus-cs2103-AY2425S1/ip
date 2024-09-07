package struggling;

import java.util.ArrayList;
import java.util.Scanner;

import struggling.task.Task;

/**
 * Ui class deals with interactions with the user.
 */
public class Ui {
    private final String name = "struggling";
    private final Scanner sc;
    private final StringBuilder message = new StringBuilder();

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Formats provided string and display it.
     */
    public void show(String str) {
        String divider = "____________________________________________________________";
        StringBuilder indent = new StringBuilder();
        for (String s : str.split("\\R")) {
            indent.append(" ").append(s).append("\n");
        }
        String box = String.format("%s\n%s%s", divider, indent, divider);
        for (String s : box.split("\\R")) {
            String line = String.format("\t%s\n", s);
            System.out.printf(line);
            this.message.append(line);
        }
        System.out.println();
        this.message.append("\n");
    }

    /**
     * Displays greeting when the program starts.
     */
    public void showWelcome() {
        show(String.format("Hello! I'm %s\nWhat can I do for you?", this.name));
    }

    /**
     * Displays greeting when the program terminates.
     */
    public void showGoodBye() {
        show("Bye. Hope to see you again soon!");
    }

    /**
     * Displays error message.
     */
    public void showError(String errorMsg) {
        show(errorMsg);
    }

    /**
     * Read the next line of command input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays all Task in TaskList.
     */
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

    /**
     * Display a list of filtered tasks which
     * descriptions contain a keyword.
     *
     * @param arr The filtered list of tasks in string form.
     */
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

    /**
     * Displays the message after a Task is added to TaskList.
     */
    public void showAddTask(Task task, int size) {
        show(String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.", task,
                size));
    }

    /**
     * Displays the message after a Task is removed to TaskList.
     */
    public void showDeleteTask(Task task, int size) {
        show(String.format("Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.", task,
                size));
    }

    /**
     * Displays message after a Task has been marked.
     */
    public void showMarkTask(Task task) {
        show(String.format("Nice! I've marked this task as done:\n\t%s", task));
    }

    /**
     * Displays message after a Task has been unmarked.
     */
    public void showUnmarkTask(Task task) {
        show(String.format("OK, I've marked this task as not done yet:\n\t%s", task));
    }

    public String getMessage() {
        String msg = this.message.toString();
        this.message.setLength(0);
        return msg;
    }
}
