package bobbybot;

import bobbybot.commands.Command;
import bobbybot.ui.Ui;

import java.util.ArrayList;

/**
 *  BobbyBot is a chatbot that helps you keep track of your tasks.
 */
public class BobbyBot {
    private TaskList tasks;
    private final Ui ui;
    private final Storage storage;
    private static final String chatBotName = "BobbyBot";

    /**
     * Creates an instance of BobbyBot.
     */
    public BobbyBot() {
        ui = new Ui();
        storage = new Storage("./data/bobbybot.txt");
        try {
            ArrayList<Task> taskArray = storage.getTasksFromFile();
            tasks = new TaskList(taskArray);
        } catch (Exception e) {
            ui.printInput("Error reading from file. Starting with an empty task list.");
            tasks = new TaskList();
        }
    }

    /**
     * Main entry-point for the bobbybot.BobbyBot application.
     */
    public static void main(String[] args) {
        BobbyBot bot = new BobbyBot();
        bot.runBot();
    }

    private void runBot() {
        ui.printInput("Hello! I'm " + chatBotName, "What can I do for you?");
        boolean isExit = false;
        while (!isExit) {
            try {
                final String input = ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (BobbyBotException e) {
                ui.printError(e);
            }
        }
    }
}
