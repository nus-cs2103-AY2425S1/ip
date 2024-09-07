import elysia.ui.ElysiaException;
import elysia.ui.InputOutputHandler;
import elysia.ui.Message;

import java.util.Scanner;

/**
 * The main class for the Elysia application.
 * This class handles the main program loop, where user input is continuously accepted and processed.
 * It interacts with the user via the console and manages the flow of the application.
 */
public class Elysia {


    /**
     * The main method that starts the Elysia application.
     * Displays an initial greeting, processes user commands, and handles the application shutdown.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        Message.print("Hi~! I'm Elysia! As you can see, I'm a girl as beautiful as as flower!\n" +
                "How can I help you today? I'm all ears!");

        Scanner command = new Scanner(System.in);
        String input;
        boolean repeat = true;
        InputOutputHandler handler = new InputOutputHandler();

        while (repeat) {
            input = command.nextLine();
            try {
                repeat = handler.parseInput(input);
            } catch (ElysiaException | StringIndexOutOfBoundsException e) {
                Message.print("What are you trying to say? You need to talk to pretty girls nicely...");
            }
        }

        Message.print("Aww, going already? Don't miss me too much, ok?");
    }
}
