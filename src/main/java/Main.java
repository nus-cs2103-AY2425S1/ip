import Messages.ReturnMessage;
import Parser.Parser;
import UI.UI;
import TaskList.TaskList;
import java.nio.file.Path;
import java.nio.file.Paths;
import Storage.Storage;
import Commands.Command;

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
