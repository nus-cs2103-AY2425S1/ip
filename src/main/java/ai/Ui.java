package ai;

/**
 * Prints output to the user.
 */
public class Ui {
    /**
     * Prints the first line user sees when booting up Ai.
     */
    public String showGreeting() {
        return "Hi, I'm your favourite idol, Ai!!!\n"
                + "What shall we do today? Teehee o(◠u◠)o\n";
    }

    /**
     * Prints the last line user sees when exiting Ai.
     */
    public String showGoodbye() {
        return "Don't you wanna get my autograph first?\n"
                + "Aww okie :,( See ya!!\n";
    }

    /**
     * Prints out the error message.
     *
     * @param msg Error message to be printed.
     */
    public String showError(String msg) {
        return msg;
    }
}
