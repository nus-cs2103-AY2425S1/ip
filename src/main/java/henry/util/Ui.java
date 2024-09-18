package henry.util;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Returns a hi statement.
     *
     * @return A hi statement.
     */
    public String greetings() {
        return "Hello! I'm Henry\n"
                + "What can I do for you?\n";
    }

    /**
     * Returns an exit statement.
     *
     * @return An exit statement.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints error message.
     */
    public void showError(String message) {
        System.out.println(message + "\n");
    }
}
