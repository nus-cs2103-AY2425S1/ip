package bimo;
/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static final String LINE = "    " + "___________________________________";
    /**
     * Displays a horizontal line.
     */
    public String showLine() {
        return LINE;
    }
    /**
     * Displays error message for invalid tasks index.
     */
    public String showTaskNotFoundError() {
        return ("Task not found in list");
    }

    /**
     * Displays error message for invalid commands.
     */
    public String showErrorMessage() {
        return ("Something went wrong! Please try again");
    }
}
