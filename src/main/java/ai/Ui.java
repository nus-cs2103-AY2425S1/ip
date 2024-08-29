package ai;

/**
 * Prints output to the user.
 */
public class Ui {
    /**
     * Prints the line separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints the first line user sees when booting up Ai.
     */
    public void showGreetings() {
        showLine();
        System.out.println("Hi, I'm your favourite idol, Ai!!!\n"
                + "What shall we do today? Teehee o(◠u◠)o\n");
        showLine();
    }

    /**
     * Prints the last line user sees when exiting Ai.
     */
    public void showGoodbye() {
        System.out.println("Don't you wanna get my autograph first?\n"
                + "Aww okie :,( See ya!!\n");
    }

    /**
     * Prints out the error message.
     *
     * @param msg Error message to be printed.
     */
    public void showError(String msg) {
        System.err.println(msg);
    }
}
