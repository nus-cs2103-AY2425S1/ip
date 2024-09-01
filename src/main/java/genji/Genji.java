package genji;

import genji.command.Command;
import genji.task.TaskList;

public class Genji {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private static String FILE_PATH = "./data/Genji.txt";

    public Genji(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = storage.loadList();
    }

    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (GenjiException g) {
                ui.showError(g.toString());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Genji(FILE_PATH).run();
    }
}
