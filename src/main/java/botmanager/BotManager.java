package botmanager;

import java.io.FileNotFoundException;
import java.io.IOException;

import action.Action;
import exception.BotException;
import task.TaskList;
import util.Parser;
import util.Storage;
import util.Ui;

/**
 * Entry point for the BotManager Chatbot
 *
 * @author dwsc37
 */
public class BotManager {
    private final Ui ui;
    private final Parser parser;
    private final Storage storage;

    public BotManager() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
    }

    public void run() {
        TaskList taskList = new TaskList();
        try {
            storage.loadTaskList(taskList);
        } catch (FileNotFoundException e) {
            ui.showLoadError();
            storage.initFile();
        }
        ui.start();
        while (true) {
            // read user input
            String input = ui.readUserInput();
            if (input.strip().equals("bye")) {
                break;
            }

            // parse and execute command
            try {
                Action action = parser.parseInput(input);
                String output = action.execute(taskList);
                ui.printMessage(output);
            } catch (BotException e) {
                ui.printMessage(e.getMessage());
            }

            // save task list to file
            try {
                storage.saveTaskList(taskList);
            } catch (IOException e) {
                ui.showSaveError();
            }
        }

        ui.exit();
    }

    public static void main(String[] args) {
        new BotManager().run();
    }
}
