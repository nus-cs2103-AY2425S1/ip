package serenity;

import java.io.IOException;

public class Serenity {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Serenity(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (SerenityException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
        }
    }

    public void run() {

        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                Parser.parse(input, tasks, ui, storage);
                isExit = Parser.isExit(input);
            } catch (SerenityException | IOException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Serenity("data/serenity.txt").run();
    }

}
