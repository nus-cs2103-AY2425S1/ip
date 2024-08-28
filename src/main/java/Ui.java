/** Represents the UI handler of the chatbot.
 * @author Lee Ze Hao (A0276123J)
 */

public class Ui {
    static final String HORIZONTAL_LINE = "____________________________________________________________";

    private String name;

    /**
     * Creates a Ui handler instance.
     * @param name Name of the chatbot.
     */
    public Ui(String name) {
        this.name = name;
    }

    /**
     * Prints a greeting message.
     */
    public void printGreeting() {
        System.out.println("Hello! I'm " + name + ".\nWhat can I do for you?");
        showLine();
    }

    public void printBye() {
        System.out.println("Bye.");
        showLine();
    }

    /**
     * Prints a horizontal line / border.
     */
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }
}
