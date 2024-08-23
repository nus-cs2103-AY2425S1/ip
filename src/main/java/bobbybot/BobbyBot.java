package bobbybot;

import bobbybot.commands.Command;
import bobbybot.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BobbyBot {
    private TaskList tasks;
    private final Ui ui;
    private final Storage storage;
    private static final String chatBotName = "bobbybot.BobbyBot";

    BobbyBot() {
        ui = new Ui();
        storage = new Storage("data/bobbybot.txt");
        try {
            ArrayList<Task> taskArray = storage.getTasksFromFile();
            tasks = new TaskList(taskArray);
        } catch (Exception e) {
            ui.printInput("Error reading from file. Starting with an empty task list.");
            tasks = new TaskList();
        }
    }

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
            } catch (DukeException e) {
                ui.printError(e);
            }
        }
    }
}
