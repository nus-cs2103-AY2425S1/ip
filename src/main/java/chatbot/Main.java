package chatbot;

import chatbot.impl.ui.AtlasView;
import chatbot.impl.Command;
import chatbot.impl.Atlas;
import chatbot.impl.TaskStorageImpl;
import chatbot.interfaces.ChatBot;
import chatbot.interfaces.MessageView;
import chatbot.interfaces.TaskStorage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       MessageView<Command> messageView = new AtlasView();
       TaskStorage<Command> taskStorage = new TaskStorageImpl("data/atlas.txt");
       ChatBot bot = new Atlas(scanner, messageView, taskStorage);
       bot.start();
    }
}
