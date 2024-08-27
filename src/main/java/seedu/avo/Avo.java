package seedu.avo;

import seedu.avo.commands.Command;
import seedu.avo.commands.CommandManager;
import seedu.avo.exceptions.AvoException;

import seedu.avo.parser.CommandParser;
import seedu.avo.storage.FileStorage;
import seedu.avo.storage.Storage;
import seedu.avo.storage.TaskParser;
import seedu.avo.tasks.Task;
import seedu.avo.tasks.TaskManager;
import seedu.avo.ui.UI;

public class Avo {
    private final UI ui;
    private final CommandParser parser;
    private Avo(UI ui, CommandParser parser) {
        this.ui = ui;
        this.parser = parser;
    }
    private void start() {
        ui.showWelcome();
    }
    private void stop() {
        ui.showExit();
    }
    private void run() {
        try {
            listen();
        } catch (AvoException e) {
            ui.showError(e.getMessage());
            run();
        }
    }
    private void listen() throws AvoException {
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readInput();
            Command command = parser.parse(userInput);
            command.execute(userInput);
            isExit = command.isExit();
        }
    }
    public static void main(String[] args) {
        Storage<Task, String> storage = new FileStorage<Task>("data", new TaskParser());
        TaskManager taskManager = new TaskManager(storage);
        CommandParser parser = new CommandParser(new CommandManager(taskManager));
        Avo chatBot = new Avo(new UI(), parser);
        chatBot.start();
        chatBot.run();
        chatBot.stop();
    }
}
