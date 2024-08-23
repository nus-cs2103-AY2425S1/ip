package duke;

import java.util.List;

import duke.tasks.Task;

public class Ui {
    public static final String BOT_NAME = "DailyTasks";
    public static final String GREETING = "Hello! I'm " + BOT_NAME + ", your awesome task planner!";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";
    public static final String SPACING = "    ";
    public static final String BORDER = "____________________________________________________________";

    public static String formattedBorder() {
        return Ui.SPACING + Ui.BORDER;
    }

    private static String formatSingleTask(int i, Task task) {
        return Ui.SPACING + " " + i + "." + task.toString() + "\n";
    }

    public void showWelcome() {
        System.out.println(Ui.formatOutputMessage(GREETING));
    }

    public void showGoodbye() {
        System.out.println(Ui.formatOutputMessage(GOODBYE));
    }

    public static String formatTaskListings(List<Task> tasks, boolean filtered) {
        StringBuilder str = new StringBuilder();

        str.append(Ui.formattedBorder()).append("\n");
        str.append(Ui.SPACING + " ")
                .append(filtered ? "Here are the filtered tasks:" : "Here are the tasks in your list:")
                .append("\n");
        for (int i = 0; i < tasks.size(); i++) {
            str.append(Ui.formatSingleTask(i + 1, tasks.get(i)));
        }
        str.append(Ui.formattedBorder()).append("\n");

        return str.toString();
    }

    public static String formatOutputMessage(String input) {
        return (
            Ui.formattedBorder() + "\n"
                    + Ui.SPACING + " " + input + "\n"
                    + Ui.formattedBorder() + "\n"
            );
    }

    public static String formatAddTask(int taskCount, Task task) {
        return (
            Ui.formattedBorder() + "\n"
                    + Ui.SPACING + " " + "Got it. I've added this task:" + "\n"
                    + Ui.SPACING + "   " + task.toString() + "\n"
                    + Ui.SPACING + " " + "Now you have " + taskCount + " tasks in the list." + "\n"
                    + Ui.formattedBorder() + "\n"
            );
    }

    public static String formatMarkTask(Task task) {
        return (
            Ui.formattedBorder() + "\n"
                    + Ui.SPACING + " " + "Nice! I've marked this task as done:" + "\n"
                    + Ui.SPACING + "   " + task.toString() + "\n"
                    + Ui.formattedBorder() + "\n"
            );
    }

    public static String formatUnmarkTask(Task task) {
        return (
            Ui.formattedBorder() + "\n"
                    + Ui.SPACING + " " + "OK, I've marked this task as not done yet:" + "\n"
                    + Ui.SPACING + "   " + task.toString() + "\n"
                    + Ui.formattedBorder() + "\n"
            );
    }

    public static String formatDeleteTask(Task task, int size) {
        return (
            Ui.formattedBorder() + "\n"
                    + Ui.SPACING + " " + "Noted. I've removed this task:" + "\n"
                    + Ui.SPACING + "   " + task.toString() + "\n"
                    + Ui.SPACING + " " + "Now you have " + size + " tasks in the list." + "\n"
                    + Ui.formattedBorder() + "\n"
            );
    }
}
