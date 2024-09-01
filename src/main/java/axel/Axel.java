package axel;

import java.io.IOException;
public class Axel {
    private static final String FILE_PATH = "./data/axel.txt";
    protected Storage storage;
    private TaskList tasks;
    protected Ui ui;
    public Axel(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | CorruptedFileException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
        }
    }
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (AxelException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }
    public static void main(String[] args) {
        new Axel(FILE_PATH).run();
    }
}

