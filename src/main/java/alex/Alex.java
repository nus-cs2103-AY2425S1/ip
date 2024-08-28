package alex;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.format.DateTimeParseException;

public class Alex {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Alex(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (AlexException | FileNotFoundException | DateTimeParseException e) {
            ui.showError(e);
        }
    }

    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (AlexException e) {
                ui.showError(e);
            } catch (IOException e) {
                ui.showError(e);
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Alex("./data/Alex.txt").run();
    }
}
