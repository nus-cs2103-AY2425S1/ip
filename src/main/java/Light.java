import command.Command;
import components.Parser;
import components.Storage;
import components.Ui;
import exceptions.LightException;
import task.TaskList;

import java.util.NoSuchElementException;

public class Light {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Light(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LightException e) {
                ui.showError(e);
            } catch (NoSuchElementException e) {
                ui.closeUI();
                return;
            }
        }
    }

    public static void main(String[] args) {
        Light program = new Light("./data/saved.txt");
        program.run();
    }

}

