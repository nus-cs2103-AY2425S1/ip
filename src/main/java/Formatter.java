public class Formatter {
    public static final String SPACING = "    ";
    public static final String BORDER = "____________________________________________________________\n";

    public static String formattedBorder() {
        return Formatter.SPACING + Formatter.BORDER;
    }

    private static String formatSingleTask(int i, Task task) {
        return Formatter.SPACING + " " + i + "." + "[" + task.getStatusIcon() + "]" + " " + task.toString() + "\n";
    }

    public static String formatTaskListings(int numTasks, Task[] tasks) {
        StringBuilder str = new StringBuilder();

        str.append(formattedBorder());
        str.append(Formatter.SPACING + " " + "Here are the tasks in your list:" + "\n");
        for (int i = 0; i < numTasks; i++) {
            str.append(Formatter.formatSingleTask(i + 1, tasks[i]));
        }
        str.append(formattedBorder());

        return str.toString();
    }

    public static String formatOutputMessage(String input) {
        return (
            formattedBorder() +
            Formatter.SPACING + " " +  input + "\n" +
            formattedBorder()
        );
    }
}
