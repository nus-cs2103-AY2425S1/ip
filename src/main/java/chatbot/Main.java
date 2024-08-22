package chatbot;

import chatbot.impl.KatChatBotImpl;

public class Main {

    public static void main(String[] args) {
        ChatBot chatBot = new KatChatBotImpl();
        chatBot.start();
    }

}
