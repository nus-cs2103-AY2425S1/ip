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

    /**
     * Starts program loop and calls initialising and ending functions.
     */
    private void run() {
        start();
        runUntilExit();
        exit();
    }

    /**
     * Initialises parser, UI, storage, and task list elements for the whole program.
     */
    private void start() {
        // On start, need to create parser, load data from storage to make Task List
        parser = new Parser();
        ui = new UI();
        storage = new Storage(filePath);
        taskList = storage.load();
        ui.showWelcomeMessage();
    }

    /**
     * Runs a program loop that continually received user input until the UI receives a "bye" command.
     */
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

    /**
     * Calls the UI to generate a parting message and terminates the program.
     */
    private void exit() {
        ui.showByeMessage();
        System.exit(0);
    }

}
