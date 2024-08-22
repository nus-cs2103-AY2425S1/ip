// import java.io.Console;

/**
 * class that handles text outputs for the chatbot.
 */
public class ChatOutput {
    private static final String BORDER_LINE = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";

    /**
     * Takes in a text string, wraps it inside a pair of borderlines, and outputs it to the console
     *
     * @param text text string to be outputed.
     */
    public void writeText(String text) {
        String output = BORDER_LINE + "\n" + text + "\n" + BORDER_LINE;

        System.out.println(output);
    }
}
