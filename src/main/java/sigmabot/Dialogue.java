package sigmabot;

import sigmabot.command.Command;
import sigmabot.command.Greeting;
import sigmabot.command.ListOperation;
import sigmabot.command.Terminate;
import sigmabot.util.ListReader;

import java.util.Scanner;

/**
 * The Dialogue class manages the interaction between the user and the chatbot.
 * It processes user commands and controls the flow of conversation until the user decides to exit.
 */
public class Dialogue {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Private constructor to prevent direct instantiation.
     * Use the defaultDialogue() method to create a new Dialogue instance.
     */
    private Dialogue() {}

    /**
     * Creates a new instance of the Dialogue class.
     *
     * @return A new Dialogue object.
     */
    public static Dialogue defaultDialogue() {
        return new Dialogue();
    }

    /**
     * Starts the dialogue session with the user.
     * Greets the user and then continuously reads commands from the user until a Terminate command is issued.
     */
    public void run() {
        Greeting.greet();  // Initial greeting to the user
        Command command;
        do {
            System.out.println("Enter a command (/list, /exit): ");
            command = readCommand();  // Read the user's command
            command.execute(scanner);  // Execute the user's command
        } while (!(command instanceof Terminate));  // Continue until a Terminate command is received
    }

    /**
     * Reads a command from the user input.
     * The method loops until a valid command is entered by the user.
     *
     * @return A Command object that represents the user's input.
     */
    private Command readCommand() {
        while (true) {
            String input = scanner.nextLine().trim();
            switch (input) {
            case "/exit":
                return new Terminate();  // Return a Terminate command if the user types '/exit'
            case "/list":
                return new ListOperation();  // Return a ListOperation command if the user types '/list'
            default:
                System.out.println("Unknown command. Please enter '/list' or '/exit'.");  // Prompt for a valid command if input is unknown
            }
        }
    }
}
