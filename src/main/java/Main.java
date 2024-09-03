package main.java;

import main.java.impl.AtlasView;
import main.java.impl.Command;
import main.java.impl.Atlas;
import main.java.impl.TaskStorageImpl;
import main.java.interfaces.ChatBot;
import main.java.interfaces.MessageView;
import main.java.interfaces.TaskStorage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MessageView<Command> messageView = new AtlasView();
        TaskStorage<Command> taskStorage = new TaskStorageImpl();
        ChatBot bot = new Atlas(scanner, messageView, taskStorage);
        bot.start();
    }
}
