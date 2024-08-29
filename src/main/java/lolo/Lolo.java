package lolo;
import lolo.command.Command;
import lolo.command.Parser;
import lolo.storage.Storage;
import lolo.task.TaskList;

public class Lolo {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Lolo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LoloException e) {
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
            } catch (LoloException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Lolo("./data/lolo.txt").run();
    }
}


