package main.java.chatbot;

import main.java.chatbot.exceptions.ChatsyException;
import main.java.chatbot.exceptions.LocalFileException;

public class Chatsy {
    static final String name = "Chatsy";
    static final String LOCAL_DIRECTORY_PATH = "./data";
    static final String LOCAL_FILE_PATH = LOCAL_DIRECTORY_PATH + "/chatsy.txt";
    static boolean isRunning = true;
    static TaskManager taskManager;
    static Storage storage;
    static UI ui;
    static CommandHandler commandHandler;

    public static void main(String[] args) {
        storage = new Storage(LOCAL_DIRECTORY_PATH, LOCAL_FILE_PATH);
        ui = new UI();
        taskManager = new TaskManager();
        commandHandler = new CommandHandler(taskManager, ui);

        try {
            taskManager.setTasks(storage.loadTasks());
        } catch (LocalFileException e) {
            ui.output("Failed to load tasks: " + e.getMessage());
        }

        ui.greet(name);
        while (isRunning) {
            try {
                commandHandler.handle(ui.readCommand());
            } catch (ChatsyException e) {
                ui.output("OOPS!!! " + e.getMessage());
            } catch (Exception e) {
                ui.output("An unexpected error occurred: " + e.getMessage());
            }
        }

        exit();
    }

    public static void exit() {
        try {
            storage.saveTasks(taskManager.getTasks());
        } catch (LocalFileException e) {
            ui.output("Failed to save tasks: " + e.getMessage());
        }
        ui.output("Bye. Hope to see you again soon!");
        isRunning = false;
    }
}
