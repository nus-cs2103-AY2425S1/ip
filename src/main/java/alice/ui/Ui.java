package alice.ui;

/** Handles output to the user. */
public class Ui {
    private static final String FACE = "(*-.-)";
    private static final String DIVIDER = "____________________________________________________________";

    /**
     * Prints a formatted response to the user.
     */
    public void say(String line) {
        System.out.println(DIVIDER);
        System.out.println(String.format("%s: %s", FACE, line));
        System.out.println(DIVIDER);
    }

    /**
     * Prints a formatted response to the user.
     */
    public void say(String[] lines) {
        System.out.println(DIVIDER);
        System.out.println(String.format("%s: %s", FACE, lines[0]));
        for (int i = 1; i < lines.length; i++) {
            System.out.println(String.format("\t%s", lines[i]));
        }
        System.out.println(DIVIDER);
    }

    /**
     * Prints a formatted warning to the user.
     */
    public void warn(String line) {
        say(String.format("⚠ Oops! %s", line));
    }

    /**
     * Prints a formatted warning to the user.
     */
    public void warn(String[] lines) {
        lines[0] = String.format("⚠ Oops! %s", lines[0]);
        say(lines);
    }
}
