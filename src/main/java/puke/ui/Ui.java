package puke.ui;

import java.util.Scanner;

import puke.exceptions.PukeException;
import puke.handlers.InputManager;

public class Ui {
    private MessageBuilder messageBuilder;
    private InputManager inputManager;

    public Ui(InputManager inputManager, MessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
        this.inputManager = inputManager;
    }

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

    private void terminate() {
        messageBuilder.sendGoodbyeMessage();
    }
}
