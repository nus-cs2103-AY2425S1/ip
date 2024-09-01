package king;

import king.commands.Command;

public class King {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public King(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (KingException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (KingException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new King("data/tasks.txt").run();
    }
}
