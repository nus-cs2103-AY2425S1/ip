import Tasks.TaskList;

import exceptions.PrinceException;
import util.Parser;
import util.Storage;
import util.Ui;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;

public class Prince {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    // flag to control input printing when running automated tests
    // private static boolean DEBUG = false;

    public Prince(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        // try {
        // tasks = new TaskList(storage.loadFromFile());
        // } catch (PrinceException e) {
        // ui.showLoadingError();
        // tasks = new TaskList();
        // }

        tasks = new TaskList(storage.loadFromFile());

    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            // try {
            // String userInput = ui.readInput();
            // // prints input if in automated testing mode
            // // if (DEBUG) {
            // // System.out.println(userInput);
            // // }
            // ui.showLine();
            // isExit = Parser.parse(userInput, tasks, storage, ui);
            // // Command c = Parser.parse(userInput, tasks, storage, ui);
            // // isExit = c.isExit();
            // } catch (PrinceException | IOException e) {
            // ui.showError(e.getMessage());
            // } finally {
            // ui.showLine();
            // }

            String userInput = ui.readInput();
            // prints input if in automated testing mode
            // if (DEBUG) {
            // System.out.println(userInput);
            // }
            ui.showLine();
            isExit = Parser.parse(userInput, tasks, storage, ui);
            // Command c = Parser.parse(userInput, tasks, storage, ui);
            // isExit = c.isExit();

        }
    }

    public static void main(String[] args) {

        // check if debug argument is passed during automated text ui testing
        // if (args.length > 0 && args[0].equals("debug")) {
        // DEBUG = true;
        // }

        new Prince(Paths.get("data", "storage.txt")).run();
    }
}