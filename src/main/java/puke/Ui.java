package puke;

import java.util.Scanner;

import puke.exceptions.PukeException;
import puke.message.MessageBuilder;

/**
 * Handles the user interface, including input parsing and message display.
 */
public class Ui {
    private MessageBuilder messageBuilder;
    private Parser parser;

    /**
     * Constructs a Ui instance with the specified Parser and MessageBuilder.
     *
     * @param parser the Parser to handle user commands
     * @param messageBuilder the MessageBuilder to construct and display messages
     */
    public Ui(Parser parser, MessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
        this.parser = parser;
    }

    /**
     * Starts the user interface, displaying the greeting message and processing user input.
     *
     * @throws PukeException if an error occurs while parsing user input
     */
    public void start() throws PukeException {
        messageBuilder.sendGreetingMessage();
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();
            if (userInput.trim().equalsIgnoreCase("bye")) {
                break;
            }
            parser.handleInput(userInput);
        }

        terminate();
        scanner.close();
    }

    /**
     * Terminates the user interface, displaying the goodbye message.
     */
    private void terminate() {
        messageBuilder.sendGoodbyeMessage();
    }
}
