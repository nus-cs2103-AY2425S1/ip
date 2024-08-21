/**
 * Produces greetings for users and initializes chat.
 */

import java.util.Scanner;

public class Nether {
    private static final int MAX_TASKS = 100;
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";

    /**
     * The main method where the program starts.
     * Initializes the application, takes user input, and responds based on commands.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        String logo = " _   _      _   _        \n"
                + "| \\ | | ___| |_| |__  ___ _ __ \n"
                + "|  \\| |/ _ \\ __| '_ \\/ _ \\ '__|\n"
                + "| |\\  |  __/ |_| | | ||__/ |  \n"
                + "|_| \\_|\\___|\\__|_| |_\\___|_|\n";
        String[] tasks = new String[MAX_TASKS];
        int counter = 0;

        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println("Hello sir! I'm Nether");
        System.out.println("What can I do for you today?");
        printHorizontalLine();

        Scanner scanner = new Scanner(System.in);
        System.out.print("");
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase(EXIT_COMMAND)) {
                break;
            }

            if (userInput.equalsIgnoreCase(LIST_COMMAND)) {
                for (int i = 0; tasks[i] != null; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                printHorizontalLine();
                continue;
            }

            printHorizontalLine();
            System.out.println("added: " + userInput);
            printHorizontalLine();

            // update tasks array and increment counter
            tasks[counter] = userInput;
            counter++;
        }

        printHorizontalLine();
        System.out.println("Bye. If you need any more help in the future, feel free to ask me. Enjoy your day!");
        printHorizontalLine();
    }

    /**
     * Prints out a long horizontal line to act as separator in the chat
     */
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
}
