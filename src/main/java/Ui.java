import java.util.Scanner;
import java.util.stream.Stream;

public class Ui {
    private static final String LINE_SEPARATOR =
            "\t_____________________________________________________________";

    private final String prefix;

    public Ui(String prefix) {
        this.prefix = prefix;
    }

    public String readCommand(Scanner sc) {
        System.out.println();
        String command = sc.nextLine();
        return command;
    }

    public void printGreeting() {
        String greeting = "Hey! My name is EkuD!!\nYou can call me Eku-Chan!";
        printLineSeparator();
        printOutput(greeting);
        printLineSeparator();
    }

    public void printGoodbye() {
        String goodbye = "I hope you enjoyed your stay!\nSee you next time! NOT!!";
        printOutput(goodbye);
    }

    public void printOutput(String input) {
        Stream<String> lines = input.lines();
        lines.forEach(line -> System.out.printf("%s%s%n", prefix, line));
    }

    public void printLineSeparator() {
        System.out.println(LINE_SEPARATOR);
    }
}
