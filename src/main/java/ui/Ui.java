package ui;

import task.Task;
import task.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static common.Messages.*;


public class Ui {
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "_________________________________________";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is only whitespace, or is empty.
     *
     * @param input full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean toIgnore(String input) {
        return input.trim().isEmpty();
    }

    public String readCommand() {
        String inputString = in.nextLine();
        while (toIgnore(inputString)) {
            inputString = in.nextLine();
        }

        return inputString;
    }

    public void greet() {
        out.println(MESSAGE_GREETING);
    }

    public void exit() {
        out.println(MESSAGE_EXIT);
    }

    public void listItems(TaskList tasks) {
        out.println("Here's what we've got on your to-do list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.getTask(i);
            System.out.println(i+1 + "." + t.toString());
        }
    }

    public void mark(Task task) {
        out.println(MESSAGE_MARK + task + LS);
    }

    public void unmark(Task task) {
        out.println(MESSAGE_UNMARK + task + LS);
    }

    public void showTasksOnDate(String date, TaskList tasks) {
        LocalDate localDate = LocalDate.parse(date);
        boolean isFound = false;
        out.println("Tasks on " + localDate + " :");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.occursOn(localDate)) {
                out.println(task);
                isFound = true;
            }
        }
        if (!isFound) {
            out.println(MESSAGE_NO_TASKS);
        }
    }

    public void showDeleted(Task task, int size) {
        out.println("Alright, I've removed this task:\n    " + task + "\nNow you have " + size + " tasks.\n");
    }

    public void showTaskAdded(Task task, int size) {
        out.println("Got it! I've added this task:\n    " + task + "\nNow you have " +
                size + " tasks in your list.\n");
    }

    public void showError(String message) {
        out.println(message);
    }

    public void showLine() {
        out.println(DIVIDER);
    }
}
