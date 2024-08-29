package lict;

import lict.command.Command;

public class Lict {
    private Storage storage;
    private TaskList tasks;
    private static Ui ui;

    public Lict(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LictException e) {
            ui.showError(e.getMessage());
        }
    }

    public void run() {
        ui.startConversation();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LictException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Lict("data/LictData.txt").run();
    }
}
