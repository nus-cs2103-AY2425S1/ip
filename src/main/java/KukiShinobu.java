import exception.KukiShinobuException;
import parser.Parser;
import storage.Storage;
import task.*;
import ui.Ui;
import command.*;

import java.util.ArrayList;
import java.util.List;


public class KukiShinobu {
    private final String name = "Kuki Shinobu";
    // IMPORTANT: Relative Filepath Specified must always be relative to root directory of the entire project
    private static final String FILE_PATH = "./data/database.txt";
    public static void main(String[] args) {
        KukiShinobu kukiShinobu = new KukiShinobu(FILE_PATH);
        kukiShinobu.listen();
    }

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public KukiShinobu(String filePath) {
        // TODO: Create constructor that takes in a filePath and then self instantiates everything else needed
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (KukiShinobuException e) {
            //TODO: Show more informative error message depending on the error that was thrown
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static boolean readBoolean(int i) {
        return i != 0;
    }

    public void listen() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                this.ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (KukiShinobuException e) {
                this.ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                this.storage.write(this.tasks.getTasks());
            }
        }
        ui.showGoodbye();
    }
}
