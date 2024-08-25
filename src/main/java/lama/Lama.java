package lama;

import lama.command.Command;

public class Lama {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public Lama(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTask());
        } catch (LamaException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.run(taskList, storage, ui);
                isExit = c.isExit();
            } catch (LamaException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Lama("./data/lama.txt").run();
    }
}
