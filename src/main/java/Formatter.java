public class Formatter {
    public static final String SPACING = "    ";
    public static final String BORDER = "____________________________________________________________\n";

    public static String formattedBorder() {
        return Formatter.SPACING + Formatter.BORDER;
    }

    public static String formatTaskListing(int i, String task) {
        return Formatter.SPACING + " " + i + ". " + task + "\n";
    }

    public static String formatOutputMessage(String input) {
        return (
            Formatter.SPACING + Formatter.BORDER +
            Formatter.SPACING + " " +  input + "\n" +
            Formatter.SPACING + Formatter.BORDER
        );
    }
}
