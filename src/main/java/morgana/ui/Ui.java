package morgana.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_LINE = "============================================================\n";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public void showWelcomeMessage(String name) {
        showToUser("Hello! I'm %s.".formatted(name), "What can I do for you?");
    }

    public void showGoodbyeMessage() {
        showToUser("Bye! Hope to see you again soon!");
    }

    public void showToUser(String... messages) {
        out.print(HORIZONTAL_LINE);
        for (String message : messages) {
            out.println(message);
        }
        out.println(HORIZONTAL_LINE);
    }
}
