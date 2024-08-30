import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserCommand() {
        return in.nextLine();
    }

    public void showToUser(String message) {
        out.println(message);
    }

    public void showWelcomeMessage() {
        showToUser("""
                Hello! I'm Park
                What can I do for you?""");;
    }

    public void showGoodbyeMessage() {
        showToUser("Bye. Hope to see you again soon!");
    }
}
