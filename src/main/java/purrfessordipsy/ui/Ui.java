package purrfessordipsy.ui;

import purrfessordipsy.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getInput() {
        out.print("Enter command: ");
        return in.nextLine().trim();
    }

    private void printWithTerminalLines(String message) {
        String TERMINAL_LINE = "―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――";
        out.println(TERMINAL_LINE);
        out.println(message);
        out.println(TERMINAL_LINE);
    }

    public void printWelcomeMessage() {
        String introduction = "Meowdy! I'm Purrfessor Dipsy, Keeper of the Cozy Sunbeam " +
                "and Purrtector of the Realm of Naps.\n" +
                "How can I purrvide assistance? Purrhaps I could lend a paw!";
        printWithTerminalLines(introduction);
    }

    public void printExitMessage() {
        printWithTerminalLines("Fur-well friend, stay paw-sitive!");
    }

    public void printErrorMessage(String message) {
        printWithTerminalLines("Error: " + message);
    }

    public void printTaskAddedMessage(Task task, int numberOfTasks) {
        printWithTerminalLines("(=ↀωↀ=)ノ Task added!\n" + task +
                "\nYou now have " + numberOfTasks + " tasks in your list.");
    }

    public void printTaskDeletedMessage(Task task, int numberOfTasks) {
        printWithTerminalLines("Purrr, I've swatted this task away:\n" + task +
                "\nYou now have " + numberOfTasks + " tasks in your list.");
    }

    public void printMarkTaskDoneMessage(Task task) {
        printWithTerminalLines("Meow! I’ve scratched this task off the list!\n" + task);
    }

    public void printMarkTaskUndoneMessage(Task task) {
        printWithTerminalLines("Mrrreow! I’ve batted this task back onto the list.\n" + task);
    }

    public void printListOfTasks(ArrayList<Task> tasks) {
        int taskCount = tasks.size();
        if (taskCount == 0) {
            printWithTerminalLines("Your task list is as empty as a well-sunned nap spot.");
        } else {
            printTasks("Time to stretch those paws and tackle your tasks!\n", tasks);

        }
    }

    public void printTasksMatchingKeyword(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            printWithTerminalLines("There are no tasks in your list that match the keyword.");
        } else {
            printTasks("Here is the list of matching tasks:\n", tasks);
        }
    }

    private void printTasks(String header, ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder(header + "\n");
        int taskCount = tasks.size();
        for (int i = 0; i < taskCount; i++) {
            int printedIndex = i + 1; // table is 0-indexed, but we print starting from 1
            result.append(printedIndex).append(".").append(tasks.get(i));
            if (i < taskCount - 1) { // Don't append a newline after the last task
                result.append("\n");
            }
        }
        printWithTerminalLines(result.toString());
    }
}
