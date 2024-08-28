package fanny;
import fanny.ui.Ui;
import fanny.task.TaskList;
import fanny.storage.Storage;
import fanny.command.Command;
import fanny.parser.Parser;

public class Fanny {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Fanny(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage);
    }

    public void run() {
        ui.printHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getUserInput();
                ui.showHorizontalLine();
                Command c = Parser.parse(input);
                c.actionable(tasks, ui);
                isExit = c.isExit();
            } catch (FannyException e) {
                ui.showMessage(e.getMessage());
            } finally {
                ui.showHorizontalLine();
            }
        }
    }

    public static void main(String[] args) {
        new Fanny("./data/fanny.txt").run();
    }
}
