package app;

import command.Command;
import fridayException.FridayException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.*;
import java.time.format.DateTimeParseException;

public class Friday {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Friday(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            this.ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isEndScanner = false;
        while (!isEndScanner) {
            try {
                String commandRaw = ui.readCommand();
                Command command = Parser.parse(commandRaw);
                command.execute(tasks, ui, storage);
                isEndScanner = command.isEndScanner();
            } catch (FridayException | DateTimeParseException e) {
                if (e instanceof DateTimeParseException) {
                    ui.showError("Please enter a valid date in the format yyyy-mm-dd.");
                } else {
                    ui.showError(e.getMessage());
                }
            }
        }
        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Friday("data/fridayTaskList.txt").run();

    }
}