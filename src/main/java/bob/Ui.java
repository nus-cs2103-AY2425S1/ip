package bob;

import bob.util.FormattedString;

import java.util.Scanner;

/**
 * The Ui class handles interactions with the user, including reading inputs and printing outputs.
 */
public class Ui {
    // TODO: this class can become a stream structure
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private static final String SEPARATOR = "____________________________________________________________";
    private static final String LINE_PREFIX = "    ";
    private static final String LOGO = """
         .----------------. .----------------. .----------------.
        | .--------------. | .--------------. | .--------------. |
        | |   ______     | | |     ____     | | |   ______     | |
        | |  |_   _ \\    | | |   .'    `.   | | |  |_   _ \\    | |
        | |    | |_) |   | | |  /  .--.  \\  | | |    | |_) |   | |
        | |    |  __'.   | | |  | |    | |  | | |    |  __'.   | |
        | |   _| |__) |  | | |  \\  `--'  /  | | |   _| |__) |  | |
        | |  |_______/   | | |   `.____.'   | | |  |_______/   | |
        | |              | | |              | | |              | |
        | '--------------' | '--------------' | '--------------' |
        '----------------' '----------------' '----------------'
        """;
    private final Scanner scanner;
    private String lastMessage = "";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Outputs the given text formatted as: <br>
     * <pre>
     *     ____________________________________________________________
     *      &lt;text&gt;
     *     ____________________________________________________________
     * </pre>
     *
     * @param text the text to output
     */
    public void printWithFormat(String text) {
        assert text != null : "Text to be printed should not be null";
        printWithFormat(new FormattedString(text));
    }

    /**
     * Outputs the given {@code FormattedString} as: <br>
     * <pre>
     *     ____________________________________________________________
     *      &lt;str&gt;
     *     ____________________________________________________________
     * </pre>
     *
     * @param str the {@code FormattedString} to output
     */
    public void printWithFormat(FormattedString str) {
        assert str != null : "str should not be null";

        String t = SEPARATOR + "\n "
                + str.toString().replace("\n", "\n ")
                + "\n" + SEPARATOR;
        System.out.println(LINE_PREFIX + t.replace("\n", "\n" + LINE_PREFIX) + "\n");
        lastMessage = str.getUnformatted();
    }

    /**
     * Outputs the logo, then the greeting message:
     * "Hey there! Bob at your service. Let's roll up our sleeves and get to work!"
     */
    public void printGreeting() {
        System.out.println(LOGO);
        printWithFormat("Hey there! Bob at your service.\n"
                + "Let's roll up our sleeves and get to work!");
    }

    /**
     * Outputs the exit message: "I'll be here if you need me. Catch you later!"
     */
    public void printExit() {
        printWithFormat("I'll be here if you need me. Catch you later!");
    }

    /**
     * Outputs the given error message in red.
     *
     * @param errorMessage the error message to output
     */
    public void printError(String errorMessage) {
        assert errorMessage != null : "Error message should not be null";
        printWithFormat(new FormattedString(errorMessage).color(FormattedString.COLOR.RED));
    }

    /**
     * Reads an input from the user.
     *
     * @return the string that was input
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Returns the unformatted last output of this Ui instance.
     *
     * @return the unformatted last output of this Ui instance.
     */
    public String getLastMessage() {
        return lastMessage;
    }
}
