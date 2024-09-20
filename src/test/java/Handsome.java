import commands.Command;
import exceptions.HandsomeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Chatbot that is able to differentiate tasks
 */
public class Handsome {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Handsome(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.welcomeUser();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcomeUser();
        boolean isExit = false;
        while(!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException | HandsomeException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args) throws HandsomeException {
        new Handsome("./data/handsome.txt").run();
    }
}
