package dudu;

import java.io.IOException;

import dudu.command.Command;
import dudu.utils.Parser;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents the main class of the chatbot.
 */
public class Dudu {
    private TaskList tasks;
    private UI ui;
    private Storage storage;

    /**
     * The constructor for a chatbot instance.
     *
     * @param filePath The file path of the file containing existing tasks
     */
    public Dudu(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Starts the chatbot and persistently takes in user input until a "bye" command is issued
     */
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

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (IOException e) {
            return ui.showError(e);
        }
    }

    public static void main(String[] args) {
        new Dudu("./data/dudu.txt").run();
    }
}
