package main;

import command.Command;
import exception.DashException;

public class Dash {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Dash(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        Ui.displayGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.nextInput();
                Command command = Parser.parse(input);
                command.execute(tasks, storage);
                isExit = command.isExit();
            } catch (DashException e) {
                Ui.showError(e.getMessage());
            } finally {
                Ui.insertLine();
            }

        }
        Ui.displayGoodbye();
    }

    public static void main(String[] args) {
        new Dash("./data/dash.txt").run();
    }
}