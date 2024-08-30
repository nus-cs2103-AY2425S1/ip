package dudu;

import dudu.command.Command;
import dudu.utils.Parser;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

import java.io.IOException;

public class Dudu {
    private TaskList tasks;
    private UI ui;
    private Storage storage;

    public Dudu(String filePath) {
        ui =  new UI();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Dudu("./data/dudu.txt").run();
    }
}
