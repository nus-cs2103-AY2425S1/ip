import commands.Command;
import exceptions.DownyException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class Downy {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Downy(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        this.tasks.loadTasks(storage);

    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.storage, this.tasks, this.ui);
                isExit = c.isExit();
            } catch (DownyException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Downy("data/tasks.txt").run();
    }
}
