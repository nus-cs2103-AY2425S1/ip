package henry;

import henry.command.Command;
import henry.gui.Main;
import henry.util.Parser;
import henry.util.Storage;
import henry.util.TaskList;
import henry.util.Ui;
import javafx.application.Application;

/**
 * a Personal Assistant Chatbot that helps a person
 * to keep track of various things.
 */
public class Henry {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Sets up the chat.
     *
     * @param filePath Path of the file where it is saved.
     */
    public Henry(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (HenryException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui);
            c.save(tasks, storage);
            return response;
        } catch (HenryException e) {
            return "Sorry! " + e.getMessage() + "\n";
        }
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
