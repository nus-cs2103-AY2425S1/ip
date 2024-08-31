package main.java.util;

import main.java.Task;
import main.java.TaskList;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {
    private final static String LINE = "_______________________\n";
    private final Scanner scanner;


    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns a string from the next line of user input
     */
    public String readInput() {
        try {
            String result = this.scanner.nextLine();
            return result;
        } catch (NoSuchElementException e) {
            System.err.println("Ui readInput: no such element exception");
            return "";
        }
    }

    public void showWelcome() {
        String output = LINE +
                "Hi! I'm Karen\n" +
                "What can I do for you?\n" +
                LINE;
        System.out.print(output);
    }

    public void sayGoodbye() {
        String output = LINE
                + "Bye! Hope to see you again!\n"
                + LINE;
        System.out.print(output);
    }

    public void showMarkMessage(Task t) {
        String output = LINE +
                "Nice! I've marked this task as done:\n\t" +
                t.toString() + "\n" +
                LINE;
        System.out.print(output);
    }

    public void showUnmarkMessage(Task t) {
        String output = LINE +
                "Ok! This task is now marked undone:\n\t" +
                t.toString() + "\n" +
                LINE;
        System.out.print(output);
    }

    public void showAddTaskMessage(Task t, TaskList taskList) {
        String output = LINE +
                "Got it! Added this task:\n\t" +
                t.toString() + "\n" +
                String.format("Now you have %d tasks in the list.\n", taskList.getSize()) +
                LINE;
        System.out.print(output);
    }

    public void showDeleteMessage(Task t) {
        String output = LINE
                + "Alright! I've removed this task from your list:\n\t"
                + t.toString() + "\n"
                + LINE;
        System.out.print(output);
    }

    public void displayTaskList(TaskList taskList) {
        String[] taskStrings = taskList.toTaskStrings();

        String output;
        if (taskStrings.length == 0) {
            output = LINE
                    + "No tasks yet!\n"
                    + LINE;
        } else {
            output = LINE;
            for (int i = 0; i < taskStrings.length; i++) {
                output += String.format("%d. %s\n", i + 1, taskStrings[i]);

            }
            output += LINE;
        }
        System.out.print(output);

    }

    public void showUnknownInputError() {
        String output = LINE
                + "Sorry! I couldn't recognise that :(\n"
                + "Try one of these commands:\n"
                + "\t list\n"
                + "\t todo <task name>\n"
                + "\t deadline <task name> /by <datetime>\n"
                + "\t event <task name> /from <datetime /to <datetime>\n"
                + "\t mark <task index>\n"
                + "\t unmark <task index>\n"
                + "\t delete <task index>\n"
                + "\t bye\n"
                + LINE;
        System.out.print(output);
    }

    public void showDateTimeError() {
        String output = LINE
                + "Invalid format! Datetime must be in this form: E.g. 2024-10-11 1200\n"
                + LINE;
        System.out.print(output);
    }
}
