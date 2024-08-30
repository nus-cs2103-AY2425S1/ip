package main.java.util;

import main.java.TaskList;

public class Ui {
    private final String LINE = "_______________________\n";

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
                output += String.format("%d. %s\n", i+1, taskStrings[i]);

            }
            output += LINE;
        }
        System.out.print(output);

    }

    public void showDateTimeError() {
        String output = LINE
                + "Invalid format! Datetime must be in this form: E.g. 2024-10-11 1200\n"
                + LINE;
        System.out.print(output);
    }
}
