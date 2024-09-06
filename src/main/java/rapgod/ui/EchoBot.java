package rapgod.ui;

import rapgod.RapGod;
import rapgod.exceptions.RudeInputException;
import rapgod.exceptions.NoInputException;

import java.util.Scanner;

/**
 * A class that represents a simple command-line bot named Echo Bot.
 * The bot interacts with the user by echoing back their input with an additional suffix,
 * and handles special cases for empty or rude inputs.
 * It continues to run in a loop until the user inputs "Bye", at which point the bot exits.
 */
public class EchoBot {

    /**
     * Starts the Echo Bot, which processes user input in a loop.
     * The bot displays an initialization message, then continuously reads user input from the console.
     * For each input, the bot checks if the input is empty or contains rude words, throwing exceptions if necessary.
     * If the input is not empty or rude, the bot echoes the input with a suffix unless the input is "Bye".
     * If the input is "Bye", the bot exits the loop and prints a farewell message.
     *
     * The bot handles the following exceptions:
     * - {@link NoInputException} if the input is null or empty.
     * - {@link RudeInputException} if the input contains rude words.
     */
    public static void run() {

        String initialise = """
                -----------------------------------------------
                Initialising Echo Bot...
                -----------------------------------------------
                """;

        System.out.print(initialise);

        Scanner scanner = RapGod.scanner;
        String input;

        while (true) {
            System.out.print("You:\n");
            input = scanner.nextLine();

            try {
                if (input == null || input.trim().isEmpty()) {
                    throw new NoInputException();
                } else if (RapGod.RUDE_WORDS.contains(input)) {
                    throw new RudeInputException();
                }
            } catch (NoInputException | RudeInputException exc) {
                System.out.println("-----------------------------------------------");
                System.out.println("RapGod:\n" + exc.getMessage());
                System.out.println("-----------------------------------------------");
                continue;
            }

            if (!input.equalsIgnoreCase("Bye")) {
                System.out.println("-----------------------------------------------");
                System.out.printf("RapGod:\n%s, yo!\n", input);
                System.out.println("-----------------------------------------------");

            } else {
                break;
            }

        }

        System.out.println("-----------------------------------------------");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("-----------------------------------------------");
    }
}
