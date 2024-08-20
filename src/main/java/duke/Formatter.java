package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class Formatter {
    public static final String SPACING = "    ";
    public static final String BORDER = "____________________________________________________________";

    public static String formattedBorder() {
        return Formatter.SPACING + Formatter.BORDER;
    }

    private static String formatSingleTask(int i, Task task) {
        return Formatter.SPACING + " " + i + "." + task.toString() + "\n";
    }

    public static String formatTaskListings(ArrayList<Task> tasks) {
        StringBuilder str = new StringBuilder();

        str.append(Formatter.formattedBorder()).append("\n");
        str.append(Formatter.SPACING + " " + "Here are the tasks in your list:" + "\n");
        for (int i = 0; i < tasks.size(); i++) {
            str.append(Formatter.formatSingleTask(i + 1, tasks.get(i)));
        }
        str.append(Formatter.formattedBorder()).append("\n");

        return str.toString();
    }

    public static String formatOutputMessage(String input) {
        return (
            Formatter.formattedBorder() + "\n" +
            Formatter.SPACING + " " +  input + "\n" +
            Formatter.formattedBorder() + "\n"
        );
    }

    public static String formatAddTask(int taskCount, Task task) {
        return (
            Formatter.formattedBorder()  + "\n"+
            Formatter.SPACING + " " +  "Got it. I've added this task:" + "\n" +
            Formatter.SPACING + "   " + task.toString() + "\n" +
            Formatter.SPACING + " " + "Now you have " + taskCount + " tasks in the list." + "\n" +
            Formatter.formattedBorder() + "\n"
        );
    }

    public static String formatMarkTask(Task task) {
        return (
            Formatter.formattedBorder() + "\n"+
            Formatter.SPACING + " " + "Nice! I've marked this task as done:" + "\n" +
            Formatter.SPACING + "   " + task.toString() + "\n" +
            Formatter.formattedBorder() + "\n"
        );
    }

    public static String formatUnmarkTask(Task task) {
        return (
            Formatter.formattedBorder() + "\n"  +
            Formatter.SPACING + " " + "OK, I've marked this task as not done yet:" + "\n" +
            Formatter.SPACING + "   " + task.toString() + "\n" +
            Formatter.formattedBorder() + "\n"
        );
    }

    public static String formatDeleteTask(Task task, int size) {
        return (
            Formatter.formattedBorder() + "\n" +
            Formatter.SPACING + " " + "Noted. I've removed this task:" + "\n" +
            Formatter.SPACING + "   " + task.toString() + "\n" +
            Formatter.SPACING + " " + "Now you have " + size + " tasks in the list." + "\n" +
            Formatter.formattedBorder() + "\n"
        );
    }
}
