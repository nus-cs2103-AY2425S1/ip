package bob;

import java.util.Scanner;

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
    private final Scanner SCANNER;

    public Ui() {
        this.SCANNER = new Scanner(System.in);
    }

    public void printWithFormat(String text) {
        String t = SEPARATOR + "\n " + text.replace("\n", "\n ") + "\n" + SEPARATOR;
        System.out.println(LINE_PREFIX + t.replace("\n", "\n" + LINE_PREFIX) + "\n");
    }

    /**
     * Highlights occurrences of {@code keyword} in {@code text} with a yellow background, and returns the result.
     * Occurrences of the keyword is matched ignoring the case if ignoreCase is true, and vice versa.
     *
     * @param text the text to output
     * @param keyword the keyword to highlight
     * @param ignoreCase matches occurrences of the keyword ignoring the case if true, and vice versa
     */
    public String highlightKeyword(String text, String keyword, boolean ignoreCase) {
        if (!ignoreCase) {
            return highlightKeyword(text, keyword);
        }

        return text.replaceAll("(?i)(" + keyword + ")",
                ANSI_YELLOW_BACKGROUND + "$1" + ANSI_RESET);
    }

    /**
     * {@code ignoreCase} defaults to {@code false}
     *
     * @see #highlightKeyword(String, String, boolean)
     */
    public String highlightKeyword(String text, String keyword) {
        return text.replace(keyword, ANSI_YELLOW_BACKGROUND + keyword + ANSI_RESET);
    }

    public void printGreeting() {
        System.out.println(LOGO);
        printWithFormat("Hey there! Bob at your service.\n" +
                "Let's roll up our sleeves and get to work!");
    }

    public void printExit() {
        printWithFormat("I'll be here if you need me. Catch you later!");
    }

    public void printError(String errorMessage) {
        printWithFormat(ANSI_RED + errorMessage + ANSI_RESET);
    }

    public String readInput() {
        return SCANNER.nextLine();
    }
}
