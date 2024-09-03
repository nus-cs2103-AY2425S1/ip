package ekud.ui;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Represents the UI of EKuD when running on CLI mode.
 */
public class TextUi extends Ui {
    /** Line separator between outputs by EKuD and inputs by the user */
    private static final String LINE_SEPARATOR =
            "\t_____________________________________________________________";

    /** Prefix appended to outputs by EKuD */
    private static final String PREFIX = "\t ";

    /** Scanner to read user input from */
    private final Scanner sc;


    /**
     * Creates the text ui with a scanner to read input from.
     *
     * @param sc The {@link Scanner} to read user input from.
     */
    public TextUi(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public String readCommand() {
        System.out.println(); // print blank line
        String input = sc.nextLine();
        printLineSeparator();
        return input;
    }

    @Override
    public void printGreeting() {
        printLineSeparator();
        String greeting = "Hey! My name is EkuD!!\nYou can call me Eku-Chan!";
        addToBuffer(greeting);
        printOutput();
    }

    @Override
    public void printGoodbye() {
        String goodbye = "I hope you enjoyed your stay!\nSee you next time! NOT!!";
        addToBuffer(goodbye);
        printOutput();
    }

    @Override
    public void printOutput() {
        String output = collectBuffer();
        Stream<String> lines = output.lines();
        lines.forEach(line -> System.out.printf("%s%s%n", PREFIX, line));
        printLineSeparator();
    }

    /**
     * Prints the {@link #LINE_SEPARATOR}.
     */
    public void printLineSeparator() {
        System.out.println(LINE_SEPARATOR);
    }


}
