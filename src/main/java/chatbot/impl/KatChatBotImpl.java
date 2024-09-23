package chatbot.impl;

import java.util.Scanner;

import chatbot.ChatBot;
import chatbot.MessageParser;
import chatbot.exceptions.InvalidMessageException;

/**
 * Implements the ChatBot interface representing a chatbot named Kat.
 * This class handles user interactions, processes input, and generates responses.
 */
public class KatChatBotImpl implements ChatBot {

    private static final String LOGO = """
             _         _
            | | __ ___| |_
            | |/ / _  | __|
            |   < (_| | |_
            |_|\\_\\____|\\__|
            """;

    private static final String GREETING_RESPONSE = "Hi! I'm Kat.\nHow can I help?";

    private static final String ENDING_COMMAND = "bye";

    private static final String ENDING_RESPONSE = "See you!";

    private final Scanner scanner;

    private final MessageParser messageParser;

    /**
     * Constructs a new KatChatBotImpl with the specified scanner and message parser.
     *
     * @param scanner       The Scanner object for reading user input
     * @param messageParser The MessageParser object for processing user messages
     */
    public KatChatBotImpl(Scanner scanner, MessageParser messageParser) {
        this.scanner = scanner;
        this.messageParser = messageParser;
    }

    @Override
    public void start() {
        System.out.println("Hello from\n" + LOGO);

        respond(GREETING_RESPONSE);
        while (true) {
            System.out.println("> Me");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase(ENDING_COMMAND)) {
                break;
            }

            try {
                String response = messageParser.handleMessage(input);
                respond(response);
            } catch (InvalidMessageException e) {
                respond(e.getMessage());
            }
        }

        respond(ENDING_RESPONSE);
    }

    @Override
    public String getResponse(String input) {
        if (input.equalsIgnoreCase(ENDING_COMMAND)) {
            return ENDING_RESPONSE;
        }

        try {
            return messageParser.handleMessage(input);
        } catch (InvalidMessageException e) {
            return e.getMessage();
        }
    }

    /**
     * Displays the chatbot's response with formatting.
     *
     * @param responseMsg The message to be displayed as the chatbot's response.
     */
    private void respond(String responseMsg) {
        System.out.println("~".repeat(50));
        System.out.println("> Kat");
        System.out.println(responseMsg);
        System.out.println("~".repeat(50));
    }

}
