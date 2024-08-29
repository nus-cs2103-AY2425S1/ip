package stan;

import java.util.ArrayList;
import stan.commands.Command;
import stan.exceptions.StanException;

public class Stan {
    private static final String FILE_PATH = "data/stan.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Stan(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(new ArrayList<>(storage.loadTasks()));
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
            } catch (StanException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Stan(FILE_PATH).run();
    }
}
