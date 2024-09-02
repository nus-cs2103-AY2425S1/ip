package chatbot;

import chatbot.impl.KatChatBotImpl;
import chatbot.impl.MessageParserImpl;
import chatbot.impl.TaskStorageImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskStorage taskStorage = new TaskStorageImpl();
        MessageParser messageParser = new MessageParserImpl(taskStorage);
        ChatBot chatBot = new KatChatBotImpl(scanner, messageParser);

        chatBot.start();
    }

}
