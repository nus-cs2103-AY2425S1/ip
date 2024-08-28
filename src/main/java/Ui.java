import java.util.Scanner;

/** Represents the UI handler of the chatbot.
 * @author Lee Ze Hao (A0276123J)
 */

public class Ui {
    static final String HORIZONTAL_LINE = "____________________________________________________________";

    private String name;
    private String filePath;
    private Parser parser;

    /**
     * Creates a Ui handler instance.
     * @param name Name of the chatbot.
     */
    public Ui(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
        this.parser = new Parser(this, filePath);
    }

    /**
     * Handles input and responds with output from Parser.
     *
     */
    public void run() {
        printGreeting();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            showLine();
            try {
                parser.processInput(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            showLine();
        }
    }

    /**
     * Prints a greeting message.
     */
    public void printGreeting() {
        showLine();
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
