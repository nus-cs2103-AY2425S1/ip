package sigmabot.ui;

import sigmabot.command.Command;
import sigmabot.command.Exit;
import sigmabot.command.Joke;
import sigmabot.command.ListOperation;
import sigmabot.exceptions.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UiComponent {
    public static final String HR_LINE_IN = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";
    public static final String HR_LINE_OUT = "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n";
    public static final String HR_LINE = "——————————————————————————————————————————————————————————————————————————————\n";
    public static final String HR_LINE_DOUBLE = "==============================================================================\n";

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    private static final Scanner scanner = new Scanner(System.in);

    public void closeScanner() {
        scanner.close();
    }

    public void printDialogue(String message) {
        System.out.println(HR_LINE);
        System.out.println(message);
    }

    public Command readCommand() {
        try {
            String input = scanner.nextLine().trim();
            return switch (input) {
                case "/exit" -> new Exit();
                case "/list" -> new ListOperation();
                case "/joke" -> new Joke();
                default -> throw new InvalidInputException("Unknown command: " + input);
            };
        } catch (InvalidInputException e) {
            System.err.println("Unknown command");
        }
        return new Exit();
    }

    public String readInput() {
        String input = scanner.nextLine().trim();
        return input;
    }

    public LocalDate readDate() {
        while (true) {
            System.out.println("Please enter a date (yyyy-mm-dd) or type '/exit' to quit:");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("/exit")) {
                return LocalDate.now(); // Return current date if the user types "/exit"
            }
            try {
                LocalDate date = LocalDate.parse(input);
                return date; // Return the parsed date if it's valid
            } catch (DateTimeParseException e) {
                System.err.println("Invalid Date format. Please enter a valid date in yyyy-mm-dd format.");
            }
        }
    }

    public static Command readListCommand() {
        try {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine().trim();
                return switch (input) {
                    case "/exit" -> new Exit();
                    case "/list" -> new ListOperation();
                    case "/joke" -> new Joke();
                    default -> throw new InvalidInputException("Unknown command: " + input);
                };
            }
        } catch (InvalidInputException e) {
            System.err.println("Unknown command");
        }
        return new Exit();
    }
}
