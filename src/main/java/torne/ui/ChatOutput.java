package torne.ui;

/**
 * class that handles text outputs for the chatbot.
 * <p></p>
 * TODO needs some kind of return format (with whether it's an error, etc)
 */
public class ChatOutput {
    private static final String BORDER_LINE = "-------------------------------------------------------------";
    public static final String INDENT = "    ";

    // records the currently shown output.
    private ChatResponse currentOut;

    /**
     * Shows a greeting message, to be shown when user initialises Torne.
     */
    void showGreeting() {
        String greetingText = """
                Hello! I am
                  _______ ____  _____  _   _ ______\s
                 |__   __/ __ \\|  __ \\| \\ | |  ____|
                    | | | |  | | |__) |  \\| | |__  \s
                    | | | |  | |  _  /| . ` |  __| \s
                    | | | |__| | | \\ \\| |\\  | |____\s
                    |_|  \\____/|_|  \\_\\_| \\_|______|
                                                   \s
                short for Torment Nexus™, your F R I E N D L Y neighborhood chatbot :3
                                
                How may I help you today?
                                
                💡 Pro tip: Use command `help` to view all commands!""";
        writeText(greetingText);
    }

    /**
     * Prints a standard exit message.
     */
    void showExitMessage() {
        String exitText = """
                Aww, bye to you as well :c""";
        writeText(exitText);
    }

    /**
     * Takes in a text string, and outputs it to the console
     *
     * @param text {@link ChatResponse} with text string to be output.
     */
    public void writeText(String text) {
        currentOut = ChatResponse.response(text);
        System.out.println(text);
    }

    /**
     * Takes in a text string, wraps it inside a pair of borderlines, and outputs it to the console
     *
     * @param text {@link ChatResponse} with text string to be output.
     */
    public void writeTextBordered(String text) {
        String output = BORDER_LINE + "\n" + text + "\n" + BORDER_LINE;
        currentOut = ChatResponse.response(output);

        System.out.println(output);
    }

    /**
     * Outputs an error with the given text. The text will be prefixed by `ERROR:`, and
     * is formatted using ANSI codes to be red (not working, using System.err)
     *
     * @param errorText {@link ChatResponse} with text to be printed out as the error.
     */
    public void error(String errorText) {
        // TODO ANSI color codes no work
        String output = "\\u001B[31m ERROR:" + errorText + "\\u001B[0m";
        currentOut = ChatResponse.error(errorText);

        System.err.println(errorText);
    }

    /**
     * Gets the current output sent to this {@link ChatOutput}.
     *
     * @return Output {@link ChatResponse}.
     */
    public ChatResponse getCurrentOut() {
        return currentOut;
    }
}
