package victor;

import java.nio.file.Path;
import java.nio.file.Paths;

import victor.commands.Command;
import victor.messages.ReturnMessage;
import victor.parser.Parser;
import victor.storage.Storage;
import victor.tasklist.TaskList;
import victor.ui.UI;

public class Main {
    private final Path filePath = Paths.get("data", "data.txt");
    private Parser parser;
    private UI ui;
    private TaskList taskList;
    private Storage storage;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        start();
        runUntilExit();
        exit();
    }

    private void start() {
        // On start, need to create parser, load data from storage to make Task List
        parser = new Parser();
        ui = new UI();
        storage = new Storage(filePath);
        taskList = storage.load();
        ui.showWelcomeMessage();
    }

    private void runUntilExit() {
        Command nextCommand;
        do {
            nextCommand = parser.parseInput(ui.getUserInput());
            nextCommand.setData(taskList);
            ReturnMessage returnMessage = nextCommand.execute();
            ui.showUserMessage(returnMessage);
            nextCommand.write(filePath);
        } while (!nextCommand.isExit());
    }

    private void exit() {
        ui.showByeMessage();
        System.exit(0);
    }

}
