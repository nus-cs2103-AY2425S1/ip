package chatbot;

import java.util.Scanner;

import chatbot.impl.KatChatBotImpl;
import chatbot.impl.MessageParserImpl;
import chatbot.impl.TaskStorageImpl;

/**
 * The Main class contains the entry point of the application.
 * It initializes and starts the chatbot.
 */
public class Main {

    /**
     * Sets up the necessary components and starts the chatbot.
     *
     * @param args Command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskStorage taskStorage = new TaskStorageImpl();
        MessageParser messageParser = new MessageParserImpl(taskStorage);
        ChatBot chatBot = new KatChatBotImpl(scanner, messageParser);

        chatBot.start();
    }

}
