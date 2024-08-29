package atlas;

import atlas.commands.Command;
import atlas.exceptions.AtlasException;
import atlas.parser.Parser;
import atlas.storage.Storage;
import atlas.tasks.TaskList;
import atlas.ui.Ui;

public class Atlas {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public Atlas(String filepath) {
        this.storage = new Storage(filepath);
        this.ui = new Ui();

        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (AtlasException e) {
            this.ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (AtlasException e) {
                this.ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Atlas("./data/atlas.txt").run();
    }
}
