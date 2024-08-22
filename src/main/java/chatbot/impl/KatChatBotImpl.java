package chatbot.impl;

import chatbot.ChatBot;
import chatbot.MessageParser;
import chatbot.exceptions.InvalidMessageException;

import java.util.Scanner;

public class KatChatBotImpl implements ChatBot {

    private static final String LOGO = """
             _         _
            | | __ ___| |_
            | |/ / _  | __|
            |   < (_| | |_
            |_|\\_\\____|\\__|
            """;

    private final Scanner scanner;

    private final MessageParser messageParser;

    public KatChatBotImpl() {
        scanner = new Scanner(System.in);
        messageParser = new MessageParserImpl(new TaskStorageImpl());
    }

    @Override
    public void start() {
        System.out.println("Hello from\n" + LOGO);

        respond("Hi! I'm Kat.\nHow can I help?");
        while (true) {
            System.out.println("> Me");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                break;
            }

            try {
                String response = messageParser.handleMessage(input);
                respond(response);
            } catch (InvalidMessageException e) {
                respond(e.getMessage());
            }
        }

        respond("See you!");
    }

    private void respond(String responseMsg) {
        System.out.println("~".repeat(50));
        System.out.println("> Kat");
        System.out.println(responseMsg);
        System.out.println("~".repeat(50));
    }

}
