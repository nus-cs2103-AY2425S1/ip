package xbot;

import xbot.ui.Ui;
import xbot.storage.Storage;
import xbot.parser.Parser;

import java.io.IOException;

public class XBot {
    private static TaskList list = new TaskList();
    private static Storage storage = new Storage();
    private static Ui ui = new Ui();
    private static Parser parser = new Parser();
    public static void main(String[] args) {
        try {
            storage.loadTask();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        ui.showWelcome();
        String input = ui.readCommand();
        while(!input.equalsIgnoreCase("bye")) {
            try {
                parser.processInput(input, list, ui, storage);
            } catch (XBotException e) {
                ui.mainErrorMessage(e);
            }
            input = ui.readCommand();
        }
        ui.showBye();
        ui.close();
    }
}
