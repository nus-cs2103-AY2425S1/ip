public class Formatter {
    public static final String SPACING = "    ";
    public static final String BORDER = "____________________________________________________________\n";

    public static String formattedBorder() {
        return Formatter.SPACING + Formatter.BORDER;
    }

    private static String formatSingleTask(int i, String task) {
        return Formatter.SPACING + " " + i + ". " + task + "\n";
    }

    public static String formatTaskListings(int numTasks, String[] tasks) {
        StringBuilder str = new StringBuilder();

        str.append(formattedBorder());
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
