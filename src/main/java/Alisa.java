import java.util.ArrayList;
import java.util.Scanner;

public class Alisa {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Alisa(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (AlisaException e) {
            ui.showErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDivider();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AlisaException e) {
                ui.showErrorMessage(e.getMessage());
            } finally {
                ui.showDivider();
            }
        }
    }

    public static void main(String[] args) {
        new Alisa("data/tasks.txt").run();
    }
}







