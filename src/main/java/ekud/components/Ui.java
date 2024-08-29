package ekud.components;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Represents the UI of EKuD, responsible for outputting EKuD's responses and reading the user's input.
 *
 * @author uniqly
 * @see ekud.Ekud
 */
public class Ui {
    /** Line separator between outputs by EKuD and inputs by the user */
    private static final String LINE_SEPARATOR =
            "\t_____________________________________________________________";

    /** Prefix appended to outputs by EKuD */
    private final String prefix;

    /**
     * Creates the ui using the input prefix.
     *
     * @param prefix The prefix to be appended to EKuD's output.
     */
    public Ui(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Reads and returns the user's input from a given source.
     *
     * @param sc The {@link Scanner} which is the source of the user's input.
     * @return The user's input
     */
    public String readCommand(Scanner sc) {
        System.out.println();
        String command = sc.nextLine();
        return command;
    }

    /**
     * Greets the user.
     */
    public void printGreeting() {
        String greeting = "Hey! My name is EkuD!!\nYou can call me Eku-Chan!";
        printLineSeparator();
        printOutput(greeting);
        printLineSeparator();
    }

    /**
     * Says goodbye to the user.
     */
    public void printGoodbye() {
        String goodbye = "I hope you enjoyed your stay!\nSee you next time! NOT!!";
        printOutput(goodbye);
    }

    /**
     * Prepends each line of a given input with the {@link Ui#prefix} and prints them sequentially.
     *
     * @param input The input {@link String}.
     */
    public void printOutput(String input) {
        Stream<String> lines = input.lines();
        lines.forEach(line -> System.out.printf("%s%s%n", prefix, line));
    }

    /**
     * Prints the {@link Ui#LINE_SEPARATOR}.
     */
    public void printLineSeparator() {
        System.out.println(LINE_SEPARATOR);
    }
}
