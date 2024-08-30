package puke.ui;

import java.util.Scanner;

import puke.exceptions.PukeException;
import puke.handlers.InputManager;

/**
 * Handles the user interface, including input handling and message display.
 */
public class Ui {
    private MessageBuilder messageBuilder;
    private InputManager inputManager;

    /**
     * Constructs a Ui instance with the specified InputManager and MessageBuilder.
     *
     * @param inputManager the InputManager to handle user commands
     * @param messageBuilder the MessageBuilder to construct and display messages
     */
    public Ui(InputManager inputManager, MessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
        this.inputManager = inputManager;
    }

    /**
     * Starts the user interface, displaying the greeting message and processing user input.
     *
     * @throws PukeException if an error occurs while handling user input
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
            inputManager.handleInput(userInput);
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
