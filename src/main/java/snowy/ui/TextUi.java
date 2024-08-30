package snowy.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TextUi {

    private static final String LINE = "____________________________________________________________";

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showLine() {
        out.println(LINE);
    }

    public void showWelcomeMessage() {
        showToUser(LINE, "Hello! I'm Snowy. What can I do for you?", LINE);
    }

    public void showGoodbyeMessage() {
        showToUser(LINE, "Bye. See you next time!", LINE);
    }

    public void showErrorMessage(String message) {
        showToUser(LINE, message, LINE);
    }

    public void showTaskAddedMessage(String message) {
        showToUser(message, LINE);
    }

    public void showTaskMarkedMessage(String message) {
        showToUser(LINE, "Marked as done", LINE);
    }

    public void showTaskUnmarkedMessage(String message) {
        showToUser(LINE, "Unmarked task", LINE);
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public void showToUser(String... messages) {
        for (String message : messages) {
            out.println(message);
        }
    }
}
