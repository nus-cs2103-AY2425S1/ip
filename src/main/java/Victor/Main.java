package Victor;

import java.nio.file.Path;
import java.nio.file.Paths;
import Victor.Commands.Command;
import Victor.Messages.ReturnMessage;
import Victor.Parser.Parser;
import Victor.Storage.Storage;
import Victor.TaskList.TaskList;
import Victor.UI.UI;

public class Main {
    private final Path FILE_PATH = Paths.get("data", "data.txt");
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
        storage = new Storage(FILE_PATH);
        taskList = storage.load();
        ui.welcomeMessage();
    }

    private void runUntilExit() {
        Command nextCommand;
        do {
            nextCommand = parser.parseInput(ui.getUserInput());
            nextCommand.setData(taskList);
            ReturnMessage returnMessage = nextCommand.execute();
            ui.showUserMessage(returnMessage);
            nextCommand.write(FILE_PATH);
        } while (!nextCommand.isExit());
    }

    private void exit() {
        ui.byeMessage();
        System.exit(0);
    }

}
