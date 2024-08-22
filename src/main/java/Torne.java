/**
 * This is the main class for `Torne`.
 * All the main commands are here.
 */

public class Torne {
    private static final ChatOutput OUTPUT = new ChatOutput();

    /**
     * Shows a greeting message, to be shown when user initialises Torne.
     */
    private void greet() {
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

How may I help you today?""";
        OUTPUT.writeText(greetingText);
    }

    /**
     * Prints a standard exit message and quits the bot.
     */
    private void exit() {
        String exitText = """
Aww, bye to you as well :c""";
        OUTPUT.writeText(exitText);
    }

    public static void main(String[] args) {
        Torne torne = new Torne();

        // Level 0: Greet user and exit.
        torne.greet();
        torne.exit();
    }
}
