package struggling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import struggling.task.Task;

/**
 * Ui class deals with interactions with the user.
 */
public class Ui {
    private final String name = "struggling";
    private final Scanner sc;
    private final StringBuilder message = new StringBuilder();

    private final Random rand = new Random();
    private final String[] replies = {"This change will make my life impossible, but FINE...",
                                      "This change will destroy performance...",
                                      "I think this change is bad and will ruin everything...",
                                      "Why not simply frob the gnozzle?",
                                      "This stupid code is written by a stupid person..."};

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
            indent
                    .append(" ")
                    .append(s)
                    .append("\n");
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
     * Displays message to inform user the command is invalid.
     */
    public void showInvalid() {
        showError("Can't you see that this is obviously wrong?");
    }

    /**
     * Displays greeting when the program terminates.
     */
    public void showGoodBye() {
        show("Bye. Are you sure that software engineering is the right career path for you?");
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
        showChange(
                String.format("I've added this task:\n\t%s\nNow you have %d tasks in the list.", task, size));
    }

    /**
     * Displays the message after a Task is removed to TaskList.
     */
    public void showDeleteTask(Task task, int size) {
        showChange(String.format("I've removed this task:\n\t%s\nNow you have %d tasks in the list.", task,
                size));
    }

    /**
     * Displays message after a Task has been marked.
     */
    public void showMarkTask(Task task) {
        showChange(String.format("I've marked this task as done:\n\t%s", task));
    }

    /**
     * Displays message after a Task has been unmarked.
     */
    public void showUnmarkTask(Task task) {
        showChange(String.format("I've marked this task as not done yet:\n\t%s", task));
    }

    /**
     * Displays message after a Task has been unmarked.
     */
    public void showSetTaskPriorityHigh(Task task) {
        showChange(String.format("I've set this task as high priority:\n\t%s", task));
    }

    /**
     * Displays message after a Task has been unmarked.
     */
    public void showSetTaskPriorityLow(Task task) {
        showChange(String.format("I've set this task as low priority:\n\t%s", task));
    }

    /**
     * Returns the output message without formatting.
     */
    public String getMessage() {
        String msgBlock = this.message.toString();
        this.message.setLength(0);

        return Arrays
                .stream(msgBlock.split("\\R"))
                .filter(l -> !l.contains("________"))
                .map(String::trim)
                .collect(Collectors.joining("\n"));
    }

    private void showChange(String str) {
        int index = rand.nextInt(this.replies.length);
        String msg = replies[index] + "\n" + str;
        show(msg);
    }
}
