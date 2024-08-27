package ned;

import ned.commands.Command;
import ned.exceptions.NedException;

import java.util.ArrayList;
import java.nio.file.Paths;
public class Ned {
    private Storage storage;

    private Ui ui;

    private TaskList tasks;
    public static final String cachedTasksPath = Paths.get("src", "data", "cachedTasks.txt").toString();
    public Ned(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage.load());
        } catch (NedException e){
            ui.showLoadingError();
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage, fullCommand);
                isExit = c.isExit();
            } catch (NedException e) {
                ui.print(e.getMessage());
            }
        }
    };
    public static void main(String[] args) {
        new Ned(Ned.cachedTasksPath).run();
    }
}
