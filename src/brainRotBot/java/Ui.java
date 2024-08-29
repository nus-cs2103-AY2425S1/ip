
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
public class Ui {

    private static final String LINE = "____________________________________________________________\n";

    protected static final String GREETING =
                    "Hello! I'm fanumTaxRizzlerOhioSigmaLooksmaxxer\n"
                    + "What can I do for you?\n";

    protected static final String GOODBYE =
            "Bye. Hope to see you again soon!\n";

    protected static final String COMMANDERROR =
            ("OOPS!!! I'm sorry, but I don't know what that means :-(\n");

    protected static final String ACTIVITYERROR =
            ("OOPS!!! The description of an activity cannot be empty.\n");
    protected static final String LOADINGERROR =
            ("OOPS!!! I'm sorry, your database is not loading properly:-(\n");

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private final Scanner in;
    private final PrintStream out;


    public Ui() {
        this(System.in, System.out);

    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcomeMessage() {
        System.out.println(LINE + GREETING + LINE);
    }


    public void showExit() {
        System.out.println(GOODBYE + LINE);
    }

    public void showLoadingError() {
        System.out.println(LINE + LOADINGERROR + LINE);
    }

    public void showActivityError() {
        System.out.println(LINE  + ACTIVITYERROR + LINE);
    }

    public void showCommandError() {
    System.out.println(LINE + COMMANDERROR + LINE);
    }
    public String getUserCommand() {
        out.print("Enter command: ");
        String fullInputLine = in.nextLine();

        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }

        showToUser("[Command entered:" + fullInputLine + "]");
        return fullInputLine;
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    /** Shows message(s) to the user */
    public void showToUser(String... message) {
        for (String m : message) {
            out.println(m.replace("\n", LS ));
        }
    }

}
