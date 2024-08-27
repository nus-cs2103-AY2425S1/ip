package ui;

import task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Ui {
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "_________________________________________";
    private static final String MESSAGE_GREETING = "Hello! I'm Taskon\nWhat can I do for you?\n";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!\n";
    private static final String MESSAGE_MARK = "Woohoo! task.Task complete! \nI've marked this as done:\n";
    private static final String MESSAGE_UNMARK = "Got it! No rush, I've marked it as not done yet:\n";
    private static final String MESSAGE_NO_TASKS = "Hmm, it looks like you've got a free day!\n";
    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    /** Format of a comment input line */
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

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

    public void listItems(ArrayList<? extends Task> tasks) {
        out.println("Here's what we've got on your to-do list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println(i+1 + "." + t.toString());
        }
    }

    public void mark(Task task) {
        out.println(MESSAGE_MARK + task + LS);
    }

    public void unmark(Task task) {
        out.println(MESSAGE_UNMARK + task + LS);
    }

    public void showTasksOnDate(String date, ArrayList<Task> tasks) {
        LocalDate localDate = LocalDate.parse(date);
        boolean isFound = false;
        out.println("Tasks on " + localDate + " :");
        for (Task task : tasks) {
            if (task.occursOn(localDate)) {
                out.println(task);
                isFound = true;
            }
        }
        if (!isFound) {
            out.println(MESSAGE_NO_TASKS);
        }
    }
}
