import susan.command.Command;
import susan.task.TaskList;
import susan.ui.Parser;
import susan.ui.Storage;
import susan.ui.SusanException;
import susan.ui.Ui;

import java.io.IOException;

public class Susan {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Susan() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
    }

    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (SusanException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Susan().run();
    }
}
