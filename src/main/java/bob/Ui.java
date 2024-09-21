package bob;

import java.util.Scanner;

public class Ui {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
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

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printWithFormat(String text) {
        String t = SEPARATOR + "\n "
                + text.replace("\n", "\n ")
                + "\n" + SEPARATOR;
        System.out.println(LINE_PREFIX + t.replace("\n", "\n" + LINE_PREFIX) + "\n");
    }

    public void printGreeting() {
        System.out.println(LOGO);
        printWithFormat("Hey there! Bob at your service.\n"
                + "Let's roll up our sleeves and get to work!");
    }

    public void printExit() {
        printWithFormat("I'll be here if you need me. Catch you later!");
    }

    public void printError(String errorMessage) {
        printWithFormat(ANSI_RED + errorMessage + ANSI_RESET);
    }

    public String readInput() {
        return scanner.nextLine();
    }
}
