package jeff;

import jeff.command.Command;
import jeff.exception.JeffException;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.TaskList;
import jeff.ui.Ui;

public class Jeff {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Jeff(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();

        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (JeffException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (JeffException e) {
                this.ui.showError(e.toString());
            }
        }
    }

    public static void main(String[] args) {
        new Jeff("data/tasks.txt").run();
    }
}
