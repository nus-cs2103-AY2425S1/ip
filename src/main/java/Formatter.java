public class Formatter {
    public static final String SPACING = "    ";
    public static final String BORDER = "____________________________________________________________\n";

    public static String formattedBorder() {
        return Formatter.SPACING + Formatter.BORDER;
    }

    private static String formatSingleTask(int i, Task task) {
        return Formatter.SPACING + " " + i + "." + task.toString() + "\n";
    }

    public static String formatTaskListings(int numTasks, Task[] tasks) {
        StringBuilder str = new StringBuilder();

        str.append(Formatter.formattedBorder());
        str.append(Formatter.SPACING + " " + "Here are the tasks in your list:" + "\n");
        for (int i = 0; i < numTasks; i++) {
            str.append(Formatter.formatSingleTask(i + 1, tasks[i]));
        }
        str.append(Formatter.formattedBorder());

        return str.toString();
    }

    public static String formatOutputMessage(String input) {
        return (
            Formatter.formattedBorder() +
            Formatter.SPACING + " " +  input + "\n" +
            Formatter.formattedBorder()
        );
    }

    public static String formatAddTask(int taskCount, Task task) {
        return (
            Formatter.formattedBorder() +
            Formatter.SPACING + " " +  "Got it. I've added this task:" + "\n" +
            Formatter.SPACING + "   " + task.toString() + "\n" +
            Formatter.SPACING + " " + "Now you have " + taskCount + " tasks in the list." + "\n" +
            Formatter.formattedBorder()
        );
    }

    public static String formatMarkTask(Task task) {
        return (
            Formatter.formattedBorder()  +
            Formatter.SPACING + " " + "Nice! I've marked this task as done:" + "\n" +
            Formatter.SPACING + "   " + "[" + task.getStatusIcon() + "]" + " " +
            task.getDescription() + "\n" +
            Formatter.formattedBorder()
        );
    }

    public static String formatUnmarkTask(Task task) {
        return (
            Formatter.formattedBorder()  +
            Formatter.SPACING + " " + "OK, I've marked this task as not done yet:" + "\n" +
            Formatter.SPACING + "   [" + task.getStatusIcon() + "] " +
            task.getDescription() + "\n" +
            Formatter.formattedBorder()
        );
    }
}
