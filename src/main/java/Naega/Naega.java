package Naega;

import Naega.Command.Command;
import Naega.Parser.Parser;
import Naega.Storage.Storage;
import Naega.Task.TaskList;
import Naega.Ui.Ui;

import java.io.IOException;

/**
 * The main class for the Naega application.
 * Handles initialization, command processing, and application flow.
 */
public class Naega {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;


    public Naega(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NaegaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (NaegaException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                // Consider logging the exception or providing a more specific message
                throw new RuntimeException("Unexpected error occurred during IO operations.", e);
            } finally {
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) {
        new Naega("data/tasks.txt").run();
    }
}