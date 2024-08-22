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

    /**
     * Outputs an error with the given text. The text will be prefixed by `ERROR:`, and
     * is formatted using ANSI codes to be red (not working, using System.err)
     *
     * @param errorText text to be printed out as the error.
     */
    public void error(String errorText) {
        // TODO ANSI color codes no work
        String output = "\\u001B[31m ERROR:" + errorText + "\\u001B[0m";

        System.err.println(errorText);
    }
}
