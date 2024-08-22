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
        return DIVIDER + "\n" + GOODBYE + "\n" + DIVIDER;
    }

    /**
     * Returns a copy of the message typed by the user
     */
    public String echo(String echoMessage) {
        return DIVIDER + "\n" + echoMessage + "\n" + DIVIDER;
    }
}
