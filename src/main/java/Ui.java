public class Ui {
    private static final String DIVIDER = "---------------------------------------------------";
    private static  final String GREETING = "Hello! I'm Gemini, what can I do for you today?";
    private static  final String GOODBYE = "Bye! Hope to see you again soon :)";

    /**
     * Returns greeting message
     */
    public String greeting() {
        return DIVIDER + "\n" + GREETING + "\n" + DIVIDER;
    }

    /**
     * Returns goodbye message
     */
    public String goodbye() {
        return GOODBYE + "\n" + DIVIDER;
    }
}
